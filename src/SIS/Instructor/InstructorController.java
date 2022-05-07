package SIS.Instructor;

import SIS.Controller;
import SIS.Student.StudentModel;

public class InstructorController extends Controller {
    private InstructorModel model;
    private InstructorView view;
    InstructorController(){

    }
    InstructorController(InstructorModel m, InstructorView v){
       model = m;
       view = v;
    }
    public void getView(){
       view.getView();
    }

    @Override
    public void login() {

    }
}