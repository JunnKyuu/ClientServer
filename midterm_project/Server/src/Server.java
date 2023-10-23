import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONArray;

public class Server extends UnicastRemoteObject implements ServerIF {
	private static final long serialVersionUID = 1L;

    protected Server() throws RemoteException { super(); }

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Naming.rebind("Server", server);
            System.out.println("Server is ready!!");
        	Log.TraceLog("Server Connected");
        } catch (RemoteException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<String> getSelect(String message) {
    	DAO dao;
    	dao = new DAO(); 
		return dao.getSelect(message);
    }
    
    public ArrayList<String> getIdSelect(String message, String id) {
    	DAO dao;
    	dao = new DAO(); 
		return dao.getIdSelect(message, id);
    }
    
    public void getInsert(String message) {
    	DAO dao;
    	dao = new DAO();
    	dao.getInsert(message);

    }
    
    public void getDelete(String message) {
    	DAO dao;
    	dao = new DAO(); 
    	dao.getDelete(message);
    }
    
    public String login(String userId, String password) throws RemoteException {
    	Map<String, String> userIdPassword = DAO.getUserIdPassword();

        if (userIdPassword.containsKey(userId) && userIdPassword.get(userId).equals(password)) {
            String token = generateToken();
            return token;
        }
        return null;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}
