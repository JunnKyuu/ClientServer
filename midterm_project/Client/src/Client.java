import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		ServerIF server;
		server = (ServerIF) Naming.lookup("Server");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Scanner scanner = new Scanner(System.in);
		String token = cLogin(server, scanner);
        if (token != null) {
            System.out.println("Login successful");
            try {
    			while(true) {
    				printMenu();
    				String sChoice = reader.readLine().trim();
    				System.out.println("\n");
    				switch(sChoice) {
    				case "1":
    					showSelect(server.getSelect("students"));
    					break;
    				case "2":
    					System.out.println("studentId: ");
    					String studentId = reader.readLine().trim();
    					showSelect(server.getIdSelect("students", studentId));
    					break;
    				case "3":
    					server.getInsert("students");
    					break;
    				case "4":
    					server.getDelete("students");
    					break;
    				case "5":
    					showSelect(server.getSelect("courses"));
    					break;
    				case "6":
    					System.out.println("courseId: ");
    					String courseId = reader.readLine().trim();
    					showSelect(server.getIdSelect("courses", courseId));
    					break;
    				case "7":
    					server.getInsert("courses");
    					break;
    				case "8":
    					server.getDelete("courses");
    					break;
    				case "x":
    					System.out.println("Exit!!!");
    					return;
    				default:
    					System.out.println("Invalid Choice!!!");
    				}
    			}
    		} 
    		catch (RemoteException e) { e.printStackTrace(); }
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }		
	}

	private static String cLogin(ServerIF server, Scanner scanner) throws RemoteException {
		System.out.println("--------------- LOGIN ---------------\n");
		System.out.print("Username: ");
        String userId = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        String token = server.login(userId, password);
		return token;
	}
	
	private static void printMenu() {
		String menu = 
				"--------------- MENU ---------------\n" +
				"1. Show All Students Data\n" +
				"2. Show One Student Data\n" +
				"3. Add Student Data\n" + 
				"4. Delete Student Data\n" +
				"5. Show All Courses Data\n" +
				"6. Show One Course Data\n" +
				"7. Add Course Data\n" +
				"8. Delete Course Data\n" +
				"x. Exit";
		System.out.println(menu);
    }
	
	private static void showSelect(ArrayList<String> dataList) {
		if(dataList.isEmpty()) {
			System.out.println("***Data is empty***");
		} else {
			System.out.println("***Data from Mysql***\n");
			for (String data : dataList) {
	            System.out.println(data);
	        }
		}
	}
}