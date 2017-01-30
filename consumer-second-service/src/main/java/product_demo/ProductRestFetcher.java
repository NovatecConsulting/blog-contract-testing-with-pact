package product_demo;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class ProductRestFetcher {
    public Product fetchProductInfo(URI providerUri) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(providerUri, Product.class);
    }
}
