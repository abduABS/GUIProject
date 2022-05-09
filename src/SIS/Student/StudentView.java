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
    private StudentModel model;

    public StudentView(StudentModel _model) {
        model = _model;
    }


    public void getView() {
        JFrame frame = new JFrame();
        frame.setBounds(300, 300, 300, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        //JLabel label = new JLabel("Student");
        //panel.add(label, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout());
        panel.add(btnPanel, BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        JMenu optionsMenu = new JMenu("Options");
        menuBar.add(optionsMenu);
        JMenuItem loadItem = new JMenuItem("Load Course(s)");
        loadItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

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
        header3.add("Courses");
        header3.add("Grade");
        objects.add(header3);


        //TODO: Add courses of a student from a file
        String s = null;

//                StringTokenizer st = new StringTokenizer(s,";\n");
//                int i =1;
//                while(st.hasMoreTokens()) {
//                    ArrayList<Object> ob = new ArrayList<Object>(6);
//                    ob.add(i++);
//                    ob.add(st.nextToken());
//                    ob.add(st.nextToken());
//                    ob.add(st.nextToken());
//                    objects.add(ob);
//                }

        obArr = new Object[objects.size()]
                [objects.get(0).size()];
        for (int i1 = 0; i1 < objects.size(); i1++) {
            for (int j = 0; j < objects.get(i1).size(); j++) {
                obArr[i1][j] = objects.get(i1).get(j);
            }
        }

        JTable table = new JTable(obArr, new String[]{"1", "2", "3", "4", "5", "6"});
        table.setModel(new DefaultTableModel(obArr, new String[]{"1", "2", "3", "4", "5", "6"}) {
            boolean[] columnEditables = new boolean[]{
                    false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JMenuItem addItem = new JMenuItem("Add Course(s)");
        addItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                JTextField nameField = new JTextField();
                JTextField numberField = new JTextField();
                JTextField hoursField = new JTextField();
                Object[] panel = {"Course Name:", nameField,
                        "Course Number:", numberField,
                        "Course Hours:", hoursField,};
                int option = JOptionPane.showConfirmDialog(null, panel, "Add new Course", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    Course course = new Course(nameField.getText(), numberField.getText(), Integer.parseInt(hoursField.getText()));
                    model.getRegisteredCourses().add(course);

                }
            }

        });
        JMenuItem saveItem = new JMenuItem("Save Course(s)");
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser("f:");
                int r = chooser.showSaveDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    File file = new
                            File(chooser.getSelectedFile().getAbsolutePath());
                    try {
                        PrintWriter fw = new PrintWriter(new FileOutputStream(file));
                        //TODO: Save courses from an arraylist
                        fw.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
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
