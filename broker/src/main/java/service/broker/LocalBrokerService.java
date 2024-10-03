package service.broker;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;
import java.util.List;

import service.core.BrokerService;
import service.core.ClientInfo;
import service.core.Quotation;
import service.core.QuotationService;


public class LocalBrokerService implements BrokerService {


	public List<Quotation> getQuotations(ClientInfo info) {
		List<Quotation> quotations = new LinkedList<Quotation>();

        try {
            Registry reg = LocateRegistry.getRegistry(1099);
            for (String name : reg.list()) {
                if (name.startsWith("qs-")) {
                    QuotationService service = (QuotationService) reg.lookup(name);
                    quotations.add(service.generateQuotation(info));
                }
            }
        } catch (Exception e) {
            System.out.println("Broker Service Trouble: " + e);
        } 

		return quotations;
	}
}
