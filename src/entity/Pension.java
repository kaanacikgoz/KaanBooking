package entity;

public class Pension {

    private int pensionId;
    private int hotelId;
    private String hotelName;
    private PensionType pensionType;

    public enum PensionType {
        ULTRA_ALL_INCLUSIVE,
        ALL_INCLUSIVE,
        BED_AND_BREAKFAST,
        FULL_BOARD,
        HALF_BOARD,
        JUST_THE_BED,
        FULL_CREDIT_EXCEPT_ALCOHOL
    }

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
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

    public PensionType getPensionType() {
        return pensionType;
    }

    public void setPensionType(PensionType pensionType) {
        this.pensionType = pensionType;
    }

}
