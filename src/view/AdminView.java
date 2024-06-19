package view;

import business.UserManager;
import entity.User;
import entity.User.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AdminView extends JFrame {
    private JPanel container;
    private JButton btn_logout;
    private JTabbedPane tabbedPane1;
    public JLabel lbl_welcome;
    private JPanel top_container;
    private JTable tbl_user;
    private JButton btn_search;
    private JComboBox<User.Role> cmb_user_role;
    private JButton btn_clear;
    private JScrollPane scrl_user;
    private JPanel user_container;
    private final UserManager userManager;

    public AdminView() {
        this.userManager = new UserManager();
        this.add(container);
        this.setSize(750,450);
        this.setTitle("Admin View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
        loadUserRoleFilter();
        makeTable();
    }

    private void loadUserRoleFilter() {
        this.cmb_user_role.removeAllItems();
        for (Role role:User.Role.values()) {
            cmb_user_role.addItem(role);
        }
        this.cmb_user_role.setSelectedItem(null);
    }

    private void makeTable() {
        String[] columnNames = {"ID", "Username", "Password", "Role"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbl_user.setModel(model);
        tbl_user.getTableHeader().setReorderingAllowed(false);
        tbl_user.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_user.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) tbl_user.getModel();
        clearModel.setRowCount(0);

        ArrayList<Object[]> userRow = this.userManager.getForTable(columnNames.length, this.userManager.findAll());
        if (userRow==null) {
            userRow = new ArrayList<>();
        }
        for (Object[] row:userRow) {
            model.addRow(row);
        }
    }

}
