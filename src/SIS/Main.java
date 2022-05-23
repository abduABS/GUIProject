
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

public class Main extends Login{


    public static void main(String[] args) throws FileNotFoundException {

        /*
        Required Files:
        users.txt -> list of registered users and their login info
        spring2022.txt -> list of current sem courses, students enrolled, grades, etc
        since students have to be able to register for courses in future term, we can create a new textfile
        that keeps track of courses available next sem - and still follow the same naming convention 'semester_name.txt'
*/
        startup();


    }








}

