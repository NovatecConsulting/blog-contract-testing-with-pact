package product_demo.annot_demo;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;
import org.junit.Rule;
import org.junit.Test;
import product_demo.Product;
import product_demo.ProductRestFetcher;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static product_demo.TestConstants.*;

/**
 * The purpose of this test class is to demonstrate the style of creating a contract test with PACT and annotation style.
 */
public class ConsumerAnnotDemoTest {
    @Rule
    public PactProviderRule pactProviderRule = new PactProviderRule("product-provider-demo", this);

    @Pact(consumer = "product-consumer-demo")
    public PactFragment createFragment(PactDslWithProvider pactDslWithProvider) {
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
    }

    @Test
    @PactVerification
    public void runTest() throws Exception {
        String url = pactProviderRule.getConfig().url();

        URI productInfoUri = URI.create(String.format("%s/%s", url, "product?id=537"));
        ProductRestFetcher productRestFetcher = new ProductRestFetcher();
        Product product = productRestFetcher.fetchProductInfo(productInfoUri);

        assertEquals(EXPECTED_NAME, product.getName());
        assertEquals(EXPECTED_TYPE, product.getType());
        assertEquals(EXPECTED_DESC, product.getDescription());
    }
}
