package product_demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("product-service")
public class ProductServiceConfigurationProperties {
    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
