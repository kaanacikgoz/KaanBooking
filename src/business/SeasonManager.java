package business;

import dao.SeasonDao;
import entity.Season;

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

}
