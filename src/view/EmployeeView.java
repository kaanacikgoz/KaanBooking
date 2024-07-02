package view;

import business.*;
import core.ComboItem;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeView extends JFrame {

    private JPanel container;
    private JPanel pnl_top;
    private JButton btn_logout;
    private JTable tbl_hotel;
    public JLabel lbl_welcome;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JTabbedPane tab_menu;
    private JLabel lbl_pension;
    private JLabel lbl_season;
    private JPanel pnl_room;
    private JPanel pnl_hotel;
    private JTable tbl_room;
    private JComboBox<ComboItem> cmb_city;
    private JComboBox<ComboItem> cmb_hotelName;
    private JFormattedTextField fld_startDate;
    private JFormattedTextField fld_finishDate;
    private JLabel lbl_startDate;
    private JLabel lbl_finishDate;
    private JLabel lbl_city;
    private JLabel lbl_hotelName;
    private JButton btn_clear;
    private JButton btn_search;
    private JTable tbl_booking;
    private JPanel pnl_booking;
    private String[] hotelColumnNames;
    private String[] pensionColumnNames;
    private String[] seasonColumnNames;
    private String[] roomColumnNames;
    private String[] bookingColumnNames;
    private final HotelManager hotelManager;
    private final PensionManager pensionManager;
    private final SeasonManager seasonManager;
    private final RoomManager roomManager;
    private final BookingManager bookingManager;
    private final JPopupMenu hotelMenu = new JPopupMenu();
    private final JPopupMenu pensionMenu = new JPopupMenu();
    private final JPopupMenu seasonMenu = new JPopupMenu();
    private final JPopupMenu roomMenu = new JPopupMenu();
    private final JPopupMenu bookingMenu = new JPopupMenu();

    //Evaluation Form-8
    public EmployeeView() {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.bookingManager = new BookingManager();
        this.add(container);
        this.setSize(1250,500);
        this.setTitle("Employee View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);

        makeHotelTable();
        loadHotelComponent();

        makePensionTable();
        loadPensionComponent();

        makeSeasonTable();
        loadSeasonComponent();

        makeRoomTable();
        loadRoomComponent();
        loadRoomFilter();

        makeBookingTable();
        loadBookingComponent();

        btn_clear.addActionListener(e -> {
            loadRoomFilter();
        });

        //Evaluation Form-15
        btn_search.addActionListener(e -> {
            ComboItem selectedCity = (ComboItem) this.cmb_city.getSelectedItem();
            ComboItem selectedHotelName = (ComboItem) this.cmb_hotelName.getSelectedItem();

            String city = selectedCity != null ? selectedCity.getValue() : null;
            String hotelName = selectedHotelName != null ? selectedHotelName.getValue() : null;

            ArrayList<Room> roomList = this.roomManager.searchForRooms(
                    this.fld_startDate.getText(),
                    this.fld_finishDate.getText(),
                    city,
                    hotelName
            );
            ArrayList<Object[]> roomBookingRow = this.roomManager.getForTable(this.roomColumnNames.length, roomList);
            loadRoomModelTable(roomBookingRow);
        });


        btn_logout.addActionListener(e -> {
            new LoginView();
            dispose();
        });
    }

    private void loadRoomFilter() {
        this.cmb_city.removeAllItems();
        this.cmb_hotelName.removeAllItems();

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_city.addItem(new ComboItem(hotel.getId(), hotel.getCity()));
            this.cmb_hotelName.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }

        this.cmb_city.setSelectedItem(null);
        this.cmb_hotelName.setSelectedItem(null);
    }

    //Evaluation Form-16
    private void makeHotelTable() {
        hotelColumnNames = new String[]{"ID","Hotel Name","City","Region","Address","Email","Phone","Star",
                "FreeParking","FreeWifi","SwimmingPool","Gym","Concierge","Spa","24/7 RoomService"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(hotelColumnNames);
        tbl_hotel.setModel(model);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);
        setHotelTableWidth(this.tbl_hotel);
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
        setSeasonTableWidth(tbl_season);

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

    //Evaluation Form-14
    //Evaluation Form-16
    private void makeRoomTable() {
        roomColumnNames = new String[]{"ID","Hotel ID","Pension ID","Season ID","Room Type","Room Stock", "Bed Num",
                "Square Meters","TV","MiniBar","Game Console","Hotel Safe","Projection","Child Price","Adult Price","Room Price"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(roomColumnNames);
        tbl_room.setModel(model);
        tbl_room.getTableHeader().setReorderingAllowed(false);
        tbl_room.setEnabled(false);
        setRoomTableWidth(tbl_room);

        DefaultTableModel clearModel = (DefaultTableModel) tbl_room.getModel();
        clearModel.setRowCount(0);


        ArrayList<Object[]> roomRow = this.roomManager.getForTable(roomColumnNames.length, this.roomManager.findAll());
        if (roomRow==null) {
            roomRow = new ArrayList<>();
        }
        for (Object[] row:roomRow) {
            model.addRow(row);
        }

    }

    //Evaluation Form-14
    //Evaluation Form-20
    private void makeBookingTable() {
        bookingColumnNames = new String[]{"ID","Room ID","Customer Name","Customer TC","Customer Mail","Customer Phone",
                "Start Date", "Finish Date", "Child Number", "Adult Number", "Total Price", "Customer Note"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(bookingColumnNames);
        tbl_booking.setModel(model);
        tbl_booking.getTableHeader().setReorderingAllowed(false);
        tbl_booking.setEnabled(false);

        DefaultTableModel clearModel = (DefaultTableModel) tbl_booking.getModel();
        clearModel.setRowCount(0);

        ArrayList<Object[]> bookingRow = this.bookingManager.getForTable(bookingColumnNames.length, this.bookingManager.findAll());
        if (bookingRow==null) {
            bookingRow = new ArrayList<>();
        }
        for (Object[] row:bookingRow) {
            model.addRow(row);
        }
    }

    private void setHotelTableWidth(JTable table) {
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

    private void setSeasonTableWidth(JTable table) {
        table.getColumnModel().getColumn(0).setMaxWidth(35);
        table.getColumnModel().getColumn(1).setMaxWidth(60);
    }

    private void setRoomTableWidth(JTable table) {
        table.getColumnModel().getColumn(0).setMaxWidth(25);
        table.getColumnModel().getColumn(1).setMaxWidth(50);
        table.getColumnModel().getColumn(2).setMaxWidth(65);
        table.getColumnModel().getColumn(3).setMaxWidth(60);
        table.getColumnModel().getColumn(4).setMaxWidth(200);
        table.getColumnModel().getColumn(5).setMaxWidth(100);
        table.getColumnModel().getColumn(6).setMaxWidth(75);
        table.getColumnModel().getColumn(7).setMaxWidth(100);
        table.getColumnModel().getColumn(8).setMaxWidth(50);
        table.getColumnModel().getColumn(9).setMaxWidth(100);
        table.getColumnModel().getColumn(10).setMaxWidth(125);
        table.getColumnModel().getColumn(11).setMaxWidth(100);
        table.getColumnModel().getColumn(12).setMaxWidth(100);
        table.getColumnModel().getColumn(13).setMaxWidth(100);
        table.getColumnModel().getColumn(14).setMaxWidth(100);
        table.getColumnModel().getColumn(15).setMaxWidth(100);
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
                loadHotelModelTable(null);
                //Evaluation Form-24
                JOptionPane.showMessageDialog(null, "Hotel delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
                loadPensionModelTable(null);
                JOptionPane.showMessageDialog(null, "Pension delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
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
            int selectSeasonId = this.getTableSelectedRow(this.tbl_season, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.seasonManager.deleteSeason(selectSeasonId);
                loadSeasonModelTable(null);
                //Evaluation Form-24
                JOptionPane.showMessageDialog(null, "Season delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Season could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private void loadRoomComponent() {
        tableRowSelect(this.tbl_room, this.roomMenu);
        this.roomMenu.add("New").addActionListener(e -> {
            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeRoomTable();
                }
            });
        });
        this.roomMenu.add("Update").addActionListener(e -> {
            int selectRoomId = this.getTableSelectedRow(this.tbl_room, 0);
            RoomView roomView = new RoomView(this.roomManager.getByRoomId(selectRoomId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeRoomTable();
                }
            });
        });
        this.roomMenu.add("Delete").addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning!",JOptionPane.YES_NO_OPTION);
            int selectRoomId = this.getTableSelectedRow(this.tbl_room, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.roomManager.deleteRoom(selectRoomId);
                loadRoomModelTable(null);
                //Evaluation Form-24
                JOptionPane.showMessageDialog(null, "Room delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Room could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //Evaluation Form-18
        this.roomMenu.add("Create a Booking").addActionListener(e -> {
            int selectRoomId = this.getTableSelectedRow(this.tbl_room, 0);
            System.out.println(selectRoomId);

            BookingView bookingView = new BookingView(selectRoomId,new Booking(),
                    fld_startDate.getText(),
                    fld_finishDate.getText());
            bookingView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeBookingTable();
                }
            });
        });
    }

    private void loadBookingComponent() {
        tableRowSelect(this.tbl_booking, this.bookingMenu);
        //Evaluation Form-21
        this.bookingMenu.add("Update").addActionListener(e -> {
            int selectBookingId = this.getTableSelectedRow(this.tbl_booking, 0);
            BookingView bookingView = new BookingView(0,this.bookingManager.getByBookingId(selectBookingId),
                    this.fld_startDate.getText(),
                    this.fld_finishDate.getText());
            bookingView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    makeBookingTable();
                }
            });
        });
        //Evaluation Form-22
        this.bookingMenu.add("Delete").addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "Warning!",JOptionPane.YES_NO_OPTION);
            int selectBookingId = this.getTableSelectedRow(this.tbl_booking, 0);
            if (response==JOptionPane.YES_OPTION) {
                this.bookingManager.deleteBooking(selectBookingId);
                loadBookingModelTable(null);
                //Evaluation Form-24
                JOptionPane.showMessageDialog(null, "Booking delete successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Booking could not deleted", "Not Deleted", JOptionPane.INFORMATION_MESSAGE);
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

    public void loadRoomModelTable(ArrayList<Object[]> roomList) {
        DefaultTableModel model = (DefaultTableModel) tbl_room.getModel();
        model.setRowCount(0); // Clear the table

        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.roomColumnNames.length, this.roomManager.findAll());
        }
        for (Object[] row : roomList) {
            model.addRow(row);
        }
    }

    public void loadBookingModelTable(ArrayList<Object[]> bookingList) {
        DefaultTableModel model = (DefaultTableModel) tbl_booking.getModel();
        model.setRowCount(0); // Clear the table

        if (bookingList == null) {
            bookingList = this.bookingManager.getForTable(this.bookingColumnNames.length, this.bookingManager.findAll());
        }
        for (Object[] row : bookingList) {
            model.addRow(row);
        }
    }

    private void createUIComponents() {
        try {
            this.fld_startDate = new JFormattedTextField(new MaskFormatter("####/##/##"));
            this.fld_startDate.setText("2024/06/10");
            this.fld_finishDate = new JFormattedTextField(new MaskFormatter("####/##/##"));
            this.fld_finishDate.setText("2024/06/13");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

}
