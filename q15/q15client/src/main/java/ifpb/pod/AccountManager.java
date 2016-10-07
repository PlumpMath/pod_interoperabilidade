package ifpb.pod;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface AccountManager extends Remote {
  void transfer(String srcAccount, String destAccount, float value) throws RemoteException;
}
  