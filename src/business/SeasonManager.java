package business;

import dao.SeasonDao;
import entity.Season;

import javax.swing.*;
import java.util.ArrayList;

public class SeasonManager {

    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season season:seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = season.getSeasonId();
            rowObject[i++] = season.getHotelId();
            rowObject[i++] = season.getHotelName();
            rowObject[i++] = season.getStartDate();
            rowObject[i] = season.getFinishDate();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public boolean addSeason(Season season) {
        try {
            ArrayList<Season> existingSeasons = this.seasonDao.getSeasonsByHotelId(season.getHotelId());

            if (existingSeasons.size() >= 2) {
                JOptionPane.showMessageDialog(null, "This hotel already has two seasons!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }

            for (Season existingSeason : existingSeasons) {
                if (existingSeason.getStartDate().equals(season.getStartDate()) &&
                        existingSeason.getFinishDate().equals(season.getFinishDate())) {
                    JOptionPane.showMessageDialog(null, "A season with these dates already exists for this hotel!", "ERROR", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            boolean result = this.seasonDao.addSeason(season);
            if (result) {
                JOptionPane.showMessageDialog(null, "Season added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add season", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean updateSeason(Season season) {
        ArrayList<Season> existingSeasons = this.seasonDao.getSeasonsByHotelId(season.getHotelId());

        for (Season existingSeason : existingSeasons) {
            if (existingSeason.getSeasonId() != season.getSeasonId() &&
                    existingSeason.getStartDate().equals(season.getStartDate()) &&
                    existingSeason.getFinishDate().equals(season.getFinishDate())) {
                JOptionPane.showMessageDialog(null, "A season with these dates already exists for this hotel!", "ERROR", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        boolean result = this.seasonDao.updateSeason(season);
        if (result) {
            JOptionPane.showMessageDialog(null, "Season updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        return result;
    }

    public boolean deleteSeason(int season_id) {
        return this.seasonDao.deleteSeason(season_id);
    }

    public Season getByHotelId(int id) {
        return this.seasonDao.getByHotelId(id);
    }

    public Season getBySeasonId(int id) {
        return this.seasonDao.getBySeasonId(id);
    }

}
