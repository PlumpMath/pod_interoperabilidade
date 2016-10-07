package ifpb.pod;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    private static final int PORT = 10001;
    private static final String HOST_A = "q15accounta.herokuapp.com";
    private static final String HOST_B = "q15accountb.herokuapp.com";
    private static final String HOST_C = "q15accountc.herokuapp.com";

    private static Account getAccountA() throws AccessException,
            RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST_A, PORT);
        return (Account) registry.lookup("Account");
    }

    private static Account getAccountB() throws AccessException,
            RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST_B, PORT);
        return (Account) registry.lookup("Account");
    }

    private static Account getAccountC() throws AccessException,
            RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST_C, PORT);
        return (Account) registry.lookup("Account");
    }

    private static TxCoord getTxCoord() throws AccessException, RemoteException,
            NotBoundException {
        Registry registry = LocateRegistry.getRegistry("q15txcoord.herokuapp.com", 10003);
        return (TxCoord) registry.lookup("TxCoord");
    }

    public static void startAccountManagerService(AccountManager am) throws RemoteException,
            AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(10004);
        registry.bind("AccountManager", am);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, AlreadyBoundException {
        //
        TxCoord tx = getTxCoord();
        Account a = getAccountA();
        Account b = getAccountB();
        Account c = getAccountC();
        //
        AccountManager manager = new AccountManagerImpl(tx, a, b, c);
        //
//        System.setProperty("java.rmi.server.hostname", "192.168.1.103");
        startAccountManagerService(manager);
        //
        System.out.println("Servidor liberado para uso.");
    }

}
