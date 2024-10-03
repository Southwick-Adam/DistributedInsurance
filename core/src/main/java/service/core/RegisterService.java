package service.core;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RegisterService extends Remote {
	public void RegBind(QuotationService service, String name) throws RemoteException;
}

