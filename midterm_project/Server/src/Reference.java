
public class Reference {

}

/*
 * /*
 * import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements ServerIF { 
	
    public DBManager dbManager;

	
	protected Server() throws RemoteException {
		super();
		this.dbManager = new DBManager();
		// TODO Auto-generated constructor stub
	}

	//UnicastRemoteObject -> 마샬린 해주는거
	
	public static void main(String[] args) {
		
	
		try {
			Server server = new Server();
			Naming.rebind("AddServer", server); //이름 등록으로 알려주는거, AddServer라는 거 호출할거고 그 객체는 server라는 뜻
			System.out.println("Server is ready.");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	}
	
	public int add (int a, int b) {
		System.out.println("Server is respose.");
		return a+b;
	}
	
    @Override
    public void sendMessage(String message) throws RemoteException {
    	dbManager.setMessage(message);
        System.out.println("Save Client1 message: " + message);
    }

    @Override
    public String receiveMessage() throws RemoteException {
    	String clinetMessage = dbManager.getMessage();
        if (!clinetMessage.isEmpty()) {
            return clinetMessage;
        } else {
            return "nothing..";
        }
    }

}




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/clientServer?&useSSL=false";

	private final String USER_NAME = "root";
	private final String PASSWORD = "choi2unt@2k)mysql";

	public DBManager() {
		Connection con = null;
		Statement state = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			System.out.println("[MySQL Connection success!]\n");
			state = con.createStatement();

			String sql;
			sql = "select * from message";
			ResultSet rs = state.executeQuery(sql);

			while (rs.next()) {
				String number = rs.getString("message");
				System.out.println(number);
			}
			rs.close();
			state.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (SQLException ex1) {

			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex1) {

			}
		}
	}
	
	public void setMessage(String message) {
		Connection con = null;
		Statement state = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			state = con.createStatement();

			String sql3 = String.format("INSERT INTO clientserver.message (message) VALUES(\"%s\")", message);
			state.executeUpdate(sql3);
			state.close();
			con.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (state != null) {
					state.close();
				}
			} catch (SQLException ex1) {

			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex1) {

			}
		}
	}
	
	public String getMessage() {
	    Connection con = null;
	    Statement state = null;
	    String lastMessage = "";

	    try {
	        Class.forName(JDBC_DRIVER);
	        con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
	        state = con.createStatement();

	        String selectSql = "SELECT * FROM clientserver.message ORDER BY id DESC LIMIT 1";
	        ResultSet rs = state.executeQuery(selectSql);

	        if (rs.next()) {
	            lastMessage = rs.getString("message");
	        }

	        rs.close();
	        state.close();
	        con.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (state != null) {
	                state.close();
	            }
	        } catch (SQLException ex1) {
	            ex1.printStackTrace();
	        }
	        try {
	            if (con != null) {
	                con.close();
	            }
	        } catch (SQLException ex1) {
	            ex1.printStackTrace();
	        }
	    }

	    return lastMessage;
	}



}
*/

