package view;


import business.PensionManager;
import core.ComboItem;
import entity.Hotel;
import entity.Pension;
import business.HotelManager;

import javax.swing.*;
import java.awt.*;

public class PensionView extends JFrame {
    private JPanel container;
    private JComboBox<ComboItem> cmb_hotel;
    private JComboBox<ComboItem> cmb_pension;
    private JButton btn_save;
    private JLabel lbl_hotel;
    private JLabel lbl_pension;
    private JLabel lbl_header;
    private final Pension pension;
    private final HotelManager hotelManager;
    private final PensionManager pensionManager;

    public PensionView(Pension pension) {
        this.pension = pension;
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        this.setSize(350,350);
        this.setTitle("Pension View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);
        loadHotelCmb();
        loadPensionCmb();
        updateCmb();
        /*
        btn_save.addActionListener(e -> {
            if (this.pension.getPensionId() != 0) {
                this.pensionManager.updateHotel(addPension());
            } else {
                this.pensionManager.addHotel(addPension());
                JOptionPane.showMessageDialog(null, "Pension added succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });

         */
    }

    private void loadHotelCmb() {
        this.cmb_hotel.removeAllItems();
        for (Hotel hotel: hotelManager.findAll() ) {
            cmb_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }
        this.cmb_hotel.setSelectedItem(null);
    }

    private void loadPensionCmb() {
        this.cmb_pension.removeAllItems();
        for (Pension.PensionType pensionType: Pension.PensionType.values() ) {
            cmb_pension.addItem(new ComboItem(pensionType.ordinal(), pensionType.name()));
        }
        this.cmb_pension.setSelectedItem(null);
    }

    private void updateCmb() {
        cmb_hotel.setSelectedItem(this.pension.getHotelName());
        cmb_pension.setSelectedItem(this.pension.getPensionType());
    }

    /*
    private Pension addPension() {
        pension.setPensionType((Pension.PensionType) this.cmb_pension.getSelectedItem());
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
        return pension;
    }

     */

    /*
    private void setHotelNameInfo() {
        this.cmb_hotel
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

     */

}
