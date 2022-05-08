package SIS.Adminstrator;

import SIS.View;

import javax.swing.*;
import java.awt.*;

public class AdministratorView extends View {

    @Override
    public void getView() {
        JFrame frame = new JFrame();
        frame.setBounds(300,300,300,300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Admin");
        panel.add(label,BorderLayout.CENTER);
        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    @Override
    public void setView() {

    }
}
