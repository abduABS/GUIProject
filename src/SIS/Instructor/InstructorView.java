package SIS.Instructor;

import SIS.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class InstructorView extends View{
    private Object[][] obArr;
    private InstructorController control;
    private InstructorModel model;

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

    public InstructorView(InstructorController _control) {
        control = _control;
        model = (InstructorModel) control.getModel();
    }


    public void getView() {
        frame = new JFrame();
        frame.setBounds(300, 300, 600, 300);


        JPanel panel = new JPanel(new BorderLayout());


        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton courseBtn = new JButton("Course(s) Info");
        courseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.courseInfo();
            }
        });
        JButton addBtn = new JButton("Add Course");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.addCourse();
            }
        });
        JButton passwordBtn = new JButton("Change Password");
        passwordBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.changePass();
            }
        });
        JButton nameBtn = new JButton("Change Name");
        nameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.changeName();
            }
        });

        btnPanel.add(courseBtn);
        btnPanel.add(addBtn);
        btnPanel.add(passwordBtn);
        btnPanel.add(nameBtn);

        panel.add(btnPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        JMenuItem nameItem = new JMenuItem("Change Name");
        nameItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.changeName();
            }
        });

        JMenuItem passwordItem = new JMenuItem("Change Password");
        passwordItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.changePass();
            }
        });

        optionsMenu.add(nameItem);
        optionsMenu.add(passwordItem);

        JMenu coursesMenu = new JMenu("Courses");
        menuBar.add(coursesMenu);
        JMenuItem courseItem = new JMenuItem("View Course");
        nameItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.courseInfo();
            }
        });

        JMenuItem addItem = new JMenuItem("Add Course");
        addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.addCourse();
            }
        });


        coursesMenu.add(courseItem);
        coursesMenu.add(addItem);

        ArrayList<ArrayList<Object>> objects = new ArrayList<ArrayList<Object>>();
        ArrayList<Object> header = new ArrayList<Object>();
        header.add("Name");
        header.add(model.getName());
        header.add("ID");
        header.add(model.getId());
        header.add("Department");
        header.add(model.getDepartment());
        objects.add(header);
        ArrayList<Object> header2 = new ArrayList<Object>();
        header2.add("Term");
        header2.add("Spring 2022");
        objects.add(header2);
        ArrayList<Object> header3 = new ArrayList<Object>();
        header3.add("Courses");
        objects.add(header3);
        ArrayList<Object> header4 = new ArrayList<Object>();
        header4.add("Name");
        header4.add("Number");
        objects.add(header4);


        //Getting Courses Information
        for(int i =0; i < model.getCourses().size(); i++){
            ArrayList<Object> cTable = new ArrayList<Object>();
            cTable.add(model.getCourses().get(i).getModel().getName());
            cTable.add(model.getCourses().get(i).getModel().getNumber());
            objects.add(cTable);
        }


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


        frame.setJMenuBar(menuBar);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void setView() {

    }


}
