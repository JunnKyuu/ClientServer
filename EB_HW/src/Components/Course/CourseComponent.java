/**
 * Copyright(c) 2021 All rights reserved by Jungho Kim in Myungji University
 */
package Components.Course;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Components.Student.Student;

public class CourseComponent {
    protected ArrayList<Course> vCourse;

    public CourseComponent(String sCourseFileName) throws FileNotFoundException, IOException { 	
        BufferedReader bufferedReader  = new BufferedReader(new FileReader(sCourseFileName));       
        this.vCourse  = new ArrayList<Course>();
        while (bufferedReader.ready()) {
            String courseInfo = bufferedReader.readLine();
            if(!courseInfo.equals("")) this.vCourse.add(new Course(courseInfo));
        }    
        bufferedReader.close();
    }
    public ArrayList<Course> getCourseList() {
        return this.vCourse;
    }
    public boolean isRegisteredCourse(String courseId) {
        for (int i = 0; i < this.vCourse.size(); i++) {
            if(((Course) this.vCourse.get(i)).match(courseId)) return true;
        }
        return false;
    }
    // 파일에 수업 정보 저장 
    public void registerCourseToFile(Course course, String sCourseFileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sCourseFileName, true))) {
        	writer.newLine();
        	writer.newLine();
        	writer.newLine();
        	writer.write(course.getString());
        	writer.newLine();
        }
    }
    // 파일에 수업 정보 업데이트를 저장
 	public void updateCourseFile(ArrayList<Course> coursesList, String sCourseFileName) throws IOException {
 	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(sCourseFileName))) {
 	        for (Course course : coursesList) {
 	            writer.newLine();
 	            writer.newLine();
 	            writer.write(course.getString());
 	            writer.newLine();
 	            writer.newLine();
 	        }
 	    }
 	}
}
