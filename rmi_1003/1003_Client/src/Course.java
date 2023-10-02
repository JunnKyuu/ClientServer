
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Course implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String courseId;
    protected String professor;
    protected String courseName;
    protected ArrayList<String> completedCoursesList;

    public Course(String inputString) {
        StringTokenizer stringTokenizer = new StringTokenizer(inputString);
    	this.courseId = stringTokenizer.nextToken();
    	this.professor = stringTokenizer.nextToken();
    	this.courseName = stringTokenizer.nextToken();
    	this.completedCoursesList = new ArrayList<String>();
    	while (stringTokenizer.hasMoreTokens()) {
    		this.completedCoursesList.add(stringTokenizer.nextToken());
    	}
    }

    public String getCourseId() {
    	return this.courseId;
    }
    
    public String getProfessor() {
    	return this.professor;
    }
    
    public String getCourseName() {
    	return this.courseName;
    }
    
    public ArrayList<String> getCompletedCourseList() {
    	return this.completedCoursesList;
    }
     
    public String toString() {
        String stringReturn = this.courseId + " " + this.professor + " " + this.courseName;
        for (int i = 0; i < this.completedCoursesList.size(); i++) {
            stringReturn = stringReturn + " " + this.completedCoursesList.get(i).toString();
        }        
        return stringReturn;
    }
}
