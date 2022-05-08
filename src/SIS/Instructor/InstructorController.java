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

    public InstructorController(String name, String id, String username, String password){
        model = new InstructorModel(name,id,username,password);
        view = new InstructorView();
    }


    public void getView(){
       view.getView();
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