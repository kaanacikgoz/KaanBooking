package view;

import business.UserManager;
import entity.User;

import javax.swing.*;
import java.awt.*;

public class UserView extends JFrame {
    private JPanel container;
    private JLabel lbl_header;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JComboBox<User.Role> cmb_role;
    private JButton btn_save;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_role;
    private final UserManager userManager;

    public UserView() {
        this.userManager = new UserManager();
        this.add(container);
        this.setSize(300,300);
        this.setTitle("Edit View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
        setupComboBox();

        btn_save.addActionListener(e -> {
            if (userManager.addUser(addUser())) {
                JOptionPane.showMessageDialog(null, "User added succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "User could not add", "Error!", JOptionPane.ERROR_MESSAGE);
            }
            dispose();
        });
    }

    private void setupComboBox() {
        this.cmb_role.removeAllItems();
        for (User.Role role:User.Role.values()) {
            this.cmb_role.addItem(role);
        }
        this.cmb_role.setSelectedItem(null);
    }

    private User addUser() {
        User user = new User();
        user.setUsername(this.fld_username.getText());
        user.setPassword(this.fld_password.getText());
        user.setRole((User.Role) this.cmb_role.getSelectedItem());
        return user;
    }

}
