package SIS.Student;
import SIS.Model;
import SIS.View;
import SIS.Controller;

public class StudentController extends Controller{
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

    public StudentController(String name, String id, String username, String password, String major){
        model = new StudentModel(name,id,username,password,major);
        view = new StudentView(model);
    }


    public View view() {
        return (StudentView)view;
    }

    public Model getModel() {
        return (StudentModel)model;
    }

    public void setModel() {

    }

}