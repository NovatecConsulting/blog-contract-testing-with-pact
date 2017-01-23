package product_demo;

import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by AAT on 23.01.2017.
 */
public class ProductRestFetcher {
    public Product fetchProductInfo(URI providerUri) {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(providerUri, Product.class);
    }
}
