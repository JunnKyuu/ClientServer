import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote {
	ArrayList<String> getSelect(String message) throws RemoteException;
	ArrayList<String> getIdSelect(String message, String studentId) throws RemoteException;
	void getInsert(String message) throws RemoteException;
	void getDelete(String message) throws RemoteException;
	String login(String username, String password) throws RemoteException;
}
