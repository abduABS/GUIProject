package SIS;
import SIS.Student.StudentModel;

import java.util.*;
public class Course {
    public int credits;
    public String name;
    public String number;
    public String department;
    public String instructor;
    private ArrayList<String> students = new ArrayList<String>();
    private ArrayList<Double> grades = new ArrayList<Double>();

    public double getStudentGrade(String _id){
        double g = 0;

        return g;
    }

    public Course(String name, String number, int credits) {
        this.name = name;
        this.number = number;
        this.code = code;
    }

    public void printDetails() {
        for (int i = 0; i < grades.size(); i++) {
            System.out.println("Student id: " + students.get(i) + " "
                    + grades.get(i));
        }
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDepartment() {
        return department;
    }

    public String getInstructor() {
        return instructor;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    // setters:
    public void setName(String _n) {
        name = _n;
    }

    public void setNumber(String _n) {
        number = _n;
    }

    public void setDepartment(String _d) {
        department = _d;
    }

    public void setInstructor(String _s) {
        instructor = _s;
    }

    public void addStudent(StudentModel s) {

        students.add(s.getId());
    }

    private void generateCode() {
        code = department + number;
    }

    public String toString() {
        return ("Department: " + department + " Number: " + number + " Instructor: "
                + instructor);
    }

}
