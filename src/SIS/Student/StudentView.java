package SIS.Student;

import SIS.Course;
import SIS.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//
public class StudentView extends View{
    private Object[][] obArr;
    private StudentController control;
    private StudentModel model;

    private JFrame frame = null;

    private JTable table = null;

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public StudentView(StudentController _control) {
        control = _control;
        model = (StudentModel) control.getModel();
    }


    public void getView() {
        frame = new JFrame();
        frame.setBounds(300, 300, 600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        //JLabel label = new JLabel("Student");
        //panel.add(label, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton loadBtn = new JButton("Load");
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.loadCourses();
            }
        });
        JButton addBtn = new JButton("Add Course");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.addCourse();
            }
        });
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.saveCourses();
            }
        });

        btnPanel.add(loadBtn);
        btnPanel.add(addBtn);
        btnPanel.add(saveBtn);
        panel.add(btnPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        JMenuItem loadItem = new JMenuItem("Load Course(s)");
        loadItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.loadCourses();
            }
        });

        ArrayList<ArrayList<Object>> objects = new
                ArrayList<ArrayList<Object>>();
        ArrayList<Object> header = new ArrayList<Object>();
        header.add("Name");
        header.add(model.getName());
        header.add("ID");
        header.add(model.getId());
        header.add("Major");
        header.add(model.getMajor());
        objects.add(header);
        ArrayList<Object> header2 = new ArrayList<Object>();
        header2.add("Semester");
        header2.add("Spring 2022");
        objects.add(header2);
        ArrayList<Object> header3 = new ArrayList<Object>();
        header3.add("Courses");
        header3.add("Name");
        header3.add("Number");
        header3.add("Credits");
        header3.add("Grade");
        objects.add(header3);


        //TODO: Add courses of a student from a file
        //Getting Courses Information
        for(int i =0; i < model.getRegisteredCourses().size(); i++){
            ArrayList<Object> cTable = new ArrayList<Object>();
            cTable.add(i+1);
            cTable.add(model.getRegisteredCourses().get(i).getName());
            cTable.add(model.getRegisteredCourses().get(i).getNumber());
            cTable.add(model.getRegisteredCourses().get(i).getCredits());
            cTable.add(model.getRegisteredCourses().get(i).getStudentGrade(model.getId()));
            objects.add(cTable);
        }

        ArrayList<Object> header4 = new ArrayList<Object>();
        header4.add("");
        header4.add("");
        header4.add("");
        header4.add("GPA");
        model.setGPA(control.calGPA(model));
        header4.add(model.getGPA());
        objects.add(header4);

        //Converting the ArrayList to a 2D Object array
        obArr = new Object[objects.size()]
                [objects.get(0).size()];
        for (int i1 = 0; i1 < objects.size(); i1++) {
            for (int j = 0; j < objects.get(i1).size(); j++) {
                obArr[i1][j] = objects.get(i1).get(j);
            }
        }

        table = new JTable(obArr,new String[]{"1","2","3","4","5","6"});
        table.setModel(new DefaultTableModel(obArr, new String[]{"1","2","3","4","5","6"}) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.setTableHeader(null);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JMenuItem addItem = new JMenuItem("Add Course(s)");
        addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

            }

        });
        JMenuItem saveItem = new JMenuItem("Save Course(s)");
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.saveCourses();
            }
        });

        optionsMenu.add(loadItem);
        optionsMenu.addSeparator();
        optionsMenu.add(addItem);
        optionsMenu.addSeparator();
        optionsMenu.add(saveItem);

        frame.setJMenuBar(menuBar);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void setView() {

    }


}
