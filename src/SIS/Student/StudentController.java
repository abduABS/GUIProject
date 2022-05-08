package SIS.Student;

import SIS.Controller;
import SIS.Model;
import SIS.View;

public class StudentController extends Controller {
    private StudentModel model;
    private StudentView view;


    public StudentController(){
        model = new StudentModel();
        view = new StudentView(model);
    }
    public StudentController(StudentModel m, StudentView v){
        model = m;
        view = v;
    }

    public StudentController(String name, String id, String username, String password){
        model = new StudentModel(name,id,username,password);
        view = new StudentView(model);
    }
    @Override
    public View view() {
        return view;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel() {

    }

}