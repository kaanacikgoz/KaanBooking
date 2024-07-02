package business;

import dao.BookingDao;
import entity.Booking;
import entity.Pension;
import entity.Room;

import java.util.ArrayList;

public class BookingManager {

    private final BookingDao bookingDao;

    public BookingManager() {
        this.bookingDao = new BookingDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Booking> bookings) {
        ArrayList<Object[]> bookingList = new ArrayList<>();
        for (Booking booking:bookings) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = booking.getBookingId();
            rowObject[i++] = booking.getRoomId();
            rowObject[i++] = booking.getCustomerName();
            rowObject[i++] = booking.getCustomerTc();
            rowObject[i++] = booking.getCustomerMail();
            rowObject[i++] = booking.getCustomerPhone();
            rowObject[i++] = booking.getStartDate();
            rowObject[i++] = booking.getFinishDate();
            rowObject[i++] = booking.getChildNumber();
            rowObject[i++] = booking.getAdultNumber();
            rowObject[i++] = booking.getTotalPrice();
            rowObject[i] = booking.getCustomerNote();
            bookingList.add(rowObject);
        }
        return bookingList;
    }

    public ArrayList<Booking> findAll() {
        return this.bookingDao.findAll();
    }

    public Booking getByBookingId(int id) {
        return this.bookingDao.getByBookingId(id);
    }

    public boolean addBooking(Booking booking) {
        return this.bookingDao.addBooking(booking);
    }
    public boolean updateBooking(Booking booking) {
        return this.bookingDao.updateBooking(booking);
    }

    public boolean deleteBooking(int booking_id) {
        return this.bookingDao.deleteBooking(booking_id);
    }

}
