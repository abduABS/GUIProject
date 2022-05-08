package SIS.Adminstrator;

import SIS.Course;
import SIS.Instructor.InstructorController;
import SIS.Model;
import SIS.Student.StudentController;

import java.util.ArrayList;

public class AdministratorModel extends Model {

    private ArrayList<StudentController> allStudents;
    private ArrayList<InstructorController> allInstructors;

    public AdministratorModel(){
        super();
        allStudents = null;
        allInstructors = null;
    }

    //Removed from the parameters of the constructor below:
    //ArrayList<StudentController> _allStudents,
    //ArrayList<InstructorController> _allInstructors
    //        this.allStudents = _allStudents;
    //        this.allInstructors = _allInstructors;
    public AdministratorModel(String _name, String _ID, String _username,
                              String _password) {
        super(_name, _ID, _username, _password);

    }

    public ArrayList<StudentController> getAllStudents() {
        return allStudents;
    }

    public void setAllStudents(ArrayList<StudentController> allStudents) {
        this.allStudents = allStudents;
    }

    public ArrayList<InstructorController> getAllInstructors() {
        return allInstructors;
    }

    public void setAllInstructors(ArrayList<InstructorController> allInstructors) {
        this.allInstructors = allInstructors;
    }
}