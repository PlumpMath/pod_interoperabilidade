package q15accountc;

import java.rmi.RemoteException;

public class AccountImpl implements Account, TxAccount {
	private float transBalance = 0f;
	private float currentBalance = 300f;
	private boolean inTransaction = false;

	@Override
	public void prepare() throws RemoteException {
		transBalance = currentBalance;
		inTransaction = true;
	}

	@Override
	public void commit() throws RemoteException {
		if (inTransaction){
			currentBalance = transBalance;//atribui novo saldo
			inTransaction = false;
			transBalance = 0f;
		}
	}

	@Override
	public void rollback() throws RemoteException {
		if (inTransaction){
			transBalance = 0f;//volta para o saldo anterior
			inTransaction = false;
		}
	}

	@Override
	public void debit(float value) throws RemoteException {
		if (value > this.currentBalance) {
			throw new RemoteException("Saldo insuficiente para realizar débito.");
		}

		if (inTransaction){
			transBalance -= value;
		}
		else {
			throw new RemoteException("Transação não preparada.");
		}
	}

	@Override
	public void credit(float value) throws RemoteException {
		if (inTransaction){
			transBalance += value;
		}
		else {
			throw new RemoteException("Transação não preparada.");
		}
	}

}
