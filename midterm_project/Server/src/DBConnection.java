import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    
	public DBConnection() {
        Connection conn = null;
        Statement state = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver"); // Mysql Driver 설치 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS", "root", "leejunkyu87"); // Mysql DB 연결 
            System.out.println("MYSQL DB CONNECTED");
            state = conn.createStatement();
            
            String sql = "select * from message";
			ResultSet result = state.executeQuery(sql);

			while (result.next()) {
				String number = result.getString("message");
				System.out.println(number);
			}
			result.close(); state.close(); conn.close();
        } 
        catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }
        catch (ClassNotFoundException e) { System.out.println("MYQSL DRIVER ERROR"); e.printStackTrace(); }
        finally {
        	try { if (state != null) state.close(); } 
        	catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }		
			try { if (conn != null) conn.close(); System.out.println("MYSQL DB DISCONNECTED"); }				
			catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }		
        }
    }
	
	public void setMessage(String message) {
		Connection conn = null;
		Statement state = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS", "root", "leejunkyu87"); // Mysql DB 연결 
			state = conn.createStatement();

			String sql3 = String.format("INSERT INTO clientserver.message (message) VALUES(\"%s\")", message);
			state.executeUpdate(sql3);
			state.close();
			conn.close();
			
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
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex1) {

			}
		}
	}
	
	public String getMessage() {
	    Connection conn = null;
	    Statement state = null;
	    String lMessage = "";

	    try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS", "root", "leejunkyu87"); // Mysql DB 연결 
	        state = conn.createStatement();
	        String selectSql = "SELECT * FROM LMS.message ORDER BY id DESC LIMIT 1";
	        ResultSet result = state.executeQuery(selectSql);

	        if (result.next()) lMessage = result.getString("message");
	        result.close(); state.close(); conn.close();
	    } 
	    catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }
        catch (ClassNotFoundException e) { System.out.println("MYQSL DRIVER ERROR"); e.printStackTrace(); }
        finally {
        	try { if (state != null) state.close(); } 
        	catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }		
			try { if (conn != null) conn.close(); System.out.println("MYSQL DB DISCONNECTED"); }				
			catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }		
        }
	    return lMessage;	    
	}
}