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
	public ArrayList<Student> getAllStudent() throws RemoteException, NullDataException {
		return data.getAllStudent();
	}
	
	@Override
	public ArrayList<Course> getAllCourse() throws RemoteException {
		return data.getAllCourse();
	}

	@Override
	public boolean addStudentRecords(String studentInfo) throws RemoteException {
		if(data.addStudentRecords(studentInfo)) return true;
		else return false;
	}

	@Override
	public boolean deleteStudentRecords(String studentId) throws RemoteException {
		if(data.deleteStudentRecords(studentId)) return true;
		else return false;
	}
	
	@Override
	public boolean addCourseRecords(String courseInfo) throws RemoteException {
		if(data.addCourseRecords(courseInfo)) return true;
		else return false;
	}
	
	@Override
	public boolean deleteCourseRecords(String courseId) throws RemoteException {
		if(data.deleteCourseRecords(courseId)) return true;
		else return false;
	}
	
	public boolean checkId(String inputId, String inputPw) throws RemoteException, NullDataException {
		boolean check = false;
		for(Student student : data.getAllStudent()) {
			if(inputId.equals(student.getStudentId()) && inputPw.equals(student.getName())) check = true;
		}
		return check;
	}
}
