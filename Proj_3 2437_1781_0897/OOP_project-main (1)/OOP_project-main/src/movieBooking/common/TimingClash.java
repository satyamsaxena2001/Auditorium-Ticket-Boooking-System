package movieBooking.common;

public class TimingClash extends RuntimeException {
    public TimingClash(String s) {
        super(s);
    }
}
