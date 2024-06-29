package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends JFrame {
    private JPanel container;
    private JLabel lbl_header;
    private JFormattedTextField fld_startDate;
    private JFormattedTextField fld_finishDate;
    private JButton btn_save;
    private JLabel lbl_hotel;
    private JLabel lbl_startDate;
    private JLabel lbl_finishDate;
    private JComboBox<ComboItem> cmb_hotel;
    private final Season season;
    private final HotelManager hotelManager;
    private final SeasonManager seasonManager;

    public SeasonView(Season season) {
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.season = season;
        this.add(container);
        this.setSize(350,350);
        this.setTitle("Season View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);

        setupComboBox();
        setSeasonInfo();
        saveButton();
    }

    private void setupComboBox() {
        this.cmb_hotel.removeAllItems();

        for (Hotel hotel: hotelManager.findAll()) {
            this.cmb_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }

        this.cmb_hotel.setSelectedItem(null);
    }

    private void setSeasonInfo() {
        for (int i = 0; i < cmb_hotel.getItemCount(); i++) {
            ComboItem item = cmb_hotel.getItemAt(i);
            if (item.getValue().equals(season.getHotelName())) {
                this.cmb_hotel.setSelectedItem(item);
                break;
            }
        }
        //this.fld_startDate.setText(season.getStartDate().toString());
        //this.fld_startDate.setText(season.getStartDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        //this.fld_finishDate.setText(season.getFinishDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    private void saveButton() {
        btn_save.addActionListener(e -> {
            if (this.season.getSeasonId() != 0) {
                this.seasonManager.updateSeason(addSeason());
            } else {
                this.seasonManager.addSeason(addSeason());
            }
            dispose();
        });
    }

    private void createUIComponents() {
        try {
            MaskFormatter dateFormatter = new MaskFormatter("####/##/##");
            dateFormatter.setPlaceholderCharacter('_');

            fld_startDate = new JFormattedTextField(dateFormatter);
            fld_finishDate = new JFormattedTextField(dateFormatter);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isTextFieldEmpty(JFormattedTextField textField) {
        String text = textField.getText().trim();
        return text.isEmpty() || text.equals("____/__/__");
    }

    private Season addSeason() {
        ComboItem selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();

        if (selectedHotel != null) {
            season.setHotelId(selectedHotel.getKey());
            season.setHotelName(selectedHotel.getValue());
        }
        season.setStartDate(LocalDate.parse(fld_startDate.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
        season.setFinishDate(LocalDate.parse(fld_finishDate.getText(), DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        return season;
    }

}
