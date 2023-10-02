
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ServerIF extends Remote {
	ArrayList<Student> getAllStudent() throws RemoteException;
	ArrayList<Course> getAllCourse() throws RemoteException;
	boolean isLogged(String inputId, String inputPw, boolean isLoggedIn) throws RemoteException;
}
