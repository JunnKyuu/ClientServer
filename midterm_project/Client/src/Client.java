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
			while(true) {
				printMenu();
				String sChoice = objReader.readLine().trim();
				switch(sChoice) {
				case "1":
					showList(server.getAllStudent());
				case "2":
					showList(server.getAllCourse());
				case "x":
					return;
				default:
					System.out.println("Invalid Choice !!!");			
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
		
	private static void printMenu() {
    	System.out.println("*************** MENU ***************");
		System.out.println("1. List Students");
		System.out.println("2. List Courses");
    }
	
	private static void showList(ArrayList<?> dataList) {
		String list = "";
		for(int i=0; i<dataList.size(); i++) {
			list += dataList.get(i) + "\n";
		}
		System.out.println(list);
	}
}
