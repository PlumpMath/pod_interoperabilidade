package q15accountb;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws AccessException, RemoteException, AlreadyBoundException {
		//propriedade para acesso externo ao servidor RMI
		//System.setProperty("java.rmi.server.hostname", "192.168.1.101");
	    //disponibilizar conta
	    Account account = new AccountImpl();
	    Registry registry = LocateRegistry.createRegistry(10001);
	    registry.bind("Account", account);

		TxAccount txAccount = (TxAccount) account;
		registry = LocateRegistry.createRegistry(10002);
		registry.bind("TxAccount", txAccount);

		//log
	    System.out.println("Transação Liberada.");
	}
}
