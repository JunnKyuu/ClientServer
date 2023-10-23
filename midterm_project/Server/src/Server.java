import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerIF {
	private static final long serialVersionUID = 1L;

    protected Server() throws RemoteException { super(); }

    public static void main(String[] args) throws NotBoundException {
        try {
            Server server = new Server();
            Naming.rebind("Server", server);
            System.out.println("Server is ready!!");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getSelect(String message) {
    	DBConnection db;
    	db = new DBConnection();
		return db.getSelect(message);
    }
    
    public ArrayList<String> getIdSelect(String message, String id) {
    	DBConnection db;
    	db = new DBConnection();
		return db.getIdSelect(message, id);
    }
    
    public void getInsert(String message) {
    	DBConnection db;
    	db = new DBConnection();
		db.getInsert(message);
    }
    
    public void getDelete(String message) {
    	DBConnection db;
    	db = new DBConnection();
		db.getDelete(message);
    }
}