package ifpb.pod;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Account extends Remote {
  //debita um determinado valor nesta conta
  void debit(float value) throws RemoteException;
  //credita um determinado valor nesta conta
  void credit(float value) throws RemoteException;
}
