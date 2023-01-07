package movieBooking.common;

public class MissingField extends RuntimeException {
    public MissingField(String s) {
        super(s);
    }
}
