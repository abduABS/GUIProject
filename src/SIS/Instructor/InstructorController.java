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