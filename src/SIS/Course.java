package SIS;
import SIS.Instructor.InstructorController;
import SIS.Instructor.InstructorModel;
import SIS.Student.StudentModel;

import java.util.*;
public class Course {

    private int credits;
    private String name;
    private String number;
    private String department;
    private InstructorController instructor;
    private ArrayList<CourseStudent> students = new ArrayList<CourseStudent>();

    public Double getStudentGrade(String _id){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(_id)) {
                return students.get(i).getGrade();
            }
        }
        return -1.0d;
    }

    public Course(String name, String number, int credits) {
        this.name = name;
        this.number = number;
        this.credits = credits;
    }

    public Course(String name, String number, int credits, String department, InstructorController instructor) {
        this.name = name;
        this.number = number;
        this.credits = credits;
        this.department = department;
        this.instructor = instructor;
    }

    public void printDetails() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println("Student id: " + students.get(i).getId() + " "
                    + students.get(i).getGrade());
        }
    }

    public int getCredits() {
        return credits;
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

    public InstructorController getInstructor() {
        return instructor;
    }

    public ArrayList<CourseStudent> getStudents() {
        return students;
    }

    // setters:
    public void setName(String _n) {
        name = _n;
    }

    public void setNumber(String _n) {
        number = _n;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public void setDepartment(String _d) {
        department = _d;
    }

    public void setInstructor(InstructorController instructor) {
        this.instructor = instructor;
    }


    public void addStudent(CourseStudent student) {

        students.add(student);
    }

    public String toString() {
        return ("Department: " + department + " Number: " + number + " Instructor: "
                + instructor);
    }

}
