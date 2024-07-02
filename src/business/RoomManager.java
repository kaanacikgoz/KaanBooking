package business;

import dao.BookingDao;
import dao.RoomDao;
import entity.Booking;
import entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {

    private final RoomDao roomDao;
    private final BookingDao bookingDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.bookingDao = new BookingDao();
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
            rowObject[i++] = room.getAdultPrice();
            rowObject[i] = room.getRoomPrice();
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

    /*
    public ArrayList<Room> searchForRooms(String startDate, String finishDate, String city, String hotelName) {
        String query = buildSearchQuery(startDate, finishDate, city, hotelName);
        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        ArrayList<Integer> busyRoomId = getBusyRoomIds(startDate, finishDate);
        searchedRoomList.removeIf(room -> busyRoomId.contains(room.getRoomId()));

        return searchedRoomList;
    }

    private String buildSearchQuery(String startDate, String finishDate, String city, String hotelName) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT r.* FROM public.room r LEFT JOIN public.hotel h ON r.hotel_id = h.hotel_id");

        ArrayList<String> conditions = new ArrayList<>();
        if (city != null && !city.isEmpty()) {
            conditions.add(String.format("h.city = '%s'", city));
        }
        if (hotelName != null && !hotelName.isEmpty()) {
            conditions.add(String.format("h.hotel_name = '%s'", hotelName));
        }

        if (!conditions.isEmpty()) {
            queryBuilder.append(" WHERE ");
            queryBuilder.append(String.join(" AND ", conditions));
        }

        return queryBuilder.toString();
    }

    private ArrayList<Integer> getBusyRoomIds(String startDate, String finishDate) {
        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        LocalDate finish = LocalDate.parse(finishDate, DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        ArrayList<Integer> busyRoomIds = new ArrayList<>();
        ArrayList<String> conditions = new ArrayList<>();

        conditions.add(String.format("('%s' BETWEEN start_date AND finish_date)", start));
        conditions.add(String.format("('%s' BETWEEN start_date AND finish_date)", finish));
        conditions.add(String.format("(start_date BETWEEN '%s' AND '%s')", start, finish));
        conditions.add(String.format("(finish_date BETWEEN '%s' AND '%s')", start, finish));

        String orCondition = String.join(" OR ", conditions);
        String query = String.format("SELECT * FROM public.booking WHERE %s", orCondition);

        ArrayList<Booking> bookings = this.bookingDao.selectByQuery(query);
        for (Booking booking : bookings) {
            busyRoomIds.add(booking.getRoomId());
        }

        return busyRoomIds;
    }
*/

    //Evaluation Form-15
    public ArrayList<Room> searchForRooms(String startDate, String finishDate, String city, String hotelName) {

        String query = "SELECT * FROM public.room as r LEFT JOIN public.hotel as h";

        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();
        ArrayList<String> bookOrWhere = new ArrayList<>();

        joinWhere.add("r.hotel_id = h.hotel_id");

        startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        finishDate = LocalDate.parse(finishDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();

        if (city != null) where.add(String.format("h.city = '%s'", city));
        if (hotelName != null) where.add(String.format("h.hotel_name = '%s'", hotelName));

        //if (city != null && !city.isEmpty()) where.add(String.format("h.city = '%s'", city));
        //if (hotelName != null && !hotelName.isEmpty()) where.add(String.format("h.hotel_name = '%s'", hotelName));

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (!joinStr.isEmpty()) {
            query += " ON " +joinStr;
        }

        if (!whereStr.isEmpty()) {
            query += " WHERE " + whereStr;
        }

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        bookOrWhere.add(String.format("('%s' BETWEEN start_date AND finish_date)", startDate));
        bookOrWhere.add(String.format("('%s' BETWEEN start_date AND finish_date)", finishDate));
        bookOrWhere.add(String.format("(start_date BETWEEN '%s' AND '%s')", startDate, finishDate));
        bookOrWhere.add(String.format("(finish_date BETWEEN '%s' AND '%s')", startDate, finishDate));

        String bookOrWhereStr = String.join(" OR ", bookOrWhere);
        String bookQuery = String.format("SELECT * FROM public.booking WHERE %s", bookOrWhereStr);
        System.out.println(bookQuery);
        ArrayList<Booking> bookingList = this.bookingDao.selectByQuery(bookQuery);
        ArrayList<Integer> busyRoomId = new ArrayList<>();
        for (Booking booking : bookingList) {
            busyRoomId.add(booking.getRoomId());
        }
        searchedRoomList.removeIf(room -> busyRoomId.contains(room.getRoomId()));

        return searchedRoomList;
        /*
        String query = "SELECT * FROM public.room as r LEFT JOIN public.hotel as h ON r.hotel_id = h.hotel_id";

        ArrayList<String> whereClauses = new ArrayList<>();
        ArrayList<String> bookingClauses = new ArrayList<>();

        startDate = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();
        finishDate = LocalDate.parse(finishDate, DateTimeFormatter.ofPattern("yyyy/MM/dd")).toString();

        if (city != null && !city.isEmpty()) whereClauses.add(String.format("h.city = '%s'", city));
        if (hotelName != null && !hotelName.isEmpty()) whereClauses.add(String.format("h.hotel_name = '%s'", hotelName));

        if (!whereClauses.isEmpty()) {
            query += " WHERE " + String.join(" AND ", whereClauses);
        }

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        bookingClauses.add(String.format("('%s' BETWEEN start_date AND finish_date)", startDate));
        bookingClauses.add(String.format("('%s' BETWEEN start_date AND finish_date)", finishDate));
        bookingClauses.add(String.format("(start_date BETWEEN '%s' AND '%s')", startDate, finishDate));
        bookingClauses.add(String.format("(finish_date BETWEEN '%s' AND '%s')", startDate, finishDate));

        String bookingQuery = String.format("SELECT * FROM public.booking WHERE %s", String.join(" OR ", bookingClauses));
        System.out.println(bookingQuery);
        ArrayList<Booking> bookingList = this.bookingDao.selectByQuery(bookingQuery);

        ArrayList<Integer> busyRoomIds = new ArrayList<>();
        for (Booking booking : bookingList) {
            busyRoomIds.add(booking.getRoomId());
        }
        searchedRoomList.removeIf(room -> busyRoomIds.contains(room.getRoomId()));

        return searchedRoomList;

         */
    }

}
