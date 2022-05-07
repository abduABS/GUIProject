package SIS.Adminstrator;

import SIS.Course;
import SIS.Model;

import java.util.ArrayList;

public class AdminstratorModel extends Model {
    private String major;
    private int GPA;
    ArrayList<Course> courses;

    public String getMajor() {
        return major;
    }

    public void setMajor(String _major) {
        major = _major;
    }

    public int getGPA() {
        return GPA;
    }

    public void setGPA(int _GPA) {
        GPA = _GPA;
    }
}