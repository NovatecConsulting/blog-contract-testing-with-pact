package product_demo;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class ProductRestFetcher {
    /**
     * Fetches the product data that is being received via REST from another service.
     *
     * @param providerUri the URI of the provider, on which the data is returned.
     * @return a Product object with the fetched data.
     */
    public Product fetchProductInfo(URI providerUri) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(providerUri, Product.class);
    }
}
