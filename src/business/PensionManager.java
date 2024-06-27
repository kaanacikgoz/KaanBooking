package business;

import dao.HotelDao;
import dao.PensionDao;
import entity.Hotel;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {

    private final PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for (Pension pension:pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = pension.getPensionId();
            rowObject[i++] = pension.getHotelId();
            rowObject[i++] = pension.getHotelName();
            rowObject[i] = pension.getPensionType();
            pensionList.add(rowObject);
        }
        return pensionList;
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public boolean addPension(Pension pension) {
        return this.pensionDao.addPension(pension);
    }

    public boolean deletePension(int hotel_id) {
        return this.pensionDao.deletePension(hotel_id);
    }

}
