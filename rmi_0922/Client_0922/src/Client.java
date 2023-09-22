import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args) throws MalformedURLException, NotBoundException {
		ServerIF server;
		
		try {
			server = (ServerIF) Naming.lookup("Server");
			System.out.println("Server's answer: " + server.getAllStudent());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
