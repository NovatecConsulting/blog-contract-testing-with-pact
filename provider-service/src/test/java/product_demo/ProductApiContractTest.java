package product_demo;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Provider("product-service")
@PactBroker(host = "${pactbroker.host}", tags = {"latest"} ,
        authentication = @PactBrokerAuth(token = "${pactbroker.auth.token}"))
@VerificationReports("console")
public class ProductApiContractTest {
    @TestTarget
    public final Target target = new HttpTarget(8090);

    @Before
    public void setUp(){
        System.setProperty("pact.verifier.publishResults", "true");
        System.setProperty("pact.provider.version", "0.0.1");
    }

    @State("Product with id 573 state")
    public void demoStateConsumerOne() {
        System.out.println("Interaction first consumer...");
    }

    @State("Product with id 532 state")
    public void demoStateConsumerTwo() {
        System.out.println("Interaction second consumer...");
    }
}