package view;

import business.HotelManager;
import entity.Hotel;

import javax.swing.*;
import java.awt.*;

public class HotelView extends JFrame {
    private JPanel container;
    private JLabel lbl_header;
    private JTextField fld_name;
    private JTextField fld_city;
    private JTextField fld_region;
    private JTextField fld_address;
    private JTextField fld_email;
    private JTextField fld_phone;
    private JLabel lbl_name;
    private JLabel lbl_city;
    private JLabel lbl_region;
    private JLabel lbl_address;
    private JLabel lbl_email;
    private JLabel lbl_phone;
    private JComboBox<Hotel.Star> cmb_star;
    private JLabel lbl_star;
    private JCheckBox chck_parking;
    private JCheckBox chck_wifi;
    private JCheckBox chck_swimming;
    private JCheckBox chck_gym;
    private JCheckBox chck_concierge;
    private JCheckBox chck_spa;
    private JCheckBox chck_roomService;
    private JButton btn_add;
    private final Hotel hotel;
    private final HotelManager hotelManager;

    public HotelView(Hotel hotel) {
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);
        this.setSize(500,600);
        this.setTitle("Hotel View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
        setupComboBox();
        setHotelInfo();

        //Evaluation Form-10
        btn_add.addActionListener(e -> {
            if (this.hotel.getId() != 0) {
                this.hotelManager.updateHotel(addHotel());
            } else {
                this.hotelManager.addHotel(addHotel());
                //Evaluation Form-24
                JOptionPane.showMessageDialog(null, "Hotel added succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });
    }

    private Hotel addHotel() {
        hotel.setName(this.fld_name.getText());
        hotel.setCity(this.fld_city.getText());
        hotel.setRegion(this.fld_region.getText());
        hotel.setAddress(this.fld_address.getText());
        hotel.setEmail(this.fld_email.getText());
        hotel.setPhone(this.fld_phone.getText());
        hotel.setStar((Hotel.Star) this.cmb_star.getSelectedItem());
        hotel.setFreeParking(this.chck_parking.isSelected());
        hotel.setFreeWifi(this.chck_wifi.isSelected());
        hotel.setSwimmingPool(this.chck_swimming.isSelected());
        hotel.setGym(this.chck_gym.isSelected());
        hotel.setHotelConcierge(this.chck_concierge.isSelected());
        hotel.setSpa(this.chck_spa.isSelected());
        hotel.setRoomService(this.chck_roomService.isSelected());
        return hotel;
    }

    private void setHotelInfo() {
        this.fld_name.setText(hotel.getName());
        this.fld_city.setText(hotel.getCity());
        this.fld_region.setText(hotel.getRegion());
        this.fld_address.setText(hotel.getAddress());
        this.fld_email.setText(hotel.getEmail());
        this.fld_phone.setText(hotel.getPhone());
        this.cmb_star.setSelectedItem(hotel.getStar());
        this.chck_parking.setSelected(hotel.isFreeParking());
        this.chck_wifi.setSelected(hotel.isFreeWifi());
        this.chck_swimming.setSelected(hotel.isSwimmingPool());
        this.chck_gym.setSelected(hotel.isGym());
        this.chck_concierge.setSelected(hotel.isHotelConcierge());
        this.chck_spa.setSelected(hotel.isSpa());
        this.chck_roomService.setSelected(hotel.isRoomService());
    }

    private void setupComboBox() {
        this.cmb_star.removeAllItems();
        for (Hotel.Star star:Hotel.Star.values()) {
            this.cmb_star.addItem(star);
        }
        this.cmb_star.setSelectedItem(null);
    }

}
