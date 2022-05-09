package SIS.Instructor;
import SIS.*;
import SIS.Course;

import java.util.ArrayList;

public class InstructorModel extends Model {
    private String department;
    private ArrayList<Course> courses = new ArrayList<>();

    public InstructorModel() {
        super();
        this.department = "N/A";
        this.courses = null;
    }

    public InstructorModel(String name, String ID, String username, String password, String dept) {
        super(name, ID, username, password);
        this.department = dept;
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

    public void addCourse(Course c){
        courses.add(c);
    }

    public void printCourses(){
        for (Course c :
                courses) {
            System.out.println(c.getName());
        }
    }
    @Override
    public String toString() {
        return (super.toString());
    }
}