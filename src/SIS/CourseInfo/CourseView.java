

package SIS.CourseInfo;


import SIS.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class CourseView extends View {
    private Object[][] obArr;
    private CourseController control;
    private CourseModel model;

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

    public CourseView(CourseController _control) {
        control = _control;
        model = (CourseModel) control.getModel();
    }


    public void getView() {
        frame = new JFrame();
        frame.setBounds(300, 300, 600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton saveBtn = new JButton("Save Course");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.saveCourse();
            }
        });
        JButton loadBtn = new JButton("load Course");
        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                control.loadCourse();
            }
        });

        btnPanel.add(saveBtn);
        btnPanel.add(loadBtn);

        if(!control.isAdmin()){
            JButton gradeBtn = new JButton("Change Grade");
            gradeBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    control.changeGrade();
                }
            });
            btnPanel.add(gradeBtn);
        }


        panel.add(btnPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        JMenuItem saveItem = new JMenuItem("Save Course");
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.saveCourse();
            }
        });

        JMenuItem loadItem = new JMenuItem("Load Course");
        loadItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control.loadCourse();
            }
        });

        optionsMenu.add(saveItem);
        optionsMenu.add(loadItem);

        if(!control.isAdmin()){
            JMenuItem changeItem = new JMenuItem("Change Grade");
            changeItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    control.changeGrade();
                }
            });

            optionsMenu.add(changeItem);
        }




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

        //TODO: Add courses of a Instructor from a file
        //Getting Courses Information
        for (int i = 0; i < model.getStudents().size(); i++) {
            ArrayList<Object> cTable = new ArrayList<Object>();
            cTable.add(model.getStudents().get(i).getModel().getId());
            cTable.add(model.getStudents().get(i).getModel().getName());
            cTable.add(model.getGrades().get(i));
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

        table = new JTable(obArr, new String[]{"1", "2", "3", "4", "5", "6"});
        table.setModel(new DefaultTableModel(obArr, new String[]{"1", "2", "3", "4", "5", "6"}) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table.setTableHeader(null);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);


        frame.setJMenuBar(menuBar);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    public void setView() {

    }


}
