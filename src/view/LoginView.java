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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);

        //Evaluation Form-9
        btn_login.addActionListener(e -> {
            if (fld_username.getText().isEmpty() || fld_password.getText().isEmpty()) {
                //Evaluation Form-25
                JOptionPane.showMessageDialog(null, "Please fill in both fields", "Error", JOptionPane.INFORMATION_MESSAGE);
            } else {
                boolean userFound = false;
                for (User user : this.userManager.findAll()) {
                    if (Objects.equals(fld_username.getText(), user.getUsername()) && Objects.equals(fld_password.getText(), user.getPassword())) {
                        userFound = true;
                        if (user.getRole() == User.Role.ADMIN) {
                            //Admin View
                            AdminView adminView = new AdminView();
                            setWelcomeText(adminView, null);
                        } else {
                            //Employee View
                            EmployeeView employeeView = new EmployeeView();
                            setWelcomeText(null, employeeView);
                        }
                        dispose();
                        break;
                    }
                }
                if (!userFound) {
                    //Evaluation Form-25
                    JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setWelcomeText(AdminView adminView, EmployeeView employeeView) {
        if (adminView==null) {
            employeeView.lbl_welcome.setText("Welcome, "+this.fld_username.getText());
        } else {
            adminView.lbl_welcome.setText("Welcome, "+this.fld_username.getText());
        }
    }

}
