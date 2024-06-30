package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.*;

public class RoomView extends JFrame {
    private JPanel container;
    private JLabel lbl_header;
    private JComboBox<Room.RoomType> cmb_roomType;
    private JLabel lbl_roomType;
    private JComboBox<ComboItem> cmb_hotel;
    private JLabel lbl_hotel;
    private JComboBox<ComboItem> cmb_pension;
    private JLabel lbl_pension;
    private JComboBox<ComboItem> cmb_season;
    private JLabel lbl_season;
    private JTextField fld_adultPrice;
    private JTextField fld_roomStock;
    private JTextField fld_childPrice;
    private JLabel lbl_roomStock;
    private JLabel lbl_childPrice;
    private JLabel lbl_adultPrice;
    private JTextField fld_bedNum;
    private JTextField fld_squareMeters;
    private JLabel lbl_bedNum;
    private JLabel lbl_squareMeters;
    private JCheckBox chck_tv;
    private JCheckBox chck_miniBar;
    private JCheckBox chck_gameConsole;
    private JCheckBox chck_hotelSafe;
    private JCheckBox chck_projection;
    private JButton btn_save;
    private final Room room;
    private final HotelManager hotelManager;
    private final PensionManager pensionManager;
    private final SeasonManager seasonManager;
    private final RoomManager roomManager;

    public RoomView(Room room) {
        this.room = room;
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.setSize(500,500);
        this.setTitle("Room View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);

        setupComboBoxes();
        setRoomInfo();
        saveButton();
    }

    private void setupComboBoxes() {
        this.cmb_hotel.removeAllItems();
        this.cmb_pension.removeAllItems();
        this.cmb_season.removeAllItems();
        this.cmb_roomType.removeAllItems();

        for (Hotel hotel : hotelManager.findAll()) {
            this.cmb_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }

        this.cmb_hotel.addActionListener(e -> updatePensionsAndSeasons());

        for (Room.RoomType roomType : Room.RoomType.values()) {
            this.cmb_roomType.addItem(roomType);
        }

        this.cmb_hotel.setSelectedItem(null);
        this.cmb_pension.setSelectedItem(null);
        this.cmb_season.setSelectedItem(null);
        this.cmb_roomType.setSelectedItem(null);
    }

    private void updatePensionsAndSeasons() {
        this.cmb_pension.removeAllItems();
        this.cmb_season.removeAllItems();

        ComboItem selectedHotel = (ComboItem) this.cmb_hotel.getSelectedItem();
        if (selectedHotel != null) {
            int hotelId = selectedHotel.getKey();

            for (Pension pension : pensionManager.findAll()) {
                if (pension.getHotelId() == hotelId) {
                    this.cmb_pension.addItem(new ComboItem(pension.getPensionId(), String.valueOf(pension.getPensionType())));
                }
            }

            for (Season season : seasonManager.findAll()) {
                if (season.getHotelId() == hotelId) {
                    this.cmb_season.addItem(new ComboItem(season.getSeasonId(), season.getStartDate() + " - " + season.getFinishDate()));
                }
            }
        }
    }

    private void saveButton() {
        btn_save.addActionListener(e -> {
            if (this.room.getRoomId() != 0) {
                this.roomManager.updateRoom(addRoom());
            } else {
                this.roomManager.addRoom(addRoom());
                JOptionPane.showMessageDialog(null, "Room added succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });
    }

    private void setRoomInfo() {
        for (int i = 0; i < cmb_hotel.getItemCount(); i++) {
            ComboItem item = cmb_hotel.getItemAt(i);
            if (item.getKey() == room.getHotelId()) {
                this.cmb_hotel.setSelectedItem(item);
                break;
            }
        }
        for (int i = 0; i < cmb_pension.getItemCount(); i++) {
            ComboItem item = cmb_pension.getItemAt(i);
            if (item.getKey() == room.getPensionId()) {
                this.cmb_pension.setSelectedItem(item);
                break;
            }
        }
        for (int i = 0; i < cmb_season.getItemCount(); i++) {
            ComboItem item = cmb_season.getItemAt(i);
            if (item.getKey() == room.getSeasonId()) {
                this.cmb_season.setSelectedItem(item);
                break;
            }
        }
        this.cmb_roomType.setSelectedItem(room.getRoomType());
        this.fld_adultPrice.setText(String.valueOf(room.getAdultPrice()));
        this.fld_childPrice.setText(String.valueOf(room.getChildPrice()));
        this.fld_roomStock.setText(String.valueOf(room.getRoomStock()));
        this.fld_bedNum.setText(String.valueOf(room.getBedNum()));
        this.fld_squareMeters.setText(String.valueOf(room.getSquareMeters()));
        this.chck_tv.setSelected(room.isTelevision());
        this.chck_miniBar.setSelected(room.isMiniBar());
        this.chck_gameConsole.setSelected(room.isGameConsole());
        this.chck_hotelSafe.setSelected(room.isHotelSafe());
        this.chck_projection.setSelected(room.isProjection());
    }

    private Room addRoom() {
        ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
        ComboItem selectedPension = (ComboItem) cmb_pension.getSelectedItem();
        ComboItem selectedSeason = (ComboItem) cmb_season.getSelectedItem();
        Room.RoomType selectedRoomType = (Room.RoomType) cmb_roomType.getSelectedItem();

        if (selectedHotel != null) {
            room.setHotelId(selectedHotel.getKey());
        }
        if (selectedPension != null) {
            room.setPensionId(selectedPension.getKey());
        }
        if (selectedSeason != null) {
            room.setSeasonId(selectedSeason.getKey());
        }
        if (selectedRoomType != null) {
            room.setRoomType(selectedRoomType);
        }

        room.setAdultPrice(Double.parseDouble(fld_adultPrice.getText()));
        room.setChildPrice(Double.parseDouble(fld_childPrice.getText()));
        room.setRoomStock(Integer.parseInt(fld_roomStock.getText()));
        room.setBedNum(Integer.parseInt(fld_bedNum.getText()));
        room.setSquareMeters(Double.parseDouble(fld_squareMeters.getText()));
        room.setTelevision(chck_tv.isSelected());
        room.setMiniBar(chck_miniBar.isSelected());
        room.setGameConsole(chck_gameConsole.isSelected());
        room.setHotelSafe(chck_hotelSafe.isSelected());
        room.setProjection(chck_projection.isSelected());

        return room;
    }

}
