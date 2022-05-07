package SIS.Student;

import SIS.Controller;

public class StudentController extends Controller {
    private StudentModel model;
    private StudentView view;
    StudentController(){

    }
    StudentController(StudentModel m, StudentView v){
        model = m;
        view = v;
    }
    public void getView(){

    }
    public void setView(){

    }

    @Override
    public void login() {

    }
}