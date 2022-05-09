package SIS.Instructor;

import SIS.Course;
import SIS.Model;

import java.util.ArrayList;

public class InstructorModel extends Model {
    private String department;
    private ArrayList<Course> courses;

    public InstructorModel() {
        super();
        this.department = "N/A";
        this.courses = null;
    }

    public InstructorModel(String name, String ID, String username, String password, String dept) {
        super(name, ID, username, password);
        this.department = dept;
//        this.department = department;
//        this.courses = courses;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}