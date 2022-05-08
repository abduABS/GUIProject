package SIS;
import SIS.Student.StudentModel;

import java.util.*;
public class Course {
    public int credits;
    public String name;
    public int number;
    public String code;
    public String department;
    public String instructor;
    private ArrayList<StudentModel> students = new ArrayList<StudentModel>();
    private ArrayList<Grade> grades = new ArrayList<Grade>();

    public double getStudentGrade(String _id){
        double g = 0;

        return g;
    }

    public Course(String name, String code, int number) {
        this.name = name;
        this.number = number;
        this.code = code;
    }

    public void printDetails() {
        for (int i = 0; i < grades.size(); i++) {
            System.out.println("Student id: " + students.get(i).getId() + " "
                    + grades.get(i));
        }
    }

    public String getName() {
        return name;
    }

    public long getNumber() {
        return number;
    }

    public String getCode() {
        return code;

    }

    public String getDepartment() {
        return department;
    }

    public String getInstructor() {
        return instructor;
    }

    public ArrayList<StudentModel> getStudents() {
        return students;
    }

    // setters:
    public void setName(String _n) {
        name = _n;
    }

    public void setNumber(int _n) {
        number = _n;
    }

    public void setDepartment(String _d) {
        department = _d;
    }

    public void setInstructor(String _s) {
        instructor = _s;
    }

    public void addStudent(StudentModel s) {

        students.add(s);
    }

    private void generateCode() {
        code = department + number;
    }

    public String toString() {
        generateCode();
        return ("Department: " + department + " Number: " + number + " Course code: " + code + " Instructor: "
                + instructor);
    }

}
