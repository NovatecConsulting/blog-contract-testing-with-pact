package product_demo;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRestPactRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("product-provider-demo")
@PactFolder("src/test/resources/pacts") //Folder where pact files are stored
@VerificationReports("console")
public class ProductApiContractTest {
    @TestTarget
    public final Target target = new HttpTarget(8090);

    @State("second consumer demo first state")
    public void demoState() {
        System.out.println("Provider is in demo state...");
    }
}