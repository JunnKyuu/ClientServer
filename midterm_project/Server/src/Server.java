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
    
    public ArrayList<String> getSelect() {
    	DBConnection db;
    	db = new DBConnection();
		return db.getSelect();
    }
    
    public void getInsert() {
    	DBConnection db;
    	db = new DBConnection();
		db.getInsert();
    }
    
    public void getDelete() {
    	DBConnection db;
    	db = new DBConnection();
		db.getDelete();
    }
    
    public void getUpdate() {
    	DBConnection db;
    	db = new DBConnection();
		db.getUpdate();
    }
}