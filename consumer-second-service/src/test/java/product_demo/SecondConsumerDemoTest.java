package product_demo;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static product_demo.TestConstants.*;

/**
 * The purpose of this test class is to demonstrate the style of creating a contract test with PACT.
 */
@SpringBootTest
@ExtendWith(PactConsumerTestExt.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@PactTestFor(providerName = "product-provider-demo", port = "8081")
public class SecondConsumerDemoTest {

    @Autowired
    private ProductConsumer productConsumer;

    @Autowired
    private ProductServiceConfigurationProperties productServiceConfigurationProperties;

    @BeforeAll
    void setUp() {
        productServiceConfigurationProperties.setBaseUrl("http://127.0.0.1:8081");
    }

    @Pact(provider = "product-provider-demo", consumer = "product-second-consumer-demo")
    RequestResponsePact createPact(PactDslWithProvider pactDslWithProvider) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return pactDslWithProvider
                .given("second consumer demo first state")
                .uponReceiving("SecondConsumerDemoTest interaction")
                .path("/product")
                .query("id=532")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{" +
                        "\"name\": \"Consumer Test\"," +
                        "\"type\": \"testing product\"" +
                        "}")
                .toPact();
    }


    @Test
    @PactTestFor(pactMethod = "createPact")
    void runTest() {
        Product result = productConsumer.getProductInfo(PRODUCT_ID);
        Assertions.assertThat(result.getName()).isEqualTo(EXPECTED_NAME);
        Assertions.assertThat(result.getType()).isEqualTo(EXPECTED_TYPE);
    }
}
