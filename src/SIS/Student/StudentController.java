package SIS.Student;

import SIS.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class StudentController extends Controller {

    private boolean isAdmin = false;
    private StudentModel model;
    private StudentView view;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void run() {
        view.getView();
    }

    public StudentController() {
        model = new StudentModel();
        model.setGPA(calGPA(model));
        view = new StudentView(this);
    }

    public StudentController(StudentModel m, StudentView v) {
        model = m;
        model.setGPA(calGPA(model));
        view = v;
    }

    public StudentController(String name, String id, String username, String password, String major) {
        model = new StudentModel(name, id, username, password, major);
        view = new StudentView(this);
    }

    public double calGPA(StudentModel model) {
        double GPA = 0;
        double totalCredits = 0;
        for (int i = 0; i < model.getRegisteredCourses().size(); i++) {
            GPA += model.getRegisteredCourses().get(i).getModel().getStudentGrade(getModel().getId()) * model.getRegisteredCourses().get(i).getModel().getCredits();
            totalCredits += model.getRegisteredCourses().get(i).getModel().getCredits();
        }
        GPA = GPA / totalCredits;
        return (GPA);
    }

    public View view() {
        return (StudentView) view;
    }

    public Model getModel() {
        return (StudentModel) model;
    }

    public void setModel() {

    }

    public void loadCourses() {
        JFileChooser chooser = new JFileChooser("f:");
        int r = chooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            Scanner scan = null;
            File file = new File(chooser.getSelectedFile().getAbsolutePath());
            model.getRegisteredCourses().clear();
            try {
                scan = new Scanner(file);
                scan.useDelimiter(";|\n");
                while (scan.hasNext()) {
                    String number = scan.next();
                    String name = scan.next();
                    int credits = Integer.parseInt(scan.next());
                    for (int j = 0; j < Main.getUsers().size(); j++) {
                        if (Main.getCourses().get(j).getModel().getNumber().equals(number)) {
                            model.getRegisteredCourses().add(Main.getCourses().get(j));
                            model.getRegisteredCourses().get(j).getModel().addStudent(this);
                        }
                    }


                }
                DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
                tableModel.setRowCount(3);
                for (int i = 0; i < model.getRegisteredCourses().size(); i++) {
                    tableModel.addRow(new Object[]{i + 1, model.getRegisteredCourses().get(i).getModel().getName(),
                            model.getRegisteredCourses().get(i).getModel().getNumber(),
                            model.getRegisteredCourses().get(i).getModel().getCredits()});
                }
                model.setGPA(calGPA(model));
                tableModel.addRow(new Object[]{"", "", "", "GPA", model.getGPA()});
                view.getFrame().validate();


            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } finally {
                if (scan != null)
                    scan.close();
            }

        }
    }

    public void saveCourses() {
        JFileChooser chooser = new JFileChooser("f:");
        int r = chooser.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            File file = new File(chooser.getSelectedFile().getAbsolutePath());
            try {
                PrintWriter fw = new PrintWriter(new FileOutputStream(file));
                for (int i = 0; i < model.getRegisteredCourses().size(); i++) {
                    fw.print(model.getRegisteredCourses().get(i).getModel().getNumber() + ";");
                    fw.print(model.getRegisteredCourses().get(i).getModel().getName() + ";");
                    fw.print(model.getRegisteredCourses().get(i).getModel().getCredits() + "\n");
                }
                fw.close();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void addCourse() {
        if (model.getRegisteredCourses().size() <= 5) {
            JTextField nameField = new JTextField();
            JTextField numberField = new JTextField();
            JTextField hoursField = new JTextField();
            Object[] panel = {"Course Number:", numberField};
            int option = JOptionPane.showConfirmDialog(null, panel, "Add new Course", JOptionPane.OK_CANCEL_OPTION);
            int flag =0;
            if (option == JOptionPane.OK_OPTION) {
                for (int j = 0; j < Main.getCourses().size(); j++) {
                    if (Main.getCourses().get(j).getModel().getNumber().equals(numberField.getText())) {
                        flag = 1;
                        model.getRegisteredCourses().add(Main.getCourses().get(j));
                        model.getRegisteredCourses().get(j).getModel().addStudent(this);
                    }
                }
                if(flag != 0){
                    DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
                    tableModel.removeRow(tableModel.getRowCount() - 1);
                    int courseNum = 1 + Integer.valueOf(tableModel.getValueAt(tableModel.getRowCount() - 1, 0).toString());
                    tableModel.addRow(new Object[]{courseNum, model.getRegisteredCourses().get(model.getRegisteredCourses().size() - 1).getModel().getName(),
                            model.getRegisteredCourses().get(model.getRegisteredCourses().size() - 1).getModel().getNumber(),
                            model.getRegisteredCourses().get(model.getRegisteredCourses().size() - 1).getModel().getCredits()});
                    tableModel.addRow(new Object[]{"", "", "", "GPA", model.getGPA()});
                }
                else{
                    JOptionPane.showConfirmDialog(null, "Course was not found", "Error", JOptionPane.DEFAULT_OPTION);
                }

                view.getFrame().validate();
            }

        } else {
            JOptionPane.showConfirmDialog(null, "You cannnot register more than 5 courses", "Error", JOptionPane.DEFAULT_OPTION);
        }
    }

}