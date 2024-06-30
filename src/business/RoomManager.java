package business;

import dao.RoomDao;
import entity.Room;
import entity.Season;

import java.util.ArrayList;

public class RoomManager {

    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room room:rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = room.getRoomId();
            rowObject[i++] = room.getHotelId();
            rowObject[i++] = room.getPensionId();
            rowObject[i++] = room.getSeasonId();
            rowObject[i++] = room.getRoomType();
            rowObject[i++] = room.getRoomStock();
            rowObject[i++] = room.getBedNum();
            rowObject[i++] = room.getSquareMeters();
            rowObject[i++] = room.isTelevision();
            rowObject[i++] = room.isMiniBar();
            rowObject[i++] = room.isGameConsole();
            rowObject[i++] = room.isHotelSafe();
            rowObject[i++] = room.isProjection();
            rowObject[i++] = room.getChildPrice();
            rowObject[i] = room.getAdultPrice();
            roomList.add(rowObject);
        }
        return roomList;
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public boolean addRoom(Room room) {
        return this.roomDao.addRoom(room);
    }

    public boolean updateRoom(Room room) {
        return this.roomDao.updateRoom(room);
    }

    public boolean deleteRoom(int room_id) {
        return this.roomDao.deleteRoom(room_id);
    }

    public Room getByRoomId(int id) {
        return this.roomDao.getByRoomId(id);
    }

}
