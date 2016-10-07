package ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class TxCoordImpl extends UnicastRemoteObject implements TxCoord {
    private TxAccount txAccountA;
    private TxAccount txAccountB;
    private TxAccount txAccountC;

    public TxCoordImpl(TxAccount txa, TxAccount txb, TxAccount txc) throws RemoteException {
        txAccountA = txa;
        txAccountB = txb;
        txAccountC = txc;
    }

    @Override
    public void prepareAll() throws RemoteException {
        txAccountA.prepare();
        txAccountB.prepare();
        txAccountC.prepare();
    }

    @Override
    public void commitAll() throws RemoteException {
        txAccountA.commit();
        txAccountB.commit();
        txAccountC.commit();
    }

    @Override
    public void rollbackAll() throws RemoteException {
        txAccountA.rollback();
        txAccountB.rollback();
        txAccountC.rollback();
    }

}
