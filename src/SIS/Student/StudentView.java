package SIS.Student;

import SIS.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//
public class StudentView extends View{
    private Object[][] obArr;
    private StudentController control;
    private StudentModel model;

    private JFrame frame = null;
    private JFrame current = null;
    private JFrame next = null;

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

    public void getView(){

        frame = new JFrame("Welcome to the Student Portal");
        JPanel contentPane = new JPanel(new GridLayout(0,1));
        frame.setBounds(300, 300, 600, 300);

        JButton currentSem = new JButton("View Current Semester Info");
        JButton nextSem = new JButton("View Next Semester Info");
        JLabel temp1 = new JLabel();
        JLabel temp2 = new JLabel();

        contentPane.add(temp1);
        contentPane.add(currentSem);
        contentPane.add(nextSem);
        contentPane.add(temp2);

        currentSem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentSemGUI();
            }
        });

        nextSem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextSemGUI();
            }
        });
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }

    public void currentSemGUI() {
        current = new JFrame();
        current.setBounds(300, 300, 600, 300);

        JPanel panel = new JPanel(new BorderLayout());

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

        ArrayList<ArrayList<Object>> objects = new ArrayList<ArrayList<Object>>();
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


        //Getting Courses Information
        for(int i =0; i < model.getRegisteredCourses().size(); i++){
            ArrayList<Object> cTable = new ArrayList<Object>();
            cTable.add(i+1);
            cTable.add(model.getRegisteredCourses().get(i).getModel().getName());
            cTable.add(model.getRegisteredCourses().get(i).getModel().getNumber());
            cTable.add(model.getRegisteredCourses().get(i).getModel().getCredits());
            cTable.add(model.getRegisteredCourses().get(i).getModel().getStudentGrade(model.getId()));
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
                control.addCourse();
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

        current.setJMenuBar(menuBar);
        current.setContentPane(panel);
        current.setVisible(true);
    }

    public void nextSemGUI(){

    }

    public void setView() {

    }


}
