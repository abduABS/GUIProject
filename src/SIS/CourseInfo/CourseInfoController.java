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

    public CourseInfoController(Course course) {
        model = new CourseInfoModel(course);
        view = new CourseInfoView(this);
    }

    public CourseInfoController(CourseInfoModel m, CourseInfoView v) {
        model = m;
        view = v;
    }

    public void changeGrade() {
        JTextField iDField = new JTextField();
        JTextField gradeField = new JTextField();
        Object[] panel = {"Student ID:", iDField,
                "New Grade:", gradeField};
        int option = JOptionPane.showConfirmDialog(null, panel, "Change Student Grade", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int flag =0;
            for(int i =0; i < model.getCourse().getStudents().size(); i++){
                if(model.getCourse().getStudents().get(i).getModel().getId().equals(iDField.getText())){
                    flag = 1;
                    model.getCourse().getGrades().set(i,Double.parseDouble(gradeField.getText()));
                }
            }
            if(flag == 0){
                JOptionPane.showConfirmDialog(null, "No course was found with the inputted number","Error",JOptionPane.DEFAULT_OPTION);
            }
            view.getFrame().validate();
        }
    }


    public void getView() {
        view.getView();
    }


    public View view() {
        return (CourseInfoView) view;
    }

    public Model getModel() {
        return (CourseInfoModel) model;
    }

    public void setModel() {

    }


}