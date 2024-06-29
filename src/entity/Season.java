package entity;

import java.time.LocalDate;

public class Season {

    private int seasonId;
    private int hotelId;
    private String hotelName;
    private LocalDate startDate;
    private LocalDate finishDate;
    private LocalDate startDate2;
    private LocalDate finishDate2;

    public Season() {

    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getStartDate2() {
        return startDate2;
    }

    public void setStartDate2(LocalDate startDate2) {
        this.startDate2 = startDate2;
    }

    public LocalDate getFinishDate2() {
        return finishDate2;
    }

    public void setFinishDate2(LocalDate finishDate2) {
        this.finishDate2 = finishDate2;
    }

}
