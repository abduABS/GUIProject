package SIS.Student;
import SIS.*;

import SIS.Course.CourseController;

import java.util.ArrayList;


public class StudentModel extends Model {
    private String major =  "N/A";
    private double GPA = -1;
    ArrayList<CourseController> registeredCourses = new ArrayList<CourseController>();

    StudentModel(){
        super();
        major = "N/A";
        GPA = -1;
    }
    public StudentModel(String _major, double _GPA)
    {
        super();
        major = _major;
        GPA = _GPA;
    }

    public StudentModel(String _name, String _id, String _username, String _password)
    {
        super(_name,_id,_username,_password);
    }

    public StudentModel(String _name, String _id, String _username, String _password, String _major)
    {
        super(_name,_id,_username,_password);
        major = _major;
    }

    public StudentModel(String _name, String _id, String _username, String _password, String _major, double _GPA)
    {
        super(_name,_id,_username,_password);
        major = _major;
        GPA = _GPA;
    }


    public String getMajor() {return major;}
    public void setMajor(String _major) { major = _major;}
    public double getGPA() {return GPA;}
    public void setGPA(double _GPA) { GPA = _GPA;}

    public synchronized void printCourses(){
        for (CourseController c :
                registeredCourses) {
            System.out.println(c.getModel().getName());
        }
    }

    //implement:



    public synchronized ArrayList<CourseController> getRegisteredCourses(){
        return  registeredCourses;
    }
}