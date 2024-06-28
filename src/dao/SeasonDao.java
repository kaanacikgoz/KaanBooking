package dao;

import core.Database;
import entity.Pension;
import entity.Season;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {

    private final Connection connection;

    public SeasonDao() {
        this.connection = Database.getInstance();
    }

    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM public.season " +
                "ORDER BY season_id ASC";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Season season = new Season();
                season.setSeasonId(resultSet.getInt("season_id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setHotelName(resultSet.getString("hotel_name"));
                season.setStartDate(LocalDate.parse(resultSet.getString("start_date")));
                season.setFinishDate(LocalDate.parse(resultSet.getString("finish_date")));
                seasonList.add(season);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seasonList;
    }

}
