import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server extends UnicastRemoteObject implements ServerIF {
	public DBConnection dbConnection;
    private static final long serialVersionUID = 1L;

    protected Server() throws RemoteException { 
        super();
        this.dbConnection = new DBConnection();
    }

    public static void main(String[] args) throws NotBoundException {
        try {
            Server server = new Server();
            Naming.rebind("Server", server);
            System.out.println("Server is ready!!");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}