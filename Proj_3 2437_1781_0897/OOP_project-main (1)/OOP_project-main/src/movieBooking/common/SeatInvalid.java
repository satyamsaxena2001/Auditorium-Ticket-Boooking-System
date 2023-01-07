package movieBooking.common;

public class SeatInvalid extends RuntimeException {
    public SeatInvalid(String s) {
        super(s);
    }
}
