package dao;

import core.Database;
import entity.Season;

import java.sql.*;
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

    public boolean addSeason(Season season) {
        String query = "INSERT INTO public.season (hotel_id, hotel_name, start_date, finish_date) " +
                "VALUES ( " +
                "?,?,?,?" +
                " )";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,season.getHotelId());
            preparedStatement.setString(2,season.getHotelName());
            preparedStatement.setDate(3,Date.valueOf(season.getStartDate()));
            preparedStatement.setDate(4,Date.valueOf(season.getFinishDate()));
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean updateSeason(Season season) {
        String query = "UPDATE public.season " +
                "SET hotel_id=?, hotel_name=?, start_date=? , finish_date=?" +
                "WHERE season_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,season.getHotelId());
            preparedStatement.setString(2,season.getHotelName());
            preparedStatement.setDate(3,Date.valueOf(season.getStartDate()));
            preparedStatement.setDate(4,Date.valueOf(season.getFinishDate()));
            preparedStatement.setInt(5,season.getSeasonId());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public boolean deleteSeason(int season_id) {
        String query = "DELETE FROM public.season " +
                "WHERE season_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, season_id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Season getByHotelId(int id) {
        Season season=null;
        String query = "SELECT * FROM public.season " +
                "WHERE hotel_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                season = new Season();
                season.setSeasonId(resultSet.getInt("season_id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setHotelName(resultSet.getString("hotel_name"));
                season.setStartDate(LocalDate.parse(resultSet.getString("start_date")));
                season.setFinishDate(LocalDate.parse(resultSet.getString("finish_date")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return season;
    }

    public Season getBySeasonId(int id) {
        Season season=null;
        String query = "SELECT * FROM public.season " +
                "WHERE season_id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                season = new Season();
                season.setSeasonId(resultSet.getInt("season_id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setHotelName(resultSet.getString("hotel_name"));
                season.setStartDate(LocalDate.parse(resultSet.getString("start_date")));
                season.setFinishDate(LocalDate.parse(resultSet.getString("finish_date")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return season;
    }

    public ArrayList<Season> getSeasonsByHotelId(int hotelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.season WHERE hotel_id = ?";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Season season = new Season();
                season.setSeasonId(resultSet.getInt("season_id"));
                season.setHotelId(resultSet.getInt("hotel_id"));
                season.setHotelName(resultSet.getString("hotel_name"));
                season.setStartDate(resultSet.getDate("start_date").toLocalDate());
                season.setFinishDate(resultSet.getDate("finish_date").toLocalDate());
                seasons.add(season);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return seasons;
    }

}
