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
            rowObject[i++] = season.getFinishDate();
            rowObject[i++] = season.getStartDate2();
            rowObject[i] = season.getFinishDate2();
            seasonList.add(rowObject);
        }
        return seasonList;
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public boolean addSeason(Season season) {
        if (this.getByHotelId(season.getHotelId())!= null) {
            JOptionPane.showMessageDialog(null,"Hotel season already selected!", "ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Season added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        return this.seasonDao.addSeason(season);
    }

    public boolean updateSeason(Season season) {
        if (this.getByHotelId(season.getHotelId())!= null) {
            JOptionPane.showMessageDialog(null,"Hotel season already selected!", "ERROR",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Season updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        return this.seasonDao.updateSeason(season);
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
