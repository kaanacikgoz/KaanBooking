package view;

import business.HotelManager;
import business.PensionManager;
import business.SeasonManager;
import entity.Hotel;
import entity.Pension;
import entity.Season;

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
    private JTable tbl_hotel;
    public JLabel lbl_welcome;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JTabbedPane tabbedPane4;
    private JLabel lbl_pension;
    private JLabel lbl_season;
    private JTabbedPane tabbedPane1;
    private String[] hotelColumnNames;
    private String[] pensionColumnNames;
    private String[] seasonColumnNames;
    private final HotelManager hotelManager;
    private final PensionManager pensionManager;
    private final SeasonManager seasonManager;
    private final JPopupMenu hotelMenu = new JPopupMenu();
    private final JPopupMenu pensionMenu = new JPopupMenu();
    private final JPopupMenu seasonMenu = new JPopupMenu();

    public EmployeeView() {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.setSize(1250,500);
        this.setTitle("Employee View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);

        makeHotelTable();
        makePensionTable();
        makeSeasonTable();
        loadHotelComponent();
        loadPensionComponent();
        loadSeasonComponent();
    }

    private void makeHotelTable() {
        hotelColumnNames = new String[]{"ID","Hotel Name","City","Region","Address","Email","Phone","Star",
                "FreeParking","FreeWifi","SwimmingPool","Gym","Concierge","Spa","24/7 RoomService"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(hotelColumnNames);
        tbl_hotel.setModel(model);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);
        setTableWidth(this.tbl_hotel);
        tbl_hotel.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) tbl_hotel.getModel();
        clearModel.setRowCount(0);

        ArrayList<Object[]> hotelRow = this.hotelManager.getForTable(hotelColumnNames.length, this.hotelManager.findAll());
        if (hotelRow==null) {
            hotelRow = new ArrayList<>();
        }
        for (Object[] row:hotelRow) {
            model.addRow(row);
        }
    }

    private void makePensionTable() {
        pensionColumnNames = new String[]{"ID","Hotel ID","Hotel Name","Pension Type"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(pensionColumnNames);
        tbl_pension.setModel(model);
        tbl_pension.getTableHeader().setReorderingAllowed(false);
        tbl_pension.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) tbl_pension.getModel();
        clearModel.setRowCount(0);

        ArrayList<Object[]> pensionRow = this.pensionManager.getForTable(pensionColumnNames.length, this.pensionManager.findAll());
        if (pensionRow==null) {
            pensionRow = new ArrayList<>();
        }
        for (Object[] row:pensionRow) {
            model.addRow(row);
        }
    }

    private void makeSeasonTable() {
        seasonColumnNames = new String[]{"ID","Hotel ID","Hotel Name","Start Date","Finish Date"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(seasonColumnNames);
        tbl_season.setModel(model);
        tbl_season.getTableHeader().setReorderingAllowed(false);
        tbl_season.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) tbl_season.getModel();
        clearModel.setRowCount(0);

        ArrayList<Object[]> seasonRow = this.seasonManager.getForTable(seasonColumnNames.length, this.seasonManager.findAll());
        if (seasonRow==null) {
            seasonRow = new ArrayList<>();
        }
        for (Object[] row:seasonRow) {
            model.addRow(row);
        }
    }

    private void setTableWidth(JTable table) {
        table.getColumnModel().getColumn(0).setMaxWidth(25);
        table.getColumnModel().getColumn(1).setMaxWidth(125);
        table.getColumnModel().getColumn(2).setMaxWidth(50);
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
        tableRowSelect(this.tbl_hotel, this.hotelMenu);
        this.hotelMenu.add("New").addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeHotelTable();
                }
            });
        });
        this.hotelMenu.add("Update").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeHotelTable();
                }
            });
        });
        this.hotelMenu.add("Delete").addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning!",JOptionPane.YES_NO_OPTION);
            int selectHotelId = this.getTableSelectedRow(this.tbl_hotel, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.hotelManager.deleteHotel(selectHotelId);
                JOptionPane.showMessageDialog(null, "Hotel delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadHotelModelTable(null);
            } else {
                JOptionPane.showMessageDialog(null, "Hotel could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void loadPensionComponent() {
        tableRowSelect(this.tbl_pension, this.pensionMenu);
        this.pensionMenu.add("New").addActionListener(e -> {
            PensionView pensionView = new PensionView(new Pension());
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makePensionTable();
                }
            });
        });
        this.pensionMenu.add("Update").addActionListener(e -> {
            int selectPensionId = this.getTableSelectedRow(this.tbl_pension, 0);
            PensionView pensionView = new PensionView(this.pensionManager.getById(selectPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makePensionTable();
                }
            });
        });
        this.pensionMenu.add("Delete").addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning!",JOptionPane.YES_NO_OPTION);
            int selectPensionId = this.getTableSelectedRow(this.tbl_pension, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.pensionManager.deletePension(selectPensionId);
                JOptionPane.showMessageDialog(null, "Pension delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadPensionModelTable(null);
            } else {
                JOptionPane.showMessageDialog(null, "Pension could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void loadSeasonComponent() {
        tableRowSelect(this.tbl_season, this.seasonMenu);
        this.seasonMenu.add("New").addActionListener(e -> {
            SeasonView seasonView = new SeasonView(new Season());
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeSeasonTable();
                }
            });
        });
        this.seasonMenu.add("Update").addActionListener(e -> {
            int selectSeasonId = this.getTableSelectedRow(this.tbl_season, 0);
            SeasonView seasonView = new SeasonView(this.seasonManager.getBySeasonId(selectSeasonId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeSeasonTable();
                }
            });
        });
        this.seasonMenu.add("Delete").addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning!",JOptionPane.YES_NO_OPTION);
            int selectModelId = this.getTableSelectedRow(this.tbl_season, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.seasonManager.deleteSeason(selectModelId);
                JOptionPane.showMessageDialog(null, "Season delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadSeasonModelTable(null);
            } else {
                JOptionPane.showMessageDialog(null, "Season could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
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

    public void loadHotelModelTable(ArrayList<Object[]> hotelList) {
        DefaultTableModel model = (DefaultTableModel) tbl_hotel.getModel();
        model.setRowCount(0); // Clear the table

        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.hotelColumnNames.length, this.hotelManager.findAll());
        }
        for (Object[] row : hotelList) {
            model.addRow(row);
        }
    }

    public void loadPensionModelTable(ArrayList<Object[]> pensionList) {
        DefaultTableModel model = (DefaultTableModel) tbl_pension.getModel();
        model.setRowCount(0); // Clear the table

        if (pensionList == null) {
            pensionList = this.pensionManager.getForTable(this.pensionColumnNames.length, this.pensionManager.findAll());
        }
        for (Object[] row : pensionList) {
            model.addRow(row);
        }
    }

    public void loadSeasonModelTable(ArrayList<Object[]> seasonList) {
        DefaultTableModel model = (DefaultTableModel) tbl_season.getModel();
        model.setRowCount(0); // Clear the table

        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(this.seasonColumnNames.length, this.seasonManager.findAll());
        }
        for (Object[] row : seasonList) {
            model.addRow(row);
        }
    }

}
