package SIS.CourseInfo;

import SIS.Course;
import SIS.Model;

import java.util.ArrayList;

public class CourseInfoModel extends Model {

    Course course;
    public CourseInfoModel() {
        super();
        course = new Course("Example","EXE",3);
    }


    public CourseInfoModel(Course course) {

    }


}