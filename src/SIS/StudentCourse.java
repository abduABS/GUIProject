package SIS;

public class StudentCourse extends Course{
    public String id;
    public double grade;

    StudentCourse(){

    }

    StudentCourse(String _id){
        id = _id;
        grade = getStudentGrade(id);
    }

}
