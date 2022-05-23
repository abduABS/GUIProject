package SIS.Instructor;
import SIS.*;
import SIS.Course.CourseController;

import java.util.ArrayList;

public class InstructorModel extends Model {
    private String department;
    private ArrayList<CourseController> courses = new ArrayList<>();

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

    public synchronized ArrayList<CourseController> getCourses() {
        return courses;
    }

    public synchronized void setCourses(ArrayList<CourseController> courses) {
        this.courses = courses;
    }

    public synchronized void addCourse(CourseController c){
        courses.add(c);
    }

    public synchronized void printCourses(){
        for (CourseController c :
                courses) {
            System.out.println(c.getModel().getName());
        }
    }
    @Override
    public String toString() {
        return (super.toString());
    }
}