import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import service.auldfellas.AFQService;
import service.core.QuotationService;
import service.core.RegisterService;
import service.core.Constants;

public class Main {
    public static void main(String[]args) {

        QuotationService afqService = new AFQService();
        try {
            
            Registry registry = LocateRegistry.getRegistry("broker", 1099);
            System.out.println("Joined Registry");

            // Create the Remote Object
            QuotationService quotationService = (QuotationService) UnicastRemoteObject.exportObject(afqService,0);
            String name = Constants.AULD_FELLAS_SERVICE;

            //get Resister object
            RegisterService regService = (RegisterService) registry.lookup(Constants.REGISTER_SERVICE);
            regService.RegBind(quotationService, name);

        } catch (Exception e) {
            System.out.println("Trouble: " + e);
        }
    }
}