import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class DBConnection {	
	
	public static void main(String[] arg) throws FileNotFoundException, IOException {
		System.out.println("Database is ready!!!"); 
	}
	
	// DB 연결 메서드
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS", "root", "leejunkyu87");
			System.out.println("***MYSQL DB CONNECTED***\n");
		} 
		catch (ClassNotFoundException e) { System.out.println("MYQSL DRIVER ERROR"); e.printStackTrace();} 
		catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }
		return connection;
	}
	
	public ArrayList<String> getSelect() {
		ArrayList<String> studentList = new ArrayList<String>();
		String sql = "SELECT * FROM Students";
		Connection connection = getConnection();
		Statement pStatement = null;
		ResultSet result = null;
		try {
			pStatement = connection.createStatement();
			result = pStatement.executeQuery(sql);
			while(result.next()) {
				String studentId = result.getString("studentId");
				String name = result.getString("name");
				String department = result.getString("department");
				String completedCourseList = result.getString("completedCourseList");				
				String answer 
						= "studentId : " + studentId + "\n"
						+ "name : " + name + "\n"
						+ "department : " + department + "\n"
						+ "completedCourseList : " + completedCourseList + "\n";
				System.out.println(answer);
				studentList.add(answer);
			}
		} 
		catch (Exception e) { System.out.println(e.getMessage()); } 
		finally {
			try { result.close(); pStatement.close(); connection.close(); }
			catch(Exception e) { e.printStackTrace(); }
		}
		return studentList;
	}
	
	public void getInsert() {
		Scanner scanner = new Scanner(System.in);
		String sql = "INSERT INTO Students(studentId, name, department, completedCourseList) VALUES(?, ?, ?, ?)";
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			System.out.println("studentId : ");
			String studentId = scanner.nextLine();
			System.out.println("name : ");
			String name = scanner.nextLine();
			System.out.println("department : ");
			String department = scanner.nextLine();
			System.out.println("completedCourseList : ");
			String completedCourseList = scanner.nextLine();

			pStatement.setString(1, studentId);
			pStatement.setString(2, name);
			pStatement.setString(3,department);
			pStatement.setString(4, completedCourseList);
			int count = pStatement.executeUpdate();
			System.out.println(count + " executed");
			} 
		catch (Exception e) { System.out.println(e.getMessage()); }
		finally {
			try { pStatement.close(); connection.close(); }
			catch(Exception e) { e.printStackTrace(); }
		}	
	}

	public void getDelete() {
		String sql = "DELETE FROM Students WHERE studentId=?";
		Scanner scanner = new Scanner(System.in);
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			System.out.println("studentId: ");
			pStatement.setString(1, scanner.next());
			int result = pStatement.executeUpdate();
			if(result !=0 ) { System.out.println(result + " deleted!"); }
			else { System.out.println("Delete Fail!"); }
		} 
		catch (SQLException e) { e.printStackTrace(); }
		finally {
			if(connection != null) {
				try { connection.close(); }
				catch (SQLException e) { e.printStackTrace(); }
			}
			else if(pStatement != null) {
				try { pStatement.close(); } 
				catch (SQLException e) { e.printStackTrace(); }
			}
		}
	}
	
	public void getUpdate() {
		String sql = "UPDATE Students SET completedCourseList='000000' WHERE studentId=?";
		Connection connection = getConnection();
		Scanner scanner = new Scanner(System.in);
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			System.out.println("studentId: ");
			pStatement.setString(1, scanner.next());
			int count = pStatement.executeUpdate();
			System.out.println(count + " executed!");
		} catch (SQLException e) { e.printStackTrace(); }
	}
}