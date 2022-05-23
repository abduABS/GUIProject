package SIS.Instructor;

import SIS.Controller;
import SIS.Course;
import SIS.CourseInfo.CourseInfoController;
import SIS.Model;
import SIS.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InstructorController extends Controller{
    private InstructorModel model;
    private InstructorView view;

    public void run(){
        view.getView();
    }
    public InstructorController(){
        model = new InstructorModel();
        view = new InstructorView(this);
    }
    public InstructorController(InstructorModel m, InstructorView v){
       model = m;
       view = v;
    }

    public InstructorController(String name, String id, String username, String password, String dept){
        model = new InstructorModel(name,id,username,password,dept);
        view = new InstructorView(this);
    }

    public void getView(){
       view.getView();
    }


    public InstructorView view() {
        return view;
    }

    public InstructorModel getModel() {
        return model;
    }

    public void setModel() {

    }

    public void courseInfo() {
        JTextField courseField = new JTextField();
        Object[] panel = {"Type course number to show:", courseField};
        int option = JOptionPane.showConfirmDialog(null, panel, "Choose a course", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int flag =0;
            for(int i =0; i < model.getCourses().size(); i++){
                if(model.getCourses().get(i).getNumber().equals(courseField.getText())){
                    flag = 1;
                    CourseInfoController cController = new CourseInfoController(model.getCourses().get(0));
                    cController.getView();
                }
            }
            if(flag == 0){
                JOptionPane.showConfirmDialog(null, "No course was found with the inputted number","Error",JOptionPane.DEFAULT_OPTION);
            }
        }
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
        else{
            JOptionPane.showConfirmDialog(null, "You cannot add more than 3 courses","Error",JOptionPane.DEFAULT_OPTION);
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