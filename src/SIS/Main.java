package SIS;

import javax.swing.*;
import java.util.ArrayList;

public class Main {
    static ArrayList<Model> users = new ArrayList<>();
    static int flag = 0;
    static int tries = 0;

    public static void main(String[] args) {
        
        //TODO: Figure out the ArrayList functionality
        login();
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
            String password = "" + passField.getPassword();
            for(int i =0;i < users.size();i++){
                if(users.get(i).getId() == username && users.get(i).getPassword() == password)
                {
                    flag = 1;
                }
                else{
                    tries++;
                }
            }

        }
    }

}