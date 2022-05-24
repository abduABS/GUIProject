package SIS.Course;

import SIS.*;
import SIS.Instructor.InstructorController;
import SIS.Student.StudentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CourseController {

    private boolean isAdmin = false;
    private CourseModel model;
    private CourseView view;

    public CourseController(CourseModel courseModel) {
        model = courseModel;
        view = new CourseView(this);
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public CourseController(CourseModel m, CourseView v) {
        model = m;
        view = v;
    }

    public void changeGrade() {
        JTextField iDField = new JTextField();
        JTextField gradeField = new JTextField();
        Object[] panel = {"Student ID:", iDField, "New Grade:", gradeField};
        int option = JOptionPane.showConfirmDialog(null, panel, "Change Student Grade", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            int flag = 0;
            for (int i = 0; i < model.getStudents().size(); i++) {
                if (model.getStudents().get(i).getModel().getId().equals(iDField.getText())) {
                    flag = 1;
                    model.getGrades().set(i, Double.parseDouble(gradeField.getText()));
                    DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
                    tableModel.setValueAt(Double.parseDouble(gradeField.getText()), i + 4, 2);
                }
            }
            if (flag == 0) {
                JOptionPane.showConfirmDialog(null, "No course was found with the inputted number", "Error", JOptionPane.DEFAULT_OPTION);
            }
            view.getFrame().validate();
        }
    }

    public void loadCourse() {
        JFileChooser chooser = new JFileChooser("f:");
        int r = chooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            Scanner scan = null;
            File file = new File(chooser.getSelectedFile().getAbsolutePath());

            try {
                scan = new Scanner(file);
                scan.useDelimiter(";|\n");
                while(scan.hasNextLine()){
                    int studentNum = scan.nextInt();
                    String cName = scan.next();
                    model.setName(cName);
                    String cNumber = scan.next();
                    model.setNumber(cNumber);
                    String cDepartment = scan.next();
                    model.setNumber(cDepartment);
                    String instructorId = scan.next();
                    InstructorController instructor = null;
                    for (int i = 0; i < Main.getUsers().size(); i++) {
                        if (Main.getUsers().get(i).getModel().getId().equals(instructorId)) {
                            instructor = (InstructorController) Main.getUsers().get(i);
                        }
                    }
                    model.setInstructor((InstructorController) instructor);
                    int cCredits = scan.nextInt();
                    model.setCredits(cCredits);
                    CourseModel course = new CourseModel(cName,cNumber,cCredits,cDepartment,instructor);
                    Double cGrade = null;
                    for (int i = 0; i < studentNum; i++) {
                        String studentId = scan.next();
                        System.out.println("ID: " + studentId);
                        for (int j = 0; j < Main.getUsers().size(); j++) {
                            if (Main.getUsers().get(j).getModel().getId().equals(studentId)) {
                                StudentController student = (StudentController) Main.getUsers().get(j);
                                course.addStudent(student);
                            }
                        }
                        cGrade = scan.nextDouble();
                        course.getGrades().add(cGrade);
                    }
                }

                DefaultTableModel tableModel = (DefaultTableModel) view.getTable().getModel();
                tableModel.setRowCount(4);
                ArrayList<ArrayList<Object>> objects = new ArrayList<ArrayList<Object>>();
                ArrayList<Object> header = new ArrayList<Object>();
                header.add("Name");
                header.add(model.getInstructor().getModel().getName());
                header.add("ID");
                header.add(model.getInstructor().getModel().getId());
                header.add("Department");
                header.add(model.getInstructor().getModel().getDepartment());
                objects.add(header);

                ArrayList<Object> header2 = new ArrayList<Object>();
                header2.add("Term");
                header2.add("Spring 2022");
                objects.add(header2);

                ArrayList<Object> header3 = new ArrayList<Object>();
                header3.add("Course");
                header3.add(model.getNumber());
                header3.add(model.getName());
                objects.add(header3);

                ArrayList<Object> header4 = new ArrayList<Object>();
                header4.add("ID");
                header4.add("Name");
                header4.add("Grade");
                objects.add(header4);

                //Getting Courses Information
                for (int i = 0; i < model.getStudents().size(); i++) {
                    ArrayList<Object> cTable = new ArrayList<Object>();
                    cTable.add(model.getStudents().get(i).getModel().getId());
                    cTable.add(model.getStudents().get(i).getModel().getName());
                    cTable.add(model.getGrades().get(i));
                    objects.add(cTable);
                }


                //Converting the ArrayList to a 2D Object array
                Object[][] obArr = new Object[objects.size()]
                        [objects.get(0).size()];
                for (int i1 = 0; i1 < objects.size(); i1++) {
                    for (int j = 0; j < objects.get(i1).size(); j++) {
                        obArr[i1][j] = objects.get(i1).get(j);
                    }
                }
                tableModel.setRowCount(obArr.length);
                tableModel.setColumnCount(6);
                for(int i =0; i < obArr.length;i++){
                    for(int j =0; j < obArr[i].length; j++){
                        tableModel.setValueAt(obArr[i][j],i,j);
                    }
                }

                view.getFrame().validate();


            } catch (FileNotFoundException e1) {
                JOptionPane.showConfirmDialog(null, "File not found","Error",JOptionPane.DEFAULT_OPTION);

            } finally {
                if (scan != null)
                    scan.close();
            }

        }
    }

    public void saveCourse() {
        JFileChooser chooser = new JFileChooser("f:");
        int r = chooser.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            File file = new File(chooser.getSelectedFile().getAbsolutePath());
            try {
                PrintWriter fw = new PrintWriter(new FileOutputStream(file));
                fw.print(model.getGrades().size() + ";");
                fw.print(model.getName() + ";");
                fw.print(model.getNumber() + ";");
                fw.print(model.getDepartment() + ";");
                fw.print(model.getInstructor().getModel().getId() + ";");
                fw.print(model.getCredits());
                for (int i = 0; i < model.getStudents().size(); i++) {
                    fw.print("/n" + model.getStudents().get(i).getModel().getId() + ";");
                    fw.print(model.getGrades().get(i));
                }
                fw.close();
            } catch (FileNotFoundException e1) {
                JOptionPane.showConfirmDialog(null, "No course was found with the inputted number","Error",JOptionPane.DEFAULT_OPTION);

            }
        }
    }

    public void getView() {
        view.getView();
    }


    public CourseView view() {
        return  view;
    }

    public CourseModel getModel() {
        return  model;
    }

    public void setModel(CourseModel model) {
        this.model = model;
    }


}