import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		ServerIF server;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while(true) {
				server = (ServerIF) Naming.lookup("Server");
				printMenu();
				String sChoice = reader.readLine().trim();
				switch(sChoice) {
				case "1":
					showSelect(server.getSelect());
					break;
				case "2":
					server.getInsert();
					break;
				case "3":
					server.getDelete();
					break;
				case "4":
					server.getUpdate();
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
	}
	
	private static void printMenu() {
		String menu = 
				"--------------- MENU ---------------\n" +
				"1. Show Students Data\n" +
				"2. Add Student Data\n" + 
				"3. Delete Student Data\n" +
				"4. Update Student Data\n" +
				"x. Exit";
		System.out.println(menu);
    }
	
	private static void showSelect(ArrayList<String> studentList) {
		for(int i = 0; i<studentList.size(); i++) {
			System.out.println(studentList.get(i));
		}
	}
}