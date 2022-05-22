package SIS;

import SIS.Adminstrator.*;
import SIS.Student.*;
import SIS.Instructor.*;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Controller> users = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    //
    static int flag = 0;
    static int tries = 0;
    
    static String path = new String("C:\\Users\\Uncle Sam\\Desktop\\sthyaVERAT\\4 FUN ya Practice\\GUIProject\\src\\SIS\\");

    public static void main(String[] args) throws FileNotFoundException {

        /*
        Required Files:
        users.txt -> list of registered users and their login info
        spring2022.txt -> list of current sem courses, students enrolled, grades, etc
        since students have to be able to register for courses in future term, we can create a new textfile
        that keeps track of courses available next sem - and still follow the same naming convention 'semester_name.txt'
*/


        //TODO: Figure out the ArrayList functionality

        //TODO: Figure out efficent way to add people to database


        readAllData();
        updateAllUsers();

        //Test to see if the students arraylist of registered courses is updated after reading from the file:
        System.out.println("Student courses");

        for (Controller user : users) {
            if (user instanceof StudentController) {
                System.out.println(user.getModel().toString());
                StudentModel m = (StudentModel) user.getModel();
                m.printCourses();
            }

            if (user instanceof InstructorController){
                System.out.println(user.getModel().toString());
                InstructorModel m = (InstructorModel)user.getModel();
                m.printCourses();
            }
        }


        login();

        //Flag Validation Mechanism
        if (flag == 0) {
            int option = JOptionPane.showConfirmDialog(null, "Invalid Username/Password", "Error", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                if (tries <= 3) {
                    login();
                } else {
                    int option2 = JOptionPane.showConfirmDialog(null, "Maximum attempts reached", "Error", JOptionPane.OK_CANCEL_OPTION);
                    if (option2 == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                }
            }

        }
    }

    public static void updateAllUsers() {
        for (Course c : courses) {
            //first we find and assign the instructor to the courses he teaches:
            for (Controller user : users) {
                if (user instanceof InstructorController) {

                    if (user.getModel().getName().compareTo(c.getInstructor()) == 0) {
                        InstructorModel m = (InstructorModel) user.getModel();
                        m.addCourse(c);
                    }
                }
            }

            //then we update the students registered_courses to the courses he is registered in:
            ArrayList<String> ids = c.getStudents();

            for (String id : ids) {
                for (Controller user : users) {
                    if (user.getModel().getId().compareTo(id) == 0) {
                        StudentModel m = (StudentModel) user.getModel();
                        m.getRegisteredCourses().add(c);
                    }
                }
            }


        }
    }

    public static void login() {
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        Object[] panel = {"User ID:", userField, "User Password: ", passField};
        int option = JOptionPane.showConfirmDialog(null, panel, "Enter Credentials", JOptionPane.OK_CANCEL_OPTION, 1);
        if (option == JOptionPane.OK_OPTION) {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            for (Controller user : users) {
                if (user.getModel().getUsername().compareTo(username) == 0 && user.getModel().getPassword().compareTo(password) == 0) {
                    flag = 1;
                    user.view().getView();
                } else {
                    tries++;
                }
            }

        } else if (option == JOptionPane.CANCEL_OPTION) {
            flag = -1;
        }
    }


    //function to read data from textfiles:
    public static void readAllData() throws FileNotFoundException {
        readRegisteredUsers();
        readCoursesFromThisSemester();


        //TODO:
        //readListOfAllStudents;
        //readListOfAllInstructors;
        //readCoursesFromThisSemester();
    }

    //format for spring2022.txt:
    //numStudents;name;number;department;instructor;credits;
    //IDs;
    //grades;

    //users.txt:
    //format for saved user data:
    //type;name;ID;username;password;additional (note: type can be 'admin' or 'student' or 'instructor')
    //Eg. admin;radi;b00090044;radiriyas;password123;N/A
    //instructor: type;name;ID;username;password;dept
    //student: type;name;ID;username;password;major

    public static void readRegisteredUsers() throws FileNotFoundException {
        String name, id, username, password, type, additional;
        Scanner scan = new Scanner(new File(path + "users.txt"));
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

    //function that creates a user object based on the type of user
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
        String name, number, dept, instructor;
        Scanner scan = new Scanner(new File(path + "spring2022.txt"));
        StringTokenizer st = new StringTokenizer(scan.nextLine(), ";");
        while (scan.hasNextLine() && st.hasMoreTokens()) {
            numStudents = Integer.parseInt(st.nextToken());
            name = st.nextToken();
            number = st.nextToken();
            dept = st.nextToken();
            instructor = st.nextToken();
            credits = Integer.parseInt(st.nextToken());
            Course course = new Course(name, number, credits, dept, instructor);
            st = new StringTokenizer(scan.nextLine(), ";");
            for (int i = 0; i < numStudents; i++) {
                course.addStudent(st.nextToken());
            }
            st = new StringTokenizer(scan.nextLine(), ";");
            for (int i = 0; i < numStudents; i++) {
                course.addGrade(Double.parseDouble(st.nextToken()));
            }
            courses.add(course);
            if (scan.hasNextLine())
                st = new StringTokenizer(scan.nextLine(), ";");
        }
        //Test to check if reading courses works or not:
        for (Course c : courses) {
            System.out.println(c.toString());
        }
        scan.close();
    }
}