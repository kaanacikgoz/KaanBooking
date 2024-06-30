package dao;

import core.Database;
import entity.Pension;
import entity.Room;
import entity.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
                room.setAdultPrice(resultSet.getDouble("adult_price"));
                roomList.add(room);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return roomList;
    }

    public boolean addRoom(Room room) {
        String query = "INSERT INTO public.room (hotel_id,pension_id,season_id,room_type,room_stock,bed_num," +
                "square_meters,tv,mini_bar,game_console,hotel_safe,projection,child_price,adult_price) "+
                "VALUES ( " +
                "?,?,?,?,?,?,?,?,?,?,?,?,?,?" +
                " )";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getHotelId());
            preparedStatement.setInt(2,room.getPensionId());
            preparedStatement.setInt(3,room.getSeasonId());
            preparedStatement.setString(4,room.getRoomType().toString());
            preparedStatement.setInt(5,room.getRoomStock());
            preparedStatement.setInt(6,room.getBedNum());
            preparedStatement.setDouble(7,room.getSquareMeters());
            preparedStatement.setBoolean(8,room.isTelevision());
            preparedStatement.setBoolean(9,room.isMiniBar());
            preparedStatement.setBoolean(10,room.isGameConsole());
            preparedStatement.setBoolean(11,room.isHotelSafe());
            preparedStatement.setBoolean(12,room.isProjection());
            preparedStatement.setDouble(13,room.getChildPrice());
            preparedStatement.setDouble(14,room.getAdultPrice());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean updateRoom(Room room) {
        String query = "UPDATE public.room " +
                "SET hotel_id=?, pension_id=?, season_id=?, room_type=?,room_stock=?,bed_num=?," +
                "square_meters=?,tv=?,mini_bar=?,game_console=?,hotel_safe=?,projection=?,child_price=?,adult_price=? " +
                "WHERE room_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getHotelId());
            preparedStatement.setInt(2,room.getPensionId());
            preparedStatement.setInt(3,room.getSeasonId());
            preparedStatement.setString(4,room.getRoomType().toString());
            preparedStatement.setInt(5,room.getRoomStock());
            preparedStatement.setInt(6,room.getBedNum());
            preparedStatement.setDouble(7,room.getSquareMeters());
            preparedStatement.setBoolean(8,room.isTelevision());
            preparedStatement.setBoolean(9,room.isMiniBar());
            preparedStatement.setBoolean(10,room.isGameConsole());
            preparedStatement.setBoolean(11,room.isHotelSafe());
            preparedStatement.setBoolean(12,room.isProjection());
            preparedStatement.setDouble(13,room.getChildPrice());
            preparedStatement.setDouble(14,room.getAdultPrice());
            preparedStatement.setInt(15,room.getHotelId());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean deleteRoom(int room_id) {
        String query = "DELETE FROM public.room " +
                "WHERE room_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, room_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Room getByRoomId(int id) {
        Room room=null;
        String query = "SELECT * FROM public.room " +
                "WHERE room_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                room = new Room();
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
                room.setAdultPrice(resultSet.getDouble("adult_price"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return room;
    }

}
