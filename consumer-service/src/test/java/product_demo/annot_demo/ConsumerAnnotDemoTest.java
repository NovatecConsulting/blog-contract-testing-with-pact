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

/**
 * Created by AAT on 24.01.2017.
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
                        "\"name\": \"ConsumerAtest\"," +
                        "\"description\" : \"ConsumerAtest verifies provider\"," +
                        "\"type\": \"testing product\"" +
                        "}")
                .toFragment();
    }

    @Test
    @PactVerification
    public void runTest() throws Exception {
        final String EXPECTED_NAME = "ConsumerAtest";
        final String EXTECTED_TYPE = "testing product";
        String url = pactProviderRule.getConfig().url();

        URI productInfoUri = URI.create(String.format("%s/%s", url, "product?id=537"));
        ProductRestFetcher productRestFetcher = new ProductRestFetcher();
        Product product = productRestFetcher.fetchProductInfo(productInfoUri);

        assertEquals(EXPECTED_NAME, product.getName());
        assertEquals(EXTECTED_TYPE, product.getType());
    }

}
