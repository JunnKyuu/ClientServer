import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Data extends UnicastRemoteObject implements DataIF {
	
	protected static StudentList studentList;
	protected static CourseList courseList;
	
	private static final long serialVersionUID = 1L;
	
	protected Data() throws RemoteException {
		super();
	}
	
	public static void main(String[] arg) throws FileNotFoundException, IOException {
		try {
			Data data = new Data();
			Naming.rebind("Data", data);
			System.out.println("Data is ready!!!"); 
			
			studentList = new StudentList("Students.txt");
			courseList = new CourseList("Courses.txt");
		} catch (RemoteException e){
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public ArrayList<Student> getAllStudent() throws RemoteException, NullDataException {
		return studentList.getAllStudentRecords();
	}	
	
	@Override
	public ArrayList<Course> getAllCourse() throws RemoteException {
		return courseList.getAllCourseRecords();
	}
	
	@Override
	public boolean addStudentRecords(String studentInfo) throws RemoteException {
		if(studentList.addStudentRecords(studentInfo)) return true;
		else return false;
	}
	
	@Override
	public boolean deleteStudentRecords(String studentId) throws RemoteException {
		if(studentList.deleteStudentRecords(studentId)) return true;
		else return false;
	}	
	
	@Override
	public boolean addCourseRecords(String courseInfo) throws RemoteException {
		if(courseList.addCourseRecords(courseInfo)) return true;
		else return false;
	}	
	
	@Override
	public boolean deleteCourseRecords(String courseId) throws RemoteException {
		if(courseList.deleteCourseRecords(courseId)) return true;
		else return false;
	}
}