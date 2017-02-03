package product_demo;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.VerificationReports;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(PactRunner.class)
@Provider("product-provider-demo")
@PactFolder("../consumer-service/target/pacts")
@VerificationReports({"console", "markdown"})
public class ProviderJunitContractTest {
    @TestTarget
    public final Target target = new HttpTarget(8080);

    @BeforeClass
    public static void setUpProvider() {

    }

    @State("test demo first state")
    public void demoState() {
        System.out.println("Provider is in demo state...");
    }
}
