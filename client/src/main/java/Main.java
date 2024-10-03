import java.rmi.registry.*;
import java.util.List;

import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Constants;
import service.core.Quotation;

public class Main {
    public static void main(String[]args) {

        try {
            Registry registry = LocateRegistry.getRegistry("broker", 1099);
            System.out.println("Joined Registry");
            
            BrokerService service = (BrokerService) registry.lookup(Constants.BROKER_SERVICE);

            ClientInfo ci = new ClientInfo("Niki Collier", ClientInfo.FEMALE, 49, 1.5494, 80, false, false);
        
            List<Quotation> quotesList = service.getQuotations(ci);
            for (Quotation q : quotesList) {
                System.out.println(q.company + ", " + q.reference + ", " + q.price);
            }
            System.out.println("LIST END");

        } catch(Exception e) {
            System.out.println("Client Trouble: " + e);
        }
    }
}
