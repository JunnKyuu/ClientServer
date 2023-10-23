import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote {
	ArrayList<String> getSelect() throws RemoteException;
	void getInsert() throws RemoteException;
	void getDelete() throws RemoteException;
	void getUpdate() throws RemoteException;
}
