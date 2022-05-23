package SIS.Adminstrator;
import SIS.*;
import SIS.Instructor.InstructorController;
import SIS.Student.StudentController;

import java.util.ArrayList;

public class AdministratorModel extends Model {


    public AdministratorModel(){
        super();
    }

    public AdministratorModel(String _name, String _ID, String _username, String _password) {
        super(_name, _ID, _username, _password);
    }

}