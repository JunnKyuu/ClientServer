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
	
	public void saveName (String str) {
		System.out.println("Server's response !!!");
		System.out.println("Saved!!!");
		name = str;
	}
	
	public String printName () {
		System.out.println("Server's response !!!");
		System.out.println("Print!!!");
		return name;
	}
}
