package product_demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private static final String productDescription = "%s verifies provider";
    private static final String PRODUCT_NAME = "Consumer Test";
    private String productType = "testing product";

    @RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Product product(@RequestParam(value = "id", defaultValue = "1") Integer productId) {
        return new Product(productId, PRODUCT_NAME, productType, String.format(productDescription, PRODUCT_NAME
        ));
    }
}
