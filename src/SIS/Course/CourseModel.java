package SIS.Course;

import SIS.Instructor.InstructorController;
import SIS.Model;
import SIS.Student.StudentController;

import java.util.ArrayList;

public class CourseModel extends Model {

    private int credits;
    private String name;
    private String number;
    private String department;
    private InstructorController instructor;
    private ArrayList<StudentController> students = new ArrayList<StudentController>();

    private ArrayList<Double> grades = new ArrayList<Double>();
    public Double getStudentGrade(String _id){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getModel().getId().equals(_id)) {
                return grades.get(i);
            }
        }
        return -1.0d;
    }

    public CourseModel(String name, String number, int credits) {
        this.name = name;
        this.number = number;
        this.credits = credits;
    }

    public CourseModel(String name, String number, int credits, String department, InstructorController instructor) {
        this.name = name;
        this.number = number;
        this.credits = credits;
        this.department = department;
        this.instructor = instructor;
    }

    public void printDetails() {
        for (int i = 0; i < students.size(); i++) {
            System.out.println("Student id: " + students.get(i).getModel().getId() + " "
                    + grades.get(i));
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

    public ArrayList<StudentController> getStudents() {
        return students;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }

    public void setGrades(ArrayList<Double> grades) {
        this.grades = grades;
    }

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


    public void addStudent(StudentController student) {

        students.add(student);
    }

    public String toString() {
        return ("Department: " + department + " Number: " + number + " Instructor: "
                + instructor);
    }
}