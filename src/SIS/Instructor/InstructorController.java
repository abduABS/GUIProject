package SIS.Instructor;

import SIS.Controller;
import SIS.Model;
import SIS.View;

public class InstructorController extends Controller {
    private InstructorModel model;
    private InstructorView view;
    public InstructorController(){
        model = new InstructorModel();
        view = new InstructorView();
    }
    public InstructorController(InstructorModel m, InstructorView v){
       model = m;
       view = v;
    }

    public InstructorController(String name, String id, String username, String password, String dept){
        model = new InstructorModel(name,id,username,password,dept);
        view = new InstructorView();
    }

    public void getView(){
       view.getView();
    }


    public View view() {
        return (InstructorView)view;
    }

    public Model getModel() {
        return (InstructorModel)model;
    }

    public void setModel() {

    }
}