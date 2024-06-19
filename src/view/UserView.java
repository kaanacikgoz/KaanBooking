package view;

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

    public UserView() {
        this.add(container);
        this.setSize(300,300);
        this.setTitle("Edit View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
        setupComboBox();
    }

    private void setupComboBox() {
        this.cmb_role.removeAllItems();
        for (User.Role role:User.Role.values()) {
            this.cmb_role.addItem(role);
        }
        this.cmb_role.setSelectedItem(null);
    }

}
