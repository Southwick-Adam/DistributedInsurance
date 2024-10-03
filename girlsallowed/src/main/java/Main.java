import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import service.girlsallowed.GAQService;
import service.core.QuotationService;
import service.core.RegisterService;
import service.core.Constants;

public class Main {
    public static void main(String[]args) {

        QuotationService gaqService = new GAQService();
        try {
            
            Registry registry = LocateRegistry.getRegistry("broker", 1099);
            System.out.println("Joined Registry");

            // Create the Remote Object
            QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(gaqService,0);
            String name = Constants.GIRLS_ALLOWED_SERVICE;

            //get Resister object
            RegisterService regService = (RegisterService) registry.lookup(Constants.REGISTER_SERVICE);
            regService.RegBind(quotationService, name);

        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}