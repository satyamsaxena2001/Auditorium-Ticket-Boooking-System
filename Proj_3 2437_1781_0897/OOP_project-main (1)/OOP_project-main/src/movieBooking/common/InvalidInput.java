package movieBooking.common;

public class InvalidInput extends RuntimeException {
    public InvalidInput(String s) {
        super(s);
    }
}
