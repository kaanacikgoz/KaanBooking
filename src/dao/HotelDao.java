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

}
