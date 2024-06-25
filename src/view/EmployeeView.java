package view;

import business.HotelManager;
import entity.Hotel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends JFrame {

    private JPanel container;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JTabbedPane tabbedPane1;
    private JTable tbl_otel;
    private JScrollPane scrl_otel;
    private JPanel pnl_otel;
    public JLabel lbl_welcome;
    private String[] columnNames;
    private final HotelManager hotelManager;
    private final JPopupMenu hotelMenu = new JPopupMenu();

    public EmployeeView() {
        this.hotelManager = new HotelManager();
        this.add(container);
        this.setSize(1250,500);
        this.setTitle("Employee View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
        makeTable();
        loadHotelComponent();
    }

    private void makeTable() {
        columnNames = new String[]{"ID","Hotel Name","City","Region","Address","Email","Phone","Star",
                "FreeParking","FreeWifi","SwimmingPool","Gym","Concierge","Spa","24/7 RoomService"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        tbl_otel.setModel(model);
        tbl_otel.getTableHeader().setReorderingAllowed(false);
        setTableWidth(this.tbl_otel);
        tbl_otel.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) tbl_otel.getModel();
        clearModel.setRowCount(0);

        ArrayList<Object[]> userRow = this.hotelManager.getForTable(columnNames.length, this.hotelManager.findAll());
        if (userRow==null) {
            userRow = new ArrayList<>();
        }
        for (Object[] row:userRow) {
            model.addRow(row);
        }
    }

    private void setTableWidth(JTable table) {
        table.getColumnModel().getColumn(0).setMaxWidth(35);
        table.getColumnModel().getColumn(1).setMaxWidth(125);
        table.getColumnModel().getColumn(2).setMaxWidth(75);
        table.getColumnModel().getColumn(3).setMaxWidth(75);
        table.getColumnModel().getColumn(4).setMaxWidth(200);
        table.getColumnModel().getColumn(5).setMaxWidth(125);
        table.getColumnModel().getColumn(6).setMaxWidth(125);
        table.getColumnModel().getColumn(7).setMaxWidth(50);
        table.getColumnModel().getColumn(8).setMaxWidth(75);
        table.getColumnModel().getColumn(9).setMaxWidth(50);
        table.getColumnModel().getColumn(10).setMaxWidth(100);
        table.getColumnModel().getColumn(11).setMaxWidth(50);
        table.getColumnModel().getColumn(12).setMaxWidth(75);
        table.getColumnModel().getColumn(13).setMaxWidth(50);
        table.getColumnModel().getColumn(14).setMaxWidth(125);
    }

    private void loadHotelComponent() {
        tableRowSelect(this.tbl_otel, this.hotelMenu);
        this.hotelMenu.add("New").addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeTable();
                }
            });
        });
        this.hotelMenu.add("Update").addActionListener(e -> {
            int selectModelId = this.getTableSelectedRow(this.tbl_otel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectModelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeTable();
                }
            });
        });
        this.hotelMenu.add("Delete").addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning!",JOptionPane.YES_NO_OPTION);
            int selectModelId = this.getTableSelectedRow(this.tbl_otel, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.hotelManager.deleteHotel(selectModelId);
                JOptionPane.showMessageDialog(null, "Hotel delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadModelTable(null);
            } else {
                JOptionPane.showMessageDialog(null, "Hotel could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        });
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

    private int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(),index).toString());
    }

    public void loadModelTable(ArrayList<Object[]> hotelList) {
        DefaultTableModel model = (DefaultTableModel) tbl_otel.getModel();
        model.setRowCount(0); // Clear the table

        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.columnNames.length, this.hotelManager.findAll());
        }
        for (Object[] row : hotelList) {
            model.addRow(row);
        }
    }

}
