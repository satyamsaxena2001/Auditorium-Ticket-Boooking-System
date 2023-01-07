package movieBooking.common;

public class SeatFull extends RuntimeException {
    public SeatFull(String s) {
        super(s);
    }
}
