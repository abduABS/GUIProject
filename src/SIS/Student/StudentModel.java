package SIS.Student;
import SIS.*;

import SIS.Course;

import java.util.ArrayList;

//TODO:
//write fn to parse registered courses from a text file Course Number; Course Name; Number of Credits
// there's a file containing data for all avlbl courses including grades, registered students etc
// when student registers for course go to this file and get his grade

public class StudentModel extends Model {
    private String major =  "N/A";
    private double GPA = -1;
    ArrayList<Course> registeredCourses = new ArrayList<Course>();

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

    public void printCourses(){
        for (Course c :
                registeredCourses) {
            System.out.println(c.getName());
        }
    }

    //implement:
    public void registerCourse(){

    }
    public ArrayList<Course> getRegisteredCourses(){
        return  registeredCourses;
    }
}