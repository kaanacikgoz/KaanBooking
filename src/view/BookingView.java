package view;


import business.BookingManager;
import entity.Booking;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class BookingView extends JFrame {
    private JPanel container;
    private JLabel lbl_header;
    private JTextField fld_name;
    private JTextField fld_tc;
    private JTextField fld_mail;
    private JTextField fld_phone;
    private JLabel lbl_name;
    private JLabel lbl_tc;
    private JLabel lbl_mail;
    private JLabel lbl_phone;
    private JTextField fld_note;
    private JLabel lbl_note;
    private JTextField fld_childNum;
    private JTextField fld_adultNum;
    private JLabel lbl_childNum;
    private JLabel lbl_adultNum;
    private JButton btn_calculate;
    private JTextField fld_totalPrice;
    private JButton btn_save;
    private JLabel lbl_totalPrice;
    private JFormattedTextField fld_startDate;
    private JFormattedTextField fld_finishDate;
    private JLabel lbl_startDate;
    private JLabel lbl_finishDate;
    private final Booking booking;
    private final int room_Id;
    private final BookingManager bookingManager;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public BookingView(int roomId,Booking booking, String start_date, String finish_date) {
        this.add(container);
        this.booking = booking;
        this.bookingManager = new BookingManager();
        this.room_Id = roomId;
        this.setSize(650,550);
        this.setTitle("Booking View");
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x,y);
        this.setVisible(true);

        this.fld_startDate.setText(start_date);
        this.fld_finishDate.setText(finish_date);

        setBookingInfo();
        saveButton();
    }

    private void saveButton() {
        btn_save.addActionListener(e -> {
            if (this.booking.getBookingId() != 0) {
                this.bookingManager.updateBooking(addBooking());
            } else {
                this.bookingManager.addBooking(addBooking());
                JOptionPane.showMessageDialog(null, "Booking added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            dispose();
        });
    }

    private Booking addBooking() {
        if(this.booking.getBookingId()!=0){
            System.out.println(this.booking.getRoomId());
            booking.setRoomId(this.booking.getRoomId());
        }
        else{
            System.out.println(this.booking.getRoomId());
            booking.setRoomId(room_Id);
        }
        booking.setCustomerName(this.fld_name.getText());
        booking.setCustomerTc(this.fld_tc.getText());
        booking.setCustomerMail(this.fld_mail.getText());
        booking.setCustomerPhone(this.fld_phone.getText());
        booking.setCustomerNote(this.fld_note.getText());
        try {
            booking.setStartDate(LocalDate.parse(this.fld_startDate.getText(), dateFormatter));
            booking.setFinishDate(LocalDate.parse(this.fld_finishDate.getText(), dateFormatter));
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy/MM/dd", "Error", JOptionPane.ERROR_MESSAGE);
        }
        booking.setChildNumber(Integer.parseInt(this.fld_childNum.getText()));
        booking.setAdultNumber(Integer.parseInt(this.fld_adultNum.getText()));
        booking.setTotalPrice(Double.parseDouble(this.fld_totalPrice.getText()));
        return booking;
    }

    private void setBookingInfo() {
        this.fld_name.setText(booking.getCustomerName());
        this.fld_tc.setText(booking.getCustomerTc());
        this.fld_mail.setText(booking.getCustomerMail());
        this.fld_phone.setText(booking.getCustomerPhone());
        this.fld_note.setText(booking.getCustomerNote());
        this.fld_childNum.setText(String.valueOf(booking.getChildNumber()));
        this.fld_adultNum.setText(String.valueOf(booking.getAdultNumber()));
        this.fld_totalPrice.setText(String.valueOf(booking.getTotalPrice()));
    }

}
