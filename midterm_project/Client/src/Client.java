import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws NotBoundException, IOException {
		ServerIF server;
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			server = (ServerIF) Naming.lookup("Server");
			boolean checkLogin = login(server, objReader);
			while(checkLogin) {
				printMenu();
				String sChoice = objReader.readLine().trim();
				switch(sChoice) {
				case "1":
					showList(server.getAllStudent());
					break;
				case "2":
					showList(server.getAllCourse());
					break;
				case "3":
					addStudent(server, objReader);
					break;
				case "4":
					deleteStudent(server, objReader);
					break;
				case "5":
					addCourse(server, objReader);
					break;
				case "6":
					 deleteCourse(server, objReader);
					break;
				case "7":
					// 수강신청 
					break;
				case "x":
					System.out.println("Exit!!!");
					return;
				default:
					System.out.println("Invalid Choice!!!");
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NullDataException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean login(ServerIF server, BufferedReader objReader) throws RemoteException {
		Scanner sc = new Scanner(System.in);
		System.out.println("--------------- Login ---------------");
		System.out.print("ID: ");
		String inputId = sc.nextLine();
		System.out.print("PW: ");
		String inputPw = sc.nextLine();
		
		if(server.checkId(inputId, inputPw)) return true;
		else return false;
	}
	
	private static void addCourse(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.println("--------------- Course Infortmation --------------- ");
		System.out.println("Course Id: "); String courseId = objReader.readLine().trim();
		System.out.println("Professor: "); String professor = objReader.readLine().trim();
		System.out.println("Course Name: "); String courseName = objReader.readLine().trim();
		System.out.println("Completed Course List: "); String completedCoursesList = objReader.readLine().trim();

		if(server.addCourseRecords(courseId + " " + professor + " " + courseName + " "+ completedCoursesList)) System.out.println("SUCCESS\n");
		else System.out.println("FAIL\n");
	}
	
	private static void deleteCourse(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.println("Course Id: ");
		if(server.deleteCourseRecords(objReader.readLine().trim())) System.out.println("SUCCESS\n");
		else System.out.println("FAIL\n");
	}
	
	private static void addStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.println("--------------- Student Infortmation --------------- ");
		System.out.println("Student Id: "); String studentId = objReader.readLine().trim();
		System.out.println("Student Name: "); String studentName = objReader.readLine().trim();
		System.out.println("Student Department: "); String studentDept = objReader.readLine().trim();
		System.out.println("Student Completed Course List: "); String completedCourses = objReader.readLine().trim();

		if(server.addStudentRecords(studentId + " " + studentName + " " + studentDept + " "+ completedCourses)) System.out.println("SUCCESS\n");
		else System.out.println("FAIL\n");
	}	
	
	private static void deleteStudent(ServerIF server, BufferedReader objReader) throws RemoteException, IOException {
		System.out.println("Student Id: ");
		if(server.deleteStudentRecords(objReader.readLine().trim())) System.out.println("SUCCESS\n");
		else System.out.println("FAIL\n");
	}	
	
	private static void printMenu() {
		String menu = 
				"--------------- MENU ---------------\n" +
				"1. List Students\n" +
				"2. List Courses\n" + 
				"3. Add Student\n" +
				"4. Delete Student\n" +
				"5. Add Course\n" +
				"6. Delete Course\n" +
				"x. Exit";
		System.out.println(menu);
    }
	
	private static void showList(ArrayList<?> dataList) {
		String list = "";
		for(int i=0; i<dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		System.out.println(list);
	}
}
