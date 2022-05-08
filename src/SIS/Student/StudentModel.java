package SIS.Student;

import SIS.Course;
import SIS.Model;

import java.util.ArrayList;

//TODO:
//write fn to parse registered courses from a text file Course Number; Course Name; Number of Credits
// there's a file containing data for all avlbl courses including grades, registered students etc
// when student registers for course go to this file and get his grade

public class StudentModel extends Model {
    private String major =  "N/A";
    private double GPA = -1;
    ArrayList<Course> StudentCourse = new ArrayList<Course>();

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

    //implement:
    public void registerCourse(){

    }
    //implement:
    public ArrayList<Course> getRegisteredCourses(){
        return  StudentCourse;
    }
}