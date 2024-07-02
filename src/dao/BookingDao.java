package dao;

import core.Database;
import entity.Booking;

import java.sql.*;
import java.util.ArrayList;

public class BookingDao {

    private final Connection connection;

    public BookingDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<Booking> findAll() {
        ArrayList<Booking> bookingList = new ArrayList<>();
        String query = "SELECT * FROM public.booking ";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setCustomerName(resultSet.getString("customer_name"));
                booking.setCustomerTc(resultSet.getString("customer_tc"));
                booking.setCustomerMail(resultSet.getString("customer_mail"));
                booking.setCustomerPhone(resultSet.getString("customer_phone"));
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setFinishDate(resultSet.getDate("finish_date").toLocalDate());
                booking.setChildNumber(resultSet.getInt("child_number"));
                booking.setAdultNumber(resultSet.getInt("adult_number"));
                booking.setTotalPrice(resultSet.getDouble("total_price"));
                booking.setCustomerNote(resultSet.getString("customer_note"));
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingList;
    }

    public boolean addBooking(Booking booking) {
        String query = "INSERT INTO public.booking (room_id,customer_name,customer_tc,customer_mail,customer_phone," +
                "start_date,finish_date,child_number,adult_number,total_price,customer_note) " +
                "VALUES ( " +
                "?,?,?,?,?,?,?,?,?,?,?" +
                " )";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,booking.getRoomId());
            preparedStatement.setString(2,booking.getCustomerName());
            preparedStatement.setString(3,booking.getCustomerTc());
            preparedStatement.setString(4,booking.getCustomerMail());
            preparedStatement.setString(5,booking.getCustomerPhone());
            preparedStatement.setDate(6, Date.valueOf(booking.getStartDate()));
            preparedStatement.setDate(7,Date.valueOf(booking.getFinishDate()));
            preparedStatement.setInt(8,booking.getChildNumber());
            preparedStatement.setInt(9,booking.getAdultNumber());
            preparedStatement.setDouble(10,booking.getTotalPrice());
            preparedStatement.setString(11,booking.getCustomerNote());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean updateBooking(Booking booking) {
        String query = "UPDATE public.booking " +
                "SET room_id=?,customer_name=?,customer_tc=?,customer_mail=?,customer_phone=?," +
                "start_date=?,finish_date=?,child_number=?,adult_number=?,total_price=?,customer_note=?" +
                "WHERE booking_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,booking.getRoomId());
            preparedStatement.setString(2,booking.getCustomerName());
            preparedStatement.setString(3,booking.getCustomerTc());
            preparedStatement.setString(4,booking.getCustomerMail());
            preparedStatement.setString(5,booking.getCustomerPhone());
            preparedStatement.setDate(6, Date.valueOf(booking.getStartDate()));
            preparedStatement.setDate(7,Date.valueOf(booking.getFinishDate()));
            preparedStatement.setInt(8,booking.getChildNumber());
            preparedStatement.setInt(9,booking.getAdultNumber());
            preparedStatement.setDouble(10,booking.getTotalPrice());
            preparedStatement.setString(11,booking.getCustomerNote());
            preparedStatement.setInt(12,booking.getBookingId());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean deleteBooking(int booking_id) {
        String query = "DELETE FROM public.booking " +
                "WHERE booking_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, booking_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Booking getByBookingId(int id) {
        Booking booking=null;
        String query = "SELECT * FROM public.booking " +
                "WHERE booking_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setCustomerName(resultSet.getString("customer_name"));
                booking.setCustomerTc(resultSet.getString("customer_tc"));
                booking.setCustomerMail(resultSet.getString("customer_mail"));
                booking.setCustomerPhone(resultSet.getString("customer_phone"));
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setFinishDate(resultSet.getDate("finish_date").toLocalDate());
                booking.setChildNumber(resultSet.getInt("child_number"));
                booking.setAdultNumber(resultSet.getInt("adult_number"));
                booking.setTotalPrice(resultSet.getDouble("total_price"));
                booking.setCustomerNote(resultSet.getString("customer_note"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booking;
    }

    public ArrayList<Booking> selectByQuery(String query) {
        ArrayList<Booking> bookingList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Booking booking = new Booking();
                booking.setBookingId(resultSet.getInt("booking_id"));
                booking.setRoomId(resultSet.getInt("room_id"));
                booking.setCustomerName(resultSet.getString("customer_name"));
                booking.setCustomerTc(resultSet.getString("customer_tc"));
                booking.setCustomerMail(resultSet.getString("customer_mail"));
                booking.setCustomerPhone(resultSet.getString("customer_phone"));
                booking.setStartDate(resultSet.getDate("start_date").toLocalDate());
                booking.setFinishDate(resultSet.getDate("finish_date").toLocalDate());
                booking.setChildNumber(resultSet.getInt("child_number"));
                booking.setAdultNumber(resultSet.getInt("adult_number"));
                booking.setTotalPrice(resultSet.getDouble("total_price"));
                booking.setCustomerNote(resultSet.getString("customer_note"));
                bookingList.add(booking);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return bookingList;
    }

}
