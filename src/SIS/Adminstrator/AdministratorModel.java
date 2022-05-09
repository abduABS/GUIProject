package SIS.Adminstrator;
import SIS.*;
import SIS.Instructor.InstructorController;
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

    @Override
    public String toString() {
        return super.toString();
    }
}