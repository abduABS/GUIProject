package SIS.Instructor;

import SIS.Controller;
import SIS.Main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InstructorController extends Controller{

    private boolean isAdmin = false;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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
                if(model.getCourses().get(i).getModel().getNumber().equals(courseField.getText())){
                    flag = 1;
                    model.getCourses().get(i).setAdmin(isAdmin);
                    model.getCourses().get(i).getView();
                }
            }
            if(flag == 0){
                JOptionPane.showConfirmDialog(null, "No course was found with the inputted number","Error",JOptionPane.DEFAULT_OPTION);
            }
        }
    }

    public void addCourse() {
        if (model.getCourses().size() <= 3) {
            JTextField numberField = new JTextField();
            Object[] panel = {"Course Number:", numberField};
            int option = JOptionPane.showConfirmDialog(null, panel, "Add new Course", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                int flag = 0;
                for (int j = 0; j < Main.getCourses().size(); j++) {
                    if (Main.getCourses().get(j).getModel().getNumber().equals(numberField.getText())) {
                        model.getCourses().add(Main.getCourses().get(j));
                        model.getCourses().get(j).getModel().setInstructor(this);
                        flag = 1;
                    }
                }
                if(flag ==0){
                    JOptionPane.showConfirmDialog(null, "No course exist with such number","Error",JOptionPane.DEFAULT_OPTION);
                }
                else{
                    DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
                    tableModel.addRow(new Object[]{model.getCourses().get(model.getCourses().size() - 1).getModel().getName(), model.getCourses().get(model.getCourses().size() - 1).getModel().getNumber()});
                    view.getFrame().validate();
                }
            }
        }
        else{
            JOptionPane.showConfirmDialog(null, "You cannot add more than 3 courses","Error",JOptionPane.DEFAULT_OPTION);
        }
    }

    public void changePass() {
        JTextField newField = new JPasswordField();
        JTextField confirmField = new JPasswordField();
        Object[] panel = {"New Password:", newField, "Confirm Password:", confirmField};
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