import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import service.broker.LocalBrokerService;
import service.core.BrokerService;
import service.broker.RegistryService;
import service.core.RegisterService;

import service.core.Constants;

public class Main {
    public static void main(String[]args) {

        try {
            
            Registry registry = null;
            if (args.length == 0) {
                registry = LocateRegistry.createRegistry(1099);
                System.out.println("Created new Registry");
            } else {
                registry = LocateRegistry.getRegistry(args[0], 1099);
                System.out.println("Joined Registry");
            }

            // Create and bind broker service
            BrokerService brokerService = (BrokerService) UnicastRemoteObject.exportObject(new LocalBrokerService(),0);
            registry.bind(Constants.BROKER_SERVICE, brokerService);

            // Create and bind register service
            RegisterService regService = (RegisterService) UnicastRemoteObject.exportObject(new RegistryService(),0);
            registry.bind(Constants.REGISTER_SERVICE, regService);

            System.out.println("STOPPING SERVER SHUTDOWN");

            while (true) {Thread.sleep(1000); }
        } catch (Exception e) {
            System.out.println("Server Trouble: " + e);
        }
    }
    
}
