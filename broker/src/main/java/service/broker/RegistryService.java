package service.broker;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.core.RegisterService;

import service.core.QuotationService;

public class RegistryService implements RegisterService{
    
    public void RegBind(QuotationService service, String name) {
        try {
            Registry reg = LocateRegistry.getRegistry(1099);
            reg.bind(name, service);
        } catch (Exception e) {
            System.out.println("Register Service Trouble: " + e);
        } 
    }
}
