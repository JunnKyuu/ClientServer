import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS", "root", "leejunkyu87");
			System.out.println("***Mysql Connected***\n");
	    	Log.TraceLog("Mysql Connected");
		} 
		catch (ClassNotFoundException e) { System.out.println("Mysql Driver Error"); e.printStackTrace();} 
		catch (SQLException e) { System.out.println("Mysql Connection Error"); e.printStackTrace(); }
		return connection;
	}
}
