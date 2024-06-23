package view;

import business.UserManager;
import entity.User;
import entity.User.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private final JPopupMenu userMenu = new JPopupMenu();

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
        loadUserComponent();
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

    private void loadUserComponent() {
        tableRowSelect(this.tbl_user, this.userMenu);
        this.userMenu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeTable();
                }
            });
        });
        this.userMenu.add("Güncelle").addActionListener(e -> {
            int selectModelId = this.getTableSelectedRow(this.tbl_user, 0);
            UserView userView = new UserView(this.userManager.getById(selectModelId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeTable();
                }
            });
        });
        this.userMenu.add("Sil").addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning!",JOptionPane.YES_NO_OPTION);
            int selectModelId = this.getTableSelectedRow(this.tbl_user, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.userManager.deleteUser(selectModelId);
                JOptionPane.showMessageDialog(null, "User delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadModelTable(null);
            } else {
                JOptionPane.showMessageDialog(null, "User could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index).toString());
    }

    public void tableRowSelect(JTable table, JPopupMenu menu) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int selectedRow = table.rowAtPoint(e.getPoint());
                    table.setRowSelectionInterval(selectedRow, selectedRow);
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = table.rowAtPoint(e.getPoint());
                    table.setRowSelectionInterval(row, row);
                    menu.show(table, e.getX(), e.getY());
                }
            }
        });
    }

    public void loadModelTable(ArrayList<Object[]> userList) {
        String[] columnNames = {"ID", "Username", "Password", "Role"};
        if (userList==null) {
            userList = this.userManager.getForTable(columnNames.length, this.userManager.findAll());
        }
        makeTable();
    }

}
