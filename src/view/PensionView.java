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
    private JComboBox<Pension.PensionType> cmb_pension;
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

        setupComboBoxes();
        setPensionInfo();
        saveButton();
    }

    private void saveButton() {
        btn_save.addActionListener(e -> {
            if (this.pension.getPensionId() != 0) {
                this.pensionManager.updatePension(addPension());
            } else {
                this.pensionManager.addPension(addPension());
                JOptionPane.showMessageDialog(null, "Pension added succesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });
    }

    private Pension addPension() {
        ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
        Pension.PensionType selectedPensionType = (Pension.PensionType) cmb_pension.getSelectedItem();

        if (selectedHotel != null) {
            pension.setHotelId(selectedHotel.getKey());
            pension.setHotelName(selectedHotel.getValue());
        }

        if (selectedPensionType != null) {
            pension.setPensionType(selectedPensionType);
        }

        return pension;
    }

    private void setPensionInfo() {
        for (int i = 0; i < cmb_hotel.getItemCount(); i++) {
            ComboItem item = cmb_hotel.getItemAt(i);
            if (item.getValue().equals(pension.getHotelName())) {
                this.cmb_hotel.setSelectedItem(item);
                break;
            }
        }
        this.cmb_pension.setSelectedItem(pension.getPensionType());
    }

    private void setupComboBoxes() {
        this.cmb_hotel.removeAllItems();
        this.cmb_pension.removeAllItems();

        for (Hotel hotel: hotelManager.findAll()) {
            this.cmb_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }
        for (Pension.PensionType pensionType: Pension.PensionType.values()) {
            this.cmb_pension.addItem(pensionType);
        }

        this.cmb_hotel.setSelectedItem(null);
        this.cmb_pension.setSelectedItem(null);
    }

}
