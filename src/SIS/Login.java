package SIS;

import SIS.Adminstrator.AdministratorController;
import SIS.CourseInfo.CourseController;
import SIS.CourseInfo.CourseModel;
import SIS.Instructor.InstructorController;
import SIS.Instructor.InstructorModel;
import SIS.Student.StudentController;
import SIS.Student.StudentModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Login {
    public static JFrame frame;
    int numOfUsers = 0;
    static ArrayList<Controller> users = new ArrayList<Controller>();
    static ArrayList<CourseController> courses = new ArrayList<CourseController>();

    static ArrayList<Thread> threads = new ArrayList<Thread>();

    static int flag = 0;
    static int tries = 0;

    public static ArrayList<Controller> getUsers() {
        return users;
    }

    public static ArrayList<CourseController> getCourses() {
        return courses;
    }

    public static void setUsers(ArrayList<Controller> users) {
        Login.users = users;
    }

    public static void startup() throws FileNotFoundException {
        readRegisteredUsers();
        readCoursesFromThisSemester();
        loginScreen();
        updateAllUsers();
    }

    public static void loginScreen(){
        frame = new JFrame();
        frame.setBounds(300, 300, 600, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel(new GridLayout(0,2));
        JLabel username = new JLabel("Username: ");
        JLabel password = new JLabel("Password: ");

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        contentPane.add(username);
        contentPane.add(userField);
        contentPane.add(password);
        contentPane.add(passField);

        JButton signIn = new JButton("Sign in");
        JButton exit = new JButton("Exit");

        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login(userField,passField);
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        contentPane.add(signIn);
        contentPane.add(exit);
        frame.setContentPane(contentPane);
        frame.setVisible(true);
    }
    public static void login(JTextField userField, JPasswordField passField) {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            for (Controller user : users) {
                if (user.getModel().getUsername().compareTo(username) == 0 && user.getModel().getPassword().compareTo(password) == 0) {
                    flag = 1;
//                    user.view().getView();
                    Thread t = new Thread(user,username);
                    if(noDuplicateUser(username)) {
                        threads.add(t);
                        t.start();
                        userField.setText("");
                    }
                }

            }
        passField.setText("");
        if (flag == 0) {
            int option = JOptionPane.showConfirmDialog(null, "Invalid Username/Password", "Error", JOptionPane.DEFAULT_OPTION);

            if (option == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
                if (tries < 2) {
                    tries++;
                } else {
                    int option2 = JOptionPane.showConfirmDialog(null, "Maximum attempts reached", "Error", JOptionPane.DEFAULT_OPTION);
                    if (option2 == JOptionPane.OK_OPTION || option == JOptionPane.CLOSED_OPTION) {
                        System.exit(0);
                    }
                }
            }

        }
    }

    private static boolean noDuplicateUser(String username) {
        for (Thread t :
                threads) {
            if(t.getName().compareTo(username)==0) {
                int option = JOptionPane.showConfirmDialog(null, "User already logged in to the system", "Error", JOptionPane.OK_CANCEL_OPTION);
                return false;
            }
        }
        return true;
    }

    public static void readRegisteredUsers() throws FileNotFoundException {
        String name, id, username, password, type, additional;
        Scanner scan = new Scanner(new File("src/SIS/users.txt"));
        StringTokenizer st = new StringTokenizer(scan.nextLine(), ";");
        while (scan.hasNextLine() && st.hasMoreTokens()) {
            type = st.nextToken();
            name = st.nextToken();
            id = st.nextToken();
            username = st.nextToken();
            password = st.nextToken();
            additional = st.nextToken();
            users.add(createUserObject(name, id, username, password, type, additional));
            if (scan.hasNextLine())
                st = new StringTokenizer(scan.nextLine(), ";");
        }
        scan.close();
    }

    public static Controller createUserObject(String name, String id, String username, String password, String type, String additional) {

        if (type.compareTo("admin") == 0)
            return (new AdministratorController(name, id, username, password));
        if (type.compareTo("student") == 0)
            return (new StudentController(name, id, username, password, additional));
        if (type.compareTo("instructor") == 0)
            return (new InstructorController(name, id, username, password, additional));

        return null;
    }

    public static void readCoursesFromThisSemester() throws FileNotFoundException {
        int numStudents, credits;
        String name, number, dept, instructorId;
        InstructorController instructor = null;
        Scanner scan = new Scanner(new File("src/SIS/spring2022.txt"));
        StringTokenizer st = new StringTokenizer(scan.nextLine(), ";");
        while (scan.hasNextLine() && st.hasMoreTokens()) {
            numStudents = Integer.parseInt(st.nextToken());
            name = st.nextToken();
            number = st.nextToken();
            dept = st.nextToken();
            instructorId = st.nextToken();
            credits = Integer.parseInt(st.nextToken());

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getModel().getId().equals(instructorId)) {
                    instructor = (InstructorController) users.get(i);
                }
            }

            CourseController course = new CourseController(new CourseModel(name, number, credits, dept, instructor));
            StudentController student = null;
            for (int i = 0; i < numStudents; i++) {
                st = new StringTokenizer(scan.nextLine(), ";");
                String sId = st.nextToken();
                double sGrade = Double.parseDouble(st.nextToken());
                course.getModel().getGrades().add(sGrade);
                for (int j = 0; j < users.size(); j++) {
                    if (users.get(j).getModel().getId().equals(sId)) {
                        student = (StudentController) users.get(j);
                    }
                }
                course.getModel().addStudent(student);
            }
            courses.add(course);
            if (scan.hasNextLine())
                st = new StringTokenizer(scan.nextLine(), ";");
        }
        //Test to check if reading courses works or not:
        for (CourseController c : courses) {
            System.out.println(c.getModel().toString());
        }
        scan.close();
    }

    public static void updateAllUsers() {
        for (CourseController c : courses) {
            //first we find and assign the instructor to the courses he teaches:
            for (Controller user : users) {
                if (user instanceof InstructorController) {

                    if (user.getModel().getName().compareTo(c.getModel().getInstructor().getModel().getName()) == 0) {
                        InstructorModel m = (InstructorModel) user.getModel();
                        m.addCourse(c);
                    }
                }
            }

            //then we update the students registered_courses to the courses he is registered in:
            ArrayList<StudentController> students = c.getModel().getStudents();

            for (StudentController student : students) {
                for (Controller user : users) {
                    if (user.getModel().getId().compareTo(student.getModel().getId()) == 0) {
                        StudentModel m = (StudentModel) user.getModel();
                        m.getRegisteredCourses().add(c);
                    }
                }
            }


        }
    }
}
