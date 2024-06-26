package dao;

import core.Database;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {

    private final Connection connection;

    public HotelDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String query = "SELECT * FROM public.hotel";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setId(resultSet.getInt("hotel_id"));
                hotel.setName(resultSet.getString("hotel_name"));
                hotel.setCity(resultSet.getString("city"));
                hotel.setRegion(resultSet.getString("region"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setPhone(resultSet.getString("phone"));
                hotel.setStar(Hotel.Star.valueOf(resultSet.getString("star")));
                hotel.setFreeParking(resultSet.getBoolean("freeparking"));
                hotel.setFreeWifi(resultSet.getBoolean("freewifi"));
                hotel.setSwimmingPool(resultSet.getBoolean("swimmingpool"));
                hotel.setGym(resultSet.getBoolean("gym"));
                hotel.setHotelConcierge(resultSet.getBoolean("concierge"));
                hotel.setSpa(resultSet.getBoolean("spa"));
                hotel.setRoomService(resultSet.getBoolean("roomservice"));
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hotelList;
    }

    public Hotel getById(int id) {
        Hotel hotel = null;
        String query = "SELECT * FROM public.hotel " +
                        "WHERE hotel_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hotel = new Hotel();
                hotel.setId(resultSet.getInt("hotel_id"));
                hotel.setName(resultSet.getString("hotel_name"));
                hotel.setCity(resultSet.getString("city"));
                hotel.setRegion(resultSet.getString("region"));
                hotel.setAddress(resultSet.getString("address"));
                hotel.setEmail(resultSet.getString("email"));
                hotel.setPhone(resultSet.getString("phone"));
                hotel.setStar(Hotel.Star.valueOf(resultSet.getString("star")));
                hotel.setFreeParking(resultSet.getBoolean("freeparking"));
                hotel.setFreeWifi(resultSet.getBoolean("freewifi"));
                hotel.setSwimmingPool(resultSet.getBoolean("swimmingpool"));
                hotel.setGym(resultSet.getBoolean("gym"));
                hotel.setHotelConcierge(resultSet.getBoolean("concierge"));
                hotel.setSpa(resultSet.getBoolean("spa"));
                hotel.setRoomService(resultSet.getBoolean("roomservice"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hotel;
    }

    public boolean addHotel(Hotel hotel) {
        String query = "INSERT INTO public.hotel (hotel_name, city, region, address,email,phone, " +
                "star, freeparking, freewifi, swimmingpool, gym, concierge, spa, roomservice) " +
                "VALUES ( " +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                " )";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getCity());
            preparedStatement.setString(3, hotel.getRegion());
            preparedStatement.setString(4, hotel.getAddress());
            preparedStatement.setString(5, hotel.getEmail());
            preparedStatement.setString(6, hotel.getPhone());
            preparedStatement.setString(7, hotel.getStar().toString());
            preparedStatement.setBoolean(8, hotel.isFreeParking());
            preparedStatement.setBoolean(9, hotel.isFreeWifi());
            preparedStatement.setBoolean(10, hotel.isSwimmingPool());
            preparedStatement.setBoolean(11, hotel.isGym());
            preparedStatement.setBoolean(12, hotel.isHotelConcierge());
            preparedStatement.setBoolean(13, hotel.isSpa());
            preparedStatement.setBoolean(14, hotel.isRoomService());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean updateHotel(Hotel hotel) {
        String query = "UPDATE public.hotel " +
                "SET hotel_name=?, city=?, region=?, address=?, email=?, phone=?, star=? " +
                "freeparking=?, freewifi=?, swimmingpool=?, gym=?, concierge=?, spa=? " +
                "roomservice=? " +
                "WHERE hotel_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getCity());
            preparedStatement.setString(3, hotel.getRegion());
            preparedStatement.setString(4, hotel.getAddress());
            preparedStatement.setString(5, hotel.getEmail());
            preparedStatement.setString(6, hotel.getPhone());
            preparedStatement.setString(7, hotel.getStar().toString());
            preparedStatement.setBoolean(8, hotel.isFreeParking());
            preparedStatement.setBoolean(9, hotel.isFreeWifi());
            preparedStatement.setBoolean(10, hotel.isSwimmingPool());
            preparedStatement.setBoolean(11, hotel.isGym());
            preparedStatement.setBoolean(12, hotel.isHotelConcierge());
            preparedStatement.setBoolean(13, hotel.isSpa());
            preparedStatement.setBoolean(14, hotel.isRoomService());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean deleteHotel(int hotel_id) {
        String query = "DELETE FROM public.hotel " +
                        "WHERE hotel_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, hotel_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
