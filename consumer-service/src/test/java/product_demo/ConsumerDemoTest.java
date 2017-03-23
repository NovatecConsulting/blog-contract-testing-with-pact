package product_demo;

import au.com.dius.pact.consumer.ConsumerPactTest;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static product_demo.TestConstants.*;

/**
 * The purpose of this test class is to demonstrate the style of creating a contract test with PACT and no annotation style.
 */
public class ConsumerDemoTest extends ConsumerPactTest {
    protected PactFragment createFragment(PactDslWithProvider pactDslWithProvider) {
        //@formatter:off
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        return pactDslWithProvider
                .given("test demo first state")
                .uponReceiving("ConsumerDemoTest interaction")
                    .path("/product")
                    .query("id=537")
                    .method("GET")
                .willRespondWith()
                    .status(200)
                    .headers(headers)
                    .body("{" +
                        "\"name\": \"Consumer Test\"," +
                        "\"description\" : \"Consumer Test verifies provider\"," +
                        "\"type\": \"testing product\"" +
                        "}")
                .toFragment();
         //@formatter:on
    }

    protected String providerName() {
        return "product-provider-demo";
    }

    protected String consumerName() {
        return "product-consumer-demo";
    }

    protected void runTest(String url) throws IOException {
        URI productInfoUri = URI.create(String.format("%s/%s", url, "product?id=537"));
        ProductRestFetcher productRestFetcher = new ProductRestFetcher();
        Product product = productRestFetcher.fetchProductInfo(productInfoUri);

        assertEquals(EXPECTED_NAME, product.getName());
        assertEquals(EXPECTED_TYPE, product.getType());
        assertEquals(EXPECTED_DESC, product.getDescription());
    }
}
