package SIS.Adminstrator;

import SIS.Controller;
import SIS.Instructor.InstructorController;
import SIS.Main;
import SIS.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdministratorView extends View {

    public void getView() {
        JTextField iDField = new JTextField();
        Object[] panel = {"Student ID:", iDField};
        int option = JOptionPane.showConfirmDialog(null, panel, "Search by ID", JOptionPane.OK_CANCEL_OPTION);
        int flag = 0;
        if (option == JOptionPane.OK_OPTION) {
            for (int i = 0; i < Main.getUsers().size(); i++) {
                if (Main.getUsers().get(i).getModel().getId().equals(iDField.getText())) {
                    flag = 1;
                    Controller controller = Main.getUsers().get(i);
                    controller.setAdmin(true);
                    controller.view().getView();
                }
            }
        }
        if (flag == 0) {
            JOptionPane.showConfirmDialog(null, "No user was found with the inputted ID", "Error", JOptionPane.DEFAULT_OPTION);

        }
    }

    public void setView() {

    }
}
