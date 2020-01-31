package product_demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private static final String productDescription = "%s verifies provider";
    private static final String PRODUCT_NAME = "Consumer Test";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Product product(@RequestParam(value = "id", defaultValue = "1") Integer productId) {
        String productType = "testing product";
        return new Product(productId, PRODUCT_NAME, productType, String.format(productDescription, PRODUCT_NAME));
    }
}
