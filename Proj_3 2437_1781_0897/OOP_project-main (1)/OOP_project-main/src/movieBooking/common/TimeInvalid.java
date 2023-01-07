package movieBooking.common;

public class TimeInvalid extends RuntimeException {
    public TimeInvalid(String s) {
        super(s);
    }
}
