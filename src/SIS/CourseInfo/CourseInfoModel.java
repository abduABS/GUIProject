package SIS.CourseInfo;

import SIS.Course;
import SIS.Model;

import java.util.ArrayList;

public class CourseInfoModel extends Model {

    Course course;

    public CourseInfoModel(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}