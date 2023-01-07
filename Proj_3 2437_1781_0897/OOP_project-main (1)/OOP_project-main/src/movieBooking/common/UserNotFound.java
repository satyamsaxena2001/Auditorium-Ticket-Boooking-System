package movieBooking.common;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String s) {
        super(s);
    }
}
