import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server2 extends UnicastRemoteObject implements ServerIF2 {
	String name = new String();

	protected Server2() throws RemoteException {
		super();
	}
	
	public static void main(String[] arg) {
		try {
			Server2 server = new Server2();
			Naming.rebind("NameServer", server);
			System.out.println("Server is ready !!!");
		} catch(RemoteException e) {
			e.printStackTrace();
		} catch(MalformedURLException e) {
			e.printStackTrace();
		}
	}
		
	public void saveName(String str) {
		System.out.println("Server's response !!!");

		File file = new File("/Users/junkyulee/Desktop/Study/MyongJi/2학년 2학기/클라이언트서버프로그래밍/code/Server2/src/name.txt");
		
		try {
			FileWriter fileWriter = new FileWriter(file);
	        PrintWriter writer = new PrintWriter(fileWriter);
			writer.write(str);
			writer.close();
			System.out.println("Saved!!!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printName () {
		System.out.println("Server's response !!!");

		String str;
		try {
			str = Files.readString(Paths.get("/Users/junkyulee/Desktop/Study/MyongJi/2학년 2학기/클라이언트서버프로그래밍/code/Server2/src/name.txt"));
			System.out.println("Server's name : " + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
