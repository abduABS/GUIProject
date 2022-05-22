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
    public CourseInfoController(){
        model = new CourseInfoModel();
        view = new CourseInfoView(this);
    }
    public CourseInfoController(CourseInfoModel m, CourseInfoView v){
       model = m;
       view = v;
    }

//    public CourseInfoController(Course course){
//        model = new CourseInfoModel(course);
//        view = new CourseInfoView(this);
//    }

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

    public void courseInfo() {
        JFrame frame = new JFrame();

    }

    public void addCourse() {
        if (model.getCourses().size() <= 3) {
            JTextField nameField = new JTextField();
            JTextField numberField = new JTextField();
            JTextField hoursField = new JTextField();
            Object[] panel = {"Course Name:", nameField,
                    "Course Number:", numberField,
                    "Course Hours:", hoursField};
            int option = JOptionPane.showConfirmDialog(null, panel, "Add new Course", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                Course course = new Course(nameField.getText(), numberField.getText(), Integer.parseInt(hoursField.getText()));
                model.getCourses().add(course);
                DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
                tableModel.addRow(new Object[]{course.getName(), course.getNumber()});
                view.getFrame().validate();
            }
        }
    }

    public void changePass() {
        JTextField newField = new JPasswordField();
        JTextField confirmField = new JPasswordField();
        Object[] panel = {"New Password:", newField,
                "Confirm Password:", confirmField};
        int option = JOptionPane.showConfirmDialog(null, panel, "Change Password", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            if(newField.getText().equals(confirmField.getText())){
                JOptionPane.showConfirmDialog(null, "Password Changed Successfully","Password Status",JOptionPane.DEFAULT_OPTION);
                getModel().setPassword(newField.getText());
            }
            else{
                JOptionPane.showConfirmDialog(null, "Passwords do not match","Password Status",JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public void changeName() {
        JTextField nameField = new JTextField();
        Object[] panel = {"New Name:", nameField};
        int option = JOptionPane.showConfirmDialog(null, panel, "Change Name", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            JOptionPane.showConfirmDialog(null, "Name Changed Successfully","Name Status",JOptionPane.DEFAULT_OPTION);
            getModel().setName(nameField.getText());
        }
    }
}