package ifpb.pod;

import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    private static final int PORT = 10002;
    private static final String HOST_A = "q15accounta.herokuapp.com";
    private static final String HOST_B = "q15accountb.herokuapp.com";
    private static final String HOST_C = "q15accountc.herokuapp.com";

    public static TxAccount getTxAccountA() throws AccessException, RemoteException,
            NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST_A, PORT);
        return (TxAccount) registry.lookup("TxAccount");
    }

    public static TxAccount getTxAccountB() throws AccessException, RemoteException,
            NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST_B, PORT);
        return (TxAccount) registry.lookup("TxAccount");
    }

    public static TxAccount getTxAccountC() throws AccessException, RemoteException,
            NotBoundException {
        Registry registry = LocateRegistry.getRegistry(HOST_C, PORT);
        return (TxAccount) registry.lookup("TxAccount");
    }


    public static void startTxService(TxCoord txCoord) throws RemoteException,
            AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(10003);
        registry.bind("TxCoord", txCoord);
    }

    public static void main(String[] args) throws AccessException,
            RemoteException, NotBoundException, AlreadyBoundException {
        //propriedade para acesso externo ao servidor RMI
//        System.setProperty("java.rmi.server.hostname", "192.168.1.103");
        //localizar a transação de A
        TxAccount txa = getTxAccountA();
        //localizar a transação de B
        TxAccount txb = getTxAccountB();
        //localizar a transação de C
        TxAccount txc = getTxAccountC();
        ;//getTxAccountC();

        //instancia do coordenador
        TxCoord txCoord = new TxCoordImpl(txa, txb, txc);
        //iniciar coordanação de transações
        startTxService(txCoord);
        //log
        System.out.println("Transação Liberada.");
    }

}
