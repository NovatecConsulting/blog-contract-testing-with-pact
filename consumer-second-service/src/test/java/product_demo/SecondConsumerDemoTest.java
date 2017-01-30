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

public class SecondConsumerDemoTest extends ConsumerPactTest {
    @Override
    protected PactFragment createFragment(PactDslWithProvider pactDslWithProvider) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
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
                .toFragment();
    }

    @Override
    protected String providerName() {
        return "product-provider-demo";
    }

    @Override
    protected String consumerName() {
        return "product-second-consumer-demo";
    }

    @Override
    protected void runTest(String url) throws IOException {

        URI productInfoUri = URI.create(String.format("%s/%s", url, "product?id=532"));
        ProductRestFetcher productRestFetcher = new ProductRestFetcher();
        Product product = productRestFetcher.fetchProductInfo(productInfoUri);

        assertEquals(EXPECTED_NAME, product.getName());
        assertEquals(EXPECTED_TYPE, product.getType());
    }
}
