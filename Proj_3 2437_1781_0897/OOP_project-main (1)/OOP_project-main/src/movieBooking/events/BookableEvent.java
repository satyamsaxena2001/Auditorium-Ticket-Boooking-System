package movieBooking.events;

import movieBooking.common.Multithreaded;

public class BookableEvent extends Event {

	public BookableEvent() {
		super();
		totalSeats = MAXCOL * (int) (MAXROW - 'A' + 1);
		remainingSeats = totalSeats;
	}

	@Multithreaded
	public int book(String roww, int column) throws UnsupportedOperationException {
		// if over is true, then just print that event is over and return 2
		if (over == true) {
			System.out.println("Oops !  The event is over.");
			return 2;
		}
		// try: Check if the seat is valid
		// String temp=roww;
		// temp+=String.valueOf(column);

		try {
			if ((int)roww.charAt(0) >= (int)'A' && column >= 1 && (int)roww.charAt(0) <= (int)MAXROW && column <= MAXCOL) {
				// continue;
			} else {
				throw new ArrayIndexOutOfBoundsException();
			}
		} catch (

		ArrayIndexOutOfBoundsException e) {
			// System.out.println(e.getMessage());
			throw e;
			// return -1;
		}

		// catch: type error
		// catch: out of range error (checking whether the seat even exists or not)
		rowCol r1 = new rowCol();
		r1.row = roww.charAt(0);
		r1.col = column;

		if (seatMapping.get(r1).booked == true)

		{
			return 1;
		} else {
			Seat s = seatMapping.get(r1);
			s.booked = true;
			s.userID = 99192;// *enter user id that we got from the file
			remainingSeats--;
			return 0;
		}
		// if booked, print its booked, return some value other than 0
		// else change the value of the booked variable of the Seat which is the value
		// of the map, change userID appropriately, return 0
		// return 0;
	}
}
