package dao;

import core.Database;
import entity.Hotel;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {

    private final Connection connection;

    public PensionDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM public.pension";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pension pension = new Pension();
                pension.setPensionId(resultSet.getInt("pension_id"));
                pension.setHotelId(resultSet.getInt("hotel_id"));
                pension.setPensionType(Pension.PensionType.valueOf(resultSet.getString("pension_name")));
                pension.setHotelName(resultSet.getString("hotel_name"));
                pensionList.add(pension);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pensionList;
    }

    public Pension getById(int id) {
        Pension pension = null;
        String query = "SELECT * FROM public.pension " +
                "WHERE pension_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pension = new Pension();
                pension.setPensionId(resultSet.getInt("pension_id"));
                pension.setHotelId(resultSet.getInt("hotel_id"));
                pension.setPensionType(Pension.PensionType.valueOf(resultSet.getString("pension_name")));
                pension.setHotelName(resultSet.getString("hotel_name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return pension;
    }

    public boolean deleteHotel(int pension_id) {
        String query = "DELETE FROM public.pension " +
                "WHERE pension_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, pension_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

}
