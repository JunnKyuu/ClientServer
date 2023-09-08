import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client1 {

	public static void main(String[] args) {
		ServerIF2 server;
		try {
			server = (ServerIF2)Naming.lookup("NameServer");
			String str = new String();
			Scanner sc = new Scanner(System.in);
			str = sc.nextLine();
			server.saveName(str);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
