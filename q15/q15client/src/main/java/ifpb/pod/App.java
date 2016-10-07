package ifpb.pod;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws RemoteException, NotBoundException, InterruptedException {

        Registry registry = LocateRegistry.getRegistry("q15accountmanager", 10004);
        AccountManager am =  (AccountManager) registry.lookup("AccountManager");

        try {
            am.transfer("A", "B", 100f);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Thread.sleep(15000);

        try {
            am.transfer("C", "B", 500f);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Thread.sleep(15000);

        try {
            am.transfer("C", "A", 300f);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        Thread.sleep(15000);

        try {
            am.transfer("B", "A", 1000f);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
}
