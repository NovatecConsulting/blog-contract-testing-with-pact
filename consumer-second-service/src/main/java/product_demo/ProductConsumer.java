package product_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@EnableConfigurationProperties(ProductServiceConfigurationProperties.class)
public class ProductConsumer {

    private static final String PRODUCT_ENDPOINT = "/product";

    private RestTemplate restTemplate;

    private ProductServiceConfigurationProperties productServiceConfigurationProperties;

    @Autowired
    public ProductConsumer(RestTemplate restTemplate,
                           ProductServiceConfigurationProperties productServiceConfigurationProperties) {
        this.restTemplate = restTemplate;
        this.productServiceConfigurationProperties = productServiceConfigurationProperties;
    }

    /**
     * Gets the product data that is being received via REST request.
     *
     * @return a Product object with the fetched data.
     */
    public Product getProductInfo(int productId) {
        String baseUrl = productServiceConfigurationProperties.getBaseUrl();
        return restTemplate
                .getForObject(
                        String.format("%s%s?id={productId}", baseUrl, PRODUCT_ENDPOINT),
                        Product.class,
                        productId);
    }
}
