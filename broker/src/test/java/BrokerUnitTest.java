import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Constants;
import service.broker.LocalBrokerService;

import org.junit.*;
import static org.junit.Assert.assertNotNull;

public class BrokerUnitTest {
    private static Registry registry = null;

    @BeforeClass
    public static void setup() {
        try {
            registry = LocateRegistry.createRegistry(1099);
            BrokerService brokerService = (BrokerService) UnicastRemoteObject.exportObject(new LocalBrokerService(), 0);
            registry.bind(Constants.BROKER_SERVICE, brokerService);
        } catch (Exception e) {
            System.out.println("Bind Trouble: " + e);
        }
    }

    @Test
    public void connectionTest() throws Exception {
        BrokerService service = (BrokerService) registry.lookup(Constants.BROKER_SERVICE);

        assertNotNull(service);
    }

    @Test
    public void generateQuotesTest() throws Exception {
        BrokerService service = (BrokerService) registry.lookup(Constants.BROKER_SERVICE);
        ClientInfo ci = new ClientInfo("Niki Collier", ClientInfo.FEMALE, 49, 1.5494, 80, false, false);
        assertNotNull(service.getQuotations(ci));
    }
}