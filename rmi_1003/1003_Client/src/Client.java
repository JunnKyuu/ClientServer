import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	public static void main(String[] args) throws NotBoundException, IOException {
		ServerIF server;
		server = (ServerIF) Naming.lookup("Server");
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		
		Client main = new Client();
		main.run(server, objReader);
	}
	
	public void run(ServerIF server, BufferedReader objReader) throws NotBoundException, IOException {
		ArrayList<Student> studentData = server.getAllStudent();
		ArrayList<Course> courseData = server.getAllCourse();	
		
		login(server, objReader, studentData, courseData);
	}

	private void login(ServerIF server, BufferedReader objReader, ArrayList<Student> studentData,
			ArrayList<Course> courseData)
			throws MalformedURLException, RemoteException, NotBoundException, IOException {
		
		boolean isLoggedIn = false;
		isLoggedIn = checkLogin(server, studentData, isLoggedIn); 
		
		if(!isLoggedIn) {
	        System.out.println("로그인에 실패했습니다. 학번과 비밀번호를 확인해주세요!!!\n");
		} else {
	        System.out.println("로그인에 성공했습니다!!!\n");
	        while(isLoggedIn) {
	        	try {
	    			showList(objReader, studentData, courseData);		
	    		} catch (RemoteException e) {
	    			e.printStackTrace();
	    		}
	        }
		} // 예외처리로 수정 
	}

	private boolean checkLogin(ServerIF server, ArrayList<Student> studentData, boolean isLoggedIn) throws MalformedURLException, RemoteException, NotBoundException { 
		String inputId;
		String inputPw;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\n******************** LogIn ********************");
        System.out.print("학번 : ");
        inputId = sc.nextLine();
        System.out.print("비밀번호 : ");
        inputPw = sc.nextLine();
       
		return server.isLogged(inputId, inputPw, isLoggedIn);
	}
	
	private void showList(BufferedReader objReader, ArrayList<Student> studentData, ArrayList<Course> courseData)
			throws IOException {
		
		System.out.println("\n******************** MENU ********************");
		System.out.println("1. Students List");
		System.out.println("2. Course List");
		System.out.println("3. Exit");


		String sChoice = objReader.readLine().trim();
		
		if(sChoice.equals("1")) {
			System.out.println("\n****************** Students List ******************");
			for (Student student : studentData) {
			    System.out.println("학번: " + student.studentId + "     " + 
			    					"이름: " + student.getName() + "     " + 
			    					"학과: " + student.getDepartment());
			    System.out.println("수강 강좌번호: " + student.getCompletedCourses() + "\n");
			}
		}
		
		else if(sChoice.equals("2")) {
			System.out.println("\n********************** Course List **********************");
			for (Course course : courseData) {
			    System.out.println("강좌번호: " + course.getCourseId() + "     " + 
    					"교수: " + course.getProfessor() + "     " + 
    					"강좌이름: " + course.getCourseName());
			    if(course.getCompletedCourseList().isEmpty()) {
				    System.out.println("선이수 과목이 없습니다.\n");
			    } else {
				    System.out.println("선이수 과목번호: " + course.getCompletedCourseList() + "\n");
			    }
			}
		}
		
		else if(sChoice.equals("3")) {
			System.out.println("\n프로그램을 종료합니다!!!\n");
			System.exit(0);
		}
	}
}
