package product_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by AAT on 23.01.2017.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
