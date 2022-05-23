package SIS.CourseInfo;

import SIS.Controller;
import SIS.Course;
import SIS.Instructor.InstructorModel;
import SIS.Instructor.InstructorView;
import SIS.Model;
import SIS.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseInfoController extends Controller {
    private CourseInfoModel model;
    private CourseInfoView view;
    public CourseInfoController(Course course){
        model = new CourseInfoModel(course);
        view = new CourseInfoView(this);
    }
    public CourseInfoController(CourseInfoModel m, CourseInfoView v){
       model = m;
       view = v;
    }



    public void getView(){
       view.getView();
    }


    public View view() {
        return (CourseInfoView)view;
    }

    public Model getModel() {
        return (CourseInfoModel)model;
    }

    public void setModel() {

    }


}