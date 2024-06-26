package business;

import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel hotel:hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getCity();
            rowObject[i++] = hotel.getRegion();
            rowObject[i++] = hotel.getAddress();
            rowObject[i++] = hotel.getEmail();
            rowObject[i++] = hotel.getPhone();
            rowObject[i++] = hotel.getStar();
            rowObject[i++] = hotel.isFreeParking();
            rowObject[i++] = hotel.isFreeWifi();
            rowObject[i++] = hotel.isSwimmingPool();
            rowObject[i++] = hotel.isGym();
            rowObject[i++] = hotel.isHotelConcierge();
            rowObject[i++] = hotel.isSpa();
            rowObject[i] = hotel.isRoomService();
            hotelList.add(rowObject);
        }
        return hotelList;
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public boolean addHotel(Hotel hotel) {
        return this.hotelDao.addHotel(hotel);
    }

    public boolean updateHotel(Hotel hotel) {
        return this.hotelDao.updateHotel(hotel);
    }

    public boolean deleteHotel(int hotel_id) {
        return this.hotelDao.deleteHotel(hotel_id);
    }

}
