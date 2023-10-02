import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerIF {

	private static DataIF data;
	private static final long serialVersionUID = 1L;
	
	protected Server() throws RemoteException {
		super();
	}
	
	public static void main(String[] args) throws NotBoundException {
		try {
			Server server = new Server();
			Naming.rebind("Server", server);
			System.out.println("Server is ready!!");
			data = (DataIF) Naming.lookup("Data");
		} catch (RemoteException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Student> getAllStudent() throws RemoteException {
		return data.getAllStudent();
	}
	
	public ArrayList<Course> getAllCourse() throws RemoteException {
		return data.getAllCourse();
	}
	
	public boolean isLogged(String inputId, String inputPw, boolean isLoggedIn) throws RemoteException {
		for(Student student : data.getAllStudent()) {
			if(student.getStudentId().equals(inputId) && student.getName().equals(inputPw)) {
                System.out.println(student.getName() + "님이 로그인에 성공했습니다!!");
                isLoggedIn = true;
        	}
		}
		return isLoggedIn;
	}
}
