package product_demo;

import au.com.dius.pact.consumer.ConsumerPactTest;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.PactFragment;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by AAT on 23.01.2017.
 */
public class ConsumerDemoTest extends ConsumerPactTest {
    protected PactFragment createFragment(PactDslWithProvider pactDslWithProvider) {
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
                        "\"name\": \"ConsumerAtest\"," +
                        "\"description\" : \"ConsumerAtest verifies provider\"," +
                        "\"type\": \"testing product\"" +
                        "}")
                .toFragment();
    }

    protected String providerName() {
        return "product-provider-demo";
    }

    protected String consumerName() {
        return "product-consumer-demo";
    }

    protected void runTest(String url) throws IOException {
        final String EXPECTED_NAME = "ConsumerAtest";
        final String EXTECTED_TYPE = "testing product";

        URI productInfoUri = URI.create(String.format("%s/%s", url, "product?id=537"));
        ProductRestFetcher productRestFetcher = new ProductRestFetcher();
        Product product = productRestFetcher.fetchProductInfo(productInfoUri);

        assertEquals(EXPECTED_NAME, product.getName());
        assertEquals(EXTECTED_TYPE, product.getType());
    }
}
