package product_demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AAT on 23.01.2017.
 */
@RestController
public class ProductController {
    private static final String template = "%s verifies provider";
    private String productName = "ConsumerAtest";
    private String productType = "testing product";

    @RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public Product product(@RequestParam(value = "id", defaultValue = "1") Integer productId) {
        return new Product(productId, productName, productType, String.format(template, productName
        ));
    }
}
