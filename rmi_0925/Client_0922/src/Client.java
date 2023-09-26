import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args) throws NotBoundException, IOException {
		ServerIF server;
		
		BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			server = (ServerIF) Naming.lookup("Server");
			System.out.println("*************** MENU ***************");
			System.out.println("1. List Students");
			System.out.println("2. List Courses");

			String sChoice = objReader.readLine().trim();
			
			if(sChoice.equals("1")) {
				System.out.println("Server's answer: " + server.getAllStudent());
			}
			
			else if(sChoice.equals("2")) {
				System.out.println("Server's answer: " + server.getAllCourse());
			}		
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
