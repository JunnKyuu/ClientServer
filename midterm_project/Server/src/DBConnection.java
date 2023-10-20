import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static void main(String[] args) {
        connectDB();
    }

	static void connectDB() {
		Connection conn = null;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver"); // Mysql Driver 설치 
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS", "root", "leejunkyu87"); // Mysql DB 연결 
            if (conn != null) System.out.println("MYSQL DB CONNECTED");
        } catch (SQLException e) {
        	System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); 
        } catch (ClassNotFoundException e) {
        	System.out.println("MYQSL DRIVER ERROR"); e.printStackTrace();
        }
        finally {
            try { if(conn != null) conn.close(); System.out.println("MYSQL DB DISCONNECTED"); }
            catch (SQLException e) { e.printStackTrace(); }            
        }
	}
}