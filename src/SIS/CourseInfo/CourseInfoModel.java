package SIS.CourseInfo;

import SIS.Course;
import SIS.Model;

import java.util.ArrayList;

public class CourseInfoModel extends Model {

    ArrayList<Course> courses;
    public CourseInfoModel() {
        super();
        this.courses = new ArrayList<Course>();
    }
    
//    public CourseInfoModel(Course course) {
//        this.courses = course;
//    }

    public ArrayList<Course> getCourses() {
    	return courses;
    }
    
}