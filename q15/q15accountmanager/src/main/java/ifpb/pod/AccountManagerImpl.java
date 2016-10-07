package ifpb.pod;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class AccountManagerImpl extends UnicastRemoteObject implements AccountManager {
    private TxCoord txCoord;
    private Account accountA;
    private Account accountB;
    private Account accountC;

    protected AccountManagerImpl(TxCoord tx, Account a, Account b, Account c) throws RemoteException {
        txCoord = tx;
        accountA = a;
        accountB = b;
        accountC = c;
    }

    @Override
    public void transfer(String srcAccount, String destAccount, float value) throws RemoteException {
        //
        float part = value / 4;
        //
        try {
            //preparar a transação
            txCoord.prepareAll();
            //inicializa a operação

            Account src, dest = null;

            //pegando conta de origem
            if ("A".equals(srcAccount)) {
                src = accountA;
            } else if ("B".equals(srcAccount)) {
                src = accountB;
            } else if ("C".equals(srcAccount)) {
                src = accountC;
            } else {
                throw new RemoteException("Conta origem não encontrada.");
            }

            //pegando conta destino
            if ("A".equals(destAccount)) {
                dest = accountA;
            } else if ("B".equals(destAccount)) {
                dest = accountB;
            } else if ("C".equals(destAccount)) {
                dest = accountC;
            } else {
                throw new RemoteException("Conta destino não encontrada.");
            }

            //transferindo
            System.out.println("Transferindo " + value);
            
            src.debit(value);
            dest.credit(value);

            //commit
            txCoord.commitAll();
        } catch (RemoteException e) {
            txCoord.rollbackAll();
            throw new RemoteException(e.getMessage());
        }
    }

}
