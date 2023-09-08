import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF2 extends Remote {
	void saveName(String str) throws RemoteException;
	String printName() throws RemoteException;
}
