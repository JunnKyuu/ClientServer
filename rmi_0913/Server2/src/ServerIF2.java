import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerIF2 extends Remote {
	void saveName(String str) throws RemoteException;
	void printName() throws RemoteException;
}
