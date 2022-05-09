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

    public static void main(String[] args) throws FileNotFoundException {

        /*
        Required Files:
        users.txt -> list of registered users and their login info
        spring2022.txt -> list of current sem courses, students enrolled, grades, etc
        since students have to be able to register for courses in future term, we can create a new textfile
        that keeps track of courses available next sem - and still follow the same naming convention 'semester_name.txt'


        potential program flow?

        login  -> parse all common data for program - current sem courses, etc,

        if student-> create a student thread -> retrieve grade from arraylist of current sem courses based
            on his id and store in a StudentCourse object

        if instructor -> create instructor thread
                      -> retrieve list of registered courses from arraylist of current sem courses by
                         cross-referencing his name

        if admin -> create admin thread
                 ->
        */


        //TODO: Figure out the ArrayList functionality

        //TODO: Figure out efficent way to add people to database


        readAllData();
//        users.add(new AdministratorController());

        //Login Starts from here
        login();

        //Flag Validation Mechanism
        if(flag == 0)
        {
            int option = JOptionPane.showConfirmDialog(null, "Invalid Username/Password","Error",JOptionPane.OK_CANCEL_OPTION);

            if(option == JOptionPane.OK_OPTION) {
                if(tries <= 3){
                    login();
                }
                else{
                    int option2 = JOptionPane.showConfirmDialog(null, "Maximum attempts reached","Error",JOptionPane.OK_CANCEL_OPTION);
                    if(option2 == JOptionPane.OK_OPTION){
                        System.exit(0);
                    }
                }
            }

        }
    }

    public static void login()
    {
        System.out.println("Hello world!");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        Object[] panel = {"User ID:", userField,"User Password: ", passField};
        int option = JOptionPane.showConfirmDialog(null, panel,"Enter Credentials",JOptionPane.OK_CANCEL_OPTION,1);
        if(option == JOptionPane.OK_OPTION)
        {
            //TODO: add validation for the username/password
            String username = userField.getText();
            String password = new String(passField.getPassword());
            System.out.println("Text Username: " + username);
            System.out.println("Text password: "+ password);

            for (Controller user : users) {
                System.out.println(user.getModel().getUsername());
                System.out.println(user.getModel().getPassword());
                if (user.getModel().getUsername().compareTo(username) == 0 && user.getModel().getPassword().compareTo(password) == 0) {
                    System.out.println("Validation true");
                    flag = 1;
                    user.view().getView();
                } else {
                    tries++;
                }
            }

        }
        else if(option == JOptionPane.CANCEL_OPTION){
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
        String name, id, username, password,type, additional;
        Scanner scan = new Scanner(new File("C:\\Users\\Uncle Sam\\Desktop\\sthyaVERAT\\4 FUN ya Practice\\GUIProject\\src\\SIS\\users.txt"));
        StringTokenizer st = new StringTokenizer(scan.nextLine(), ";");
        while(scan.hasNextLine() && st.hasMoreTokens()){
            type = st.nextToken();
            name = st.nextToken();
            id = st.nextToken();
            username = st.nextToken();
            password = st.nextToken();
            additional = st.nextToken();
            users.add(createUserObject(name,id,username,password,type,additional));
        }
        scan.close();
    }

    //function that creates a user object based on the type of user
    public static Controller createUserObject(String name, String id, String username, String password, String type, String additional){

        if (type.compareTo("admin") == 0)
            return (new AdministratorController(name,id,username,password));
        if (type.compareTo("student") == 0)
            return (new StudentController(name,id,username,password,additional));
        if (type.compareTo("instructor") == 0)
            return (new InstructorController(name,id,username,password,additional));

        return null;
    }

    public static void readCoursesFromThisSemester() throws FileNotFoundException {
        int numStudents, credits;
        String name, number, dept, instructor;
        Scanner scan = new Scanner(new File("C:\\Users\\Uncle Sam\\Desktop\\sthyaVERAT\\4 FUN ya Practice\\GUIProject\\src\\SIS\\spring2022.txt"));
        StringTokenizer st = new StringTokenizer(scan.nextLine(), ";");
        while(scan.hasNextLine() && st.hasMoreTokens()){
            numStudents = Integer.parseInt(st.nextToken());
            name = st.nextToken();
            number = st.nextToken();
            dept = st.nextToken();
            instructor = st.nextToken();
            credits = Integer.parseInt(st.nextToken());
            Course course = new Course(name, number, credits, dept, instructor);
            for(int i = 0; i < numStudents; i++){
                course.addStudent(st.nextToken());
            }
            for(int i = 0; i < numStudents; i++){
                course.addGrade(Double.parseDouble(st.nextToken()));
            }
            courses.add(course);
        }
        scan.close();
    }
}