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

import org.json.simple.JSONArray;

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
			System.out.println("***Mysql Connected***\n");
		} 
		catch (ClassNotFoundException e) { System.out.println("MYQSL DRIVER ERROR"); e.printStackTrace();} 
		catch (SQLException e) { System.out.println("DB CONNECTECTION ERROR"); e.printStackTrace(); }
		return connection;
	}
	
	public ArrayList<String> getSelect(String message) {
		ArrayList<String> dataList = new ArrayList<String>();
		String sql = null;
		if(message.equals("students")) sql = "SELECT * FROM Students";
		if(message.equals("courses")) sql = "SELECT * FROM Courses";
		Connection connection = getConnection();
		Statement pStatement = null;
		ResultSet result = null;
		if(message.equals("students")) {
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
					dataList.add(answer);
				}					
			} 
			catch (Exception e) { System.out.println(e.getMessage()); } 
			finally {
				try { result.close(); pStatement.close(); connection.close(); }
				catch(Exception e) { e.printStackTrace(); }
			}
		}
		
		if(message.equals("courses")) {
			try {
				pStatement = connection.createStatement();
				result = pStatement.executeQuery(sql);
				while(result.next()) {
					String courseId = result.getString("courseId");
					String professor = result.getString("professor");
					String name = result.getString("name");
					String completedCourseList = result.getString("completedCourseList");				
					String answer 
							= "courseId : " + courseId + "\n"
							+ "professor : " + professor + "\n"
							+ "name : " + name + "\n"
							+ "completedCourseList : " + completedCourseList + "\n";
					dataList.add(answer);
				}
			} 
			catch (Exception e) { System.out.println(e.getMessage()); } 
			finally {
				try { result.close(); pStatement.close(); connection.close(); }
				catch(Exception e) { e.printStackTrace(); }
			}
		}		
		System.out.println("***Select Success***");
		return dataList;
	}
	
	public ArrayList<String> getIdSelect(String message, String id) {
	    ArrayList<String> dataList = new ArrayList<String>();
	    String sql = null;

	    // 동적으로 SQL 쿼리를 생성
	    if (message.equals("students")) {
	        sql = "SELECT * FROM Students WHERE studentId = ?";
	    } else if (message.equals("courses")) {
	        sql = "SELECT * FROM Courses WHERE courseId = ?";
	    }

	    Connection connection = getConnection();
	    PreparedStatement pStatement = null;
	    ResultSet result = null;

	    try {
	        pStatement = connection.prepareStatement(sql);
	        pStatement.setString(1, id); // 매개변수를 바인딩
	        result = pStatement.executeQuery();

	        while (result.next()) {
	            // 결과를 가져와서 dataList에 추가
	            String answer = "";
	            if (message.equals("students")) {
	                String studentId = result.getString("studentId");
	                String name = result.getString("name");
	                String department = result.getString("department");
	                String completedCourseList = result.getString("completedCourseList");
	                answer = "studentId : " + studentId + "\n"
	                        + "name : " + name + "\n"
	                        + "department : " + department + "\n"
	                        + "completedCourseList : " + completedCourseList + "\n";
	            } else if (message.equals("courses")) {
	                String courseId = result.getString("courseId");
	                String professor = result.getString("professor");
	                String name = result.getString("name");
	                String completedCourseList = result.getString("completedCourseList");
	                answer = "courseId : " + courseId + "\n"
	                        + "professor : " + professor + "\n"
	                        + "name : " + name + "\n"
	                        + "completedCourseList : " + completedCourseList + "\n";
	            }
	            dataList.add(answer);
	        }
	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    } finally {
	        try {
	            result.close();
	            pStatement.close();
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    System.out.println("***Select Success***");
	    return dataList;
	}
	
	public void getInsert(String message) {
		String sql = null;
		if(message.equals("students")) sql = "INSERT INTO Students(studentId, name, department, completedCourseList) VALUES(?, ?, ?, ?)";
		if(message.equals("courses")) sql = "INSERT INTO Courses(courseId, professor, name, completedCourseList) VALUES(?, ?, ?, ?)";
		Scanner scanner = new Scanner(System.in);
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		if(message.equals("students")) {
			try {
				pStatement = connection.prepareStatement(sql);
				System.out.println("studentId : ");
				String studentId = scanner.nextLine();
				System.out.println("name : ");
				String name = scanner.nextLine();
				System.out.println("department : ");
				String department = scanner.nextLine();
				System.out.println("completedCourseList (in JSON format, separated by commas): ");
				String completedCourseListJson = scanner.nextLine();
		        JSONArray jsonArray = new JSONArray();
		        String[] courseArray = completedCourseListJson.split("[,\\n]");
		        for (String course : courseArray) {
		            String trimmedCourse = course.trim();
		            if (!trimmedCourse.isEmpty()) {
		                jsonArray.add(trimmedCourse);
		            }
		        }
		        
				pStatement.setString(1, studentId);
				pStatement.setString(2, name);
				pStatement.setString(3,department);
				pStatement.setString(4, jsonArray.toString()); // JSON 배열을 문자열로 변환하여 저장
				int count = pStatement.executeUpdate();
				System.out.println(count + " executed");
				} 
			catch (Exception e) { System.out.println(e.getMessage()); }
			finally {
				try { pStatement.close(); connection.close(); }
				catch(Exception e) { e.printStackTrace(); }
			}
		}
		if(message.equals("courses")) {
			try {
				pStatement = connection.prepareStatement(sql);
				System.out.println("courseId : ");
				String courseId = scanner.nextLine();
				System.out.println("professor : ");
				String professor = scanner.nextLine();
				System.out.println("name : ");
				String name = scanner.nextLine();
				System.out.println("completedCourseList (in JSON format, separated by commas): ");
				String completedCourseListJson = scanner.nextLine();
				JSONArray jsonArray = new JSONArray();
		        String[] courseArray = completedCourseListJson.split("[,\\n]");
		        for (String course : courseArray) {
		            String trimmedCourse = course.trim();
		            if (!trimmedCourse.isEmpty()) {
		                jsonArray.add(trimmedCourse);
		            }
		        }

				pStatement.setString(1, courseId);
				pStatement.setString(2, professor);
				pStatement.setString(3, name);
				pStatement.setString(4, jsonArray.toString()); // JSON 배열을 문자열로 변환하여 저장
				int count = pStatement.executeUpdate();
				System.out.println(count + " executed");
				} 
			catch (Exception e) { System.out.println(e.getMessage()); }
			finally {
				try { pStatement.close(); connection.close(); }
				catch(Exception e) { e.printStackTrace(); }
			}
		}
		System.out.println("***Insert Success***");
	}

	public void getDelete(String message) {
		String sql = null;
		if(message.equals("students")) sql = "DELETE FROM Students WHERE studentId=?";
		if(message.equals("courses")) sql = "DELETE FROM Courses WHERE courseId=?";
		Scanner scanner = new Scanner(System.in);
		Connection connection = getConnection();
		PreparedStatement pStatement = null;
		try {
			pStatement = connection.prepareStatement(sql);
			if(message.equals("students")) System.out.println("studentId : ");
			if(message.equals("courses")) System.out.println("courseId : ");
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
		System.out.println("***Delete Success***");
	}
//	
//	public void getUpdate() {
//		String sql = "UPDATE Students SET completedCourseList='000000' WHERE studentId=?";
//		Connection connection = getConnection();
//		Scanner scanner = new Scanner(System.in);
//		PreparedStatement pStatement = null;
//		try {
//			pStatement = connection.prepareStatement(sql);
//			System.out.println("studentId: ");
//			pStatement.setString(1, scanner.next());
//			int count = pStatement.executeUpdate();
//			System.out.println(count + " executed!");
//		} catch (SQLException e) { e.printStackTrace(); }
//		System.out.println("***Update Success***");
//
//	}
}