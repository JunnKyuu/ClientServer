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
		server = (ServerIF) Naming.lookup("Server");
		
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Student> studentData = server.getAllStudent();
		ArrayList<Course> courseData = server.getAllCourse();
        boolean isLoggedIn = false;

		Scanner sc = new Scanner(System.in);
        System.out.println("학번과 비밀번호를 입력해주세요");
        System.out.print("학번 : ");
        String inputId = sc.nextLine();
        System.out.print("비밀번호 : ");
        String inputPassword = sc.nextLine();
       
        for(Student student : studentData) {
        	if(student.getStudentId().equals(inputId) && student.getName().equals(inputPassword)) {
                System.out.println("안녕하세요 " + student.getName() + "님 로그인에 성공했습니다!!");
                isLoggedIn = true;
        	}
        }
        
        if(isLoggedIn) {
        	try {
    			System.out.println("*************** MENU ***************");
    			System.out.println("1. List Students");
    			System.out.println("2. List Courses");

    			String sChoice = objReader.readLine().trim();
    			
    			if(sChoice.equals("1")) {
    				System.out.println("Server's answer: ");
    				for (Student student : studentData) {
    				    System.out.println(student);
    				}
    			}
    			
    			else if(sChoice.equals("2")) {
    				System.out.println("Server's answer: ");
    				for (Course course : courseData) {
    				    System.out.println(course);
    				}
    			}		
    		} catch (RemoteException e) {
    			e.printStackTrace();
    		}
        } else {
            System.out.println("로그인에 실패했습니다. 학번과 비밀번호를 확인해주세요!");
        }
	}
}
