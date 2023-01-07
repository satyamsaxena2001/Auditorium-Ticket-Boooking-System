package movieBooking.events;

//COMPLETE
public class Seat {
    int seatID = -1;
    boolean booked = false;
    String row;
    int column;
    int userID;

    Seat() {
        seatID = -1;
        booked = false;
        row = null;
        column = -1;
        userID = -1;
        // is_avail = true;
    }

    public int getSeatID() {
        return seatID;
    }

    public int getColumn() {
        return column;
    }

    public String getRow() {
        return row;
    }

    public boolean getBooked() {
        return booked;
    }
}
