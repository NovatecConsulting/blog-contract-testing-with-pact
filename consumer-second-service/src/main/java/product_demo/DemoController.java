package product_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private ProductConsumer productConsumer;

    @Autowired
    public DemoController(ProductConsumer productConsumer) {
        this.productConsumer = productConsumer;
    }

    private static final int PRODUCT_DEMO_ID = 214;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public void getProductDemo() {
        productConsumer.getProductInfo(PRODUCT_DEMO_ID);
    }
}
