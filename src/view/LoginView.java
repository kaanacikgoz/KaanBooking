package view;

import business.UserManager;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class LoginView extends JFrame {
    private JPanel container;
    private JTextField fld_username;
    private JFormattedTextField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_header;
    private final UserManager userManager;

    public LoginView() {
        this.userManager = new UserManager();
        this.add(container);
        this.setSize(300,300);
        this.setTitle("KaanBooking Inc.");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);


        btn_login.addActionListener(e -> {
            if (fld_username.getText().isEmpty() || fld_password.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in both fields", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (User user:this.userManager.findAll()) {
                    if (Objects.equals(fld_username.getText(), user.getUsername()) && Objects.equals(fld_password.getText(), user.getPassword())) {
                        if (user.getRole()==User.Role.ADMIN) {
                            //Admin View
                            AdminView adminView = new AdminView();
                            adminView.lbl_welcome.setText("Welcome, "+this.fld_username.getText());
                            this.dispose();
                        } else {
                            //Employee View
                            System.out.println("Employee");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "User not found", "Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

}
