/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in MyungJi University 
 */

package Components.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StudentComponent {
	protected ArrayList<Student> vStudent;
	
	public StudentComponent(String sStudentFileName) throws FileNotFoundException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(sStudentFileName));
		this.vStudent = new ArrayList<Student>();
		while (bufferedReader.ready()) {
			String stuInfo = bufferedReader.readLine();
			if (!stuInfo.equals("")) this.vStudent.add(new Student(stuInfo));
		}
		bufferedReader.close();
	}
	public ArrayList<Student> getStudentList() {
		return vStudent;
	}
	public void setvStudent(ArrayList<Student> vStudent) {
		this.vStudent = vStudent;
	}
	public boolean isRegisteredStudent(String sSID) {
		for (int i = 0; i < this.vStudent.size(); i++) {
			if (((Student) this.vStudent.get(i)).match(sSID)) return true;
		}
		return false;
	}
	// 파일에 학생 정보 저장
	public void registerStudentToFile(Student student, String sStudentFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sStudentFileName, true))) {
        	writer.newLine();
        	writer.newLine();
        	writer.newLine();
        	writer.write(student.getString());
        	writer.newLine();
        }
    }
	// 파일에 학생 정보 업데이트를 저장
	public void updateStudentFile(ArrayList<Student> studentList, String sStudentFileName) throws IOException {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(sStudentFileName))) {
	        for (Student student : studentList) {
	            writer.newLine();
	            writer.newLine();
	            writer.write(student.getString());
	            writer.newLine();
	            writer.newLine();
	        }
	    }
	}
}
