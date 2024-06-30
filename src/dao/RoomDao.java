package dao;

import core.Database;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {

    private final Connection connection;

    public RoomDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM public.room " +
                "ORDER BY pension_id ASC";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setHotelId(resultSet.getInt("hotel_id"));
                room.setPensionId(resultSet.getInt("pension_id"));
                room.setSeasonId(resultSet.getInt("season_id"));
                room.setRoomType(Room.RoomType.valueOf(resultSet.getString("room_type")));
                room.setRoomStock(resultSet.getInt("room_stock"));
                room.setBedNum(resultSet.getInt("bed_num"));
                room.setSquareMeters(resultSet.getDouble("square_meters"));
                room.setTelevision(resultSet.getBoolean("tv"));
                room.setMiniBar(resultSet.getBoolean("mini_bar"));
                room.setGameConsole(resultSet.getBoolean("game_console"));
                room.setHotelSafe(resultSet.getBoolean("hotel_safe"));
                room.setProjection(resultSet.getBoolean("projection"));
                room.setChildPrice(resultSet.getDouble("child_price"));
                room.setAdultPrice(resultSet.getDouble("child_price"));
                roomList.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

}
