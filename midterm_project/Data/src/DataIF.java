
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface DataIF extends Remote {
	ArrayList<Student> getAllStudent() throws RemoteException, NullDataException;
	ArrayList<Course> getAllCourse() throws RemoteException;
	boolean addStudentRecords(String studentInfo) throws RemoteException;
	boolean deleteStudentRecords(String studentId) throws RemoteException;
	boolean deleteCourseRecords(String courseId) throws RemoteException;
	boolean addCourseRecords(String courseInfo) throws RemoteException;
}
