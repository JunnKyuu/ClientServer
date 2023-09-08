import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client2 {

	public static void main(String[] args) {
		ServerIF2 server;
		try {
			server = (ServerIF2)Naming.lookup("NameServer");
			System.out.println("Server's answer: " + server.printName());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
