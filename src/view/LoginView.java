package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {
    private JPanel container;
    private JTextField fld_username;
    private JFormattedTextField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_header;

    public LoginView() {
        this.add(container);
        this.setSize(300,300);
        this.setTitle("KaanBooking Inc.");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
    }

}
