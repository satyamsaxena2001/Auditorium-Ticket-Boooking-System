package movieBooking.events;

import java.util.*;

import movieBooking.common.COMPLETE;
import movieBooking.common.TODO;

public class Event {
	public static final int MAXCOL = 20;
	public static final char MAXROW = 'L';
	public int ID;

	class rowCol {
		char row;

		int col;

		public int compareTo(rowCol o) {
			int comp = ((Character) this.row).compareTo(o.row);
			if (comp == 0) {
				return ((Integer) this.col).compareTo((Integer) o.col);
			}
			return comp;
		}
	}

	String Title;
	String description;
	Calendar start;
	Calendar end;
	float ticketPrice;
	int remainingSeats;
	int totalSeats;
	boolean over;

	protected TreeMap<rowCol, Seat> seatMapping = new TreeMap<rowCol, Seat>(new Comparator<rowCol>() {
		public int compare(rowCol o1, rowCol o2) {
			return o1.compareTo(o2);
		}
	});

	Event() {
		ID = -1;
		Title = null;
		description = "NotSet";
		start = null;
		end = null;
		totalSeats = 0;
		ticketPrice = 0;
		remainingSeats = totalSeats;
		for (char b = 'A'; b <= MAXROW; b++) {
			for (int j = 1; j <= MAXCOL; j++) {
				rowCol temp = new rowCol();
				temp.col = j;
				temp.row = b;
				seatMapping.put(temp, new Seat());
				// seatMapping.get(temp).booked=false;
			}
		}
	}

	@TODO("Nope, still needs to show description, + an option for input to ask for row and column if they wan tot book or to GO BACK; return 1 for input or 2 for exit")
	public int displayEvent() {
		System.out.println("Event name: " + Title + '\n' + "Description: " + description + '\n' + " From: " + start.getTime().toString()
				+ " to: " + end.getTime().toString());
		showSeats();
		return 0;
	}

	public String getDescription() {
		return description;
	}

	public Calendar getEnd() {
		return end;
	}

	public int getID() {
		return ID;
	}

	public Calendar getStart() {
		return start;
	}

	public String getTitle() {
		return Title;
	}

	public float getTicketPrice() {
		return ticketPrice;
	}

	@COMPLETE
	public void showSeats() {
		System.out.print("   ");
		for (int i = 1; i <= (int)(MAXROW-'A'); i++) {
			System.out.print(i >= 10 ? " " : "  ");
		}
		System.out.println();
		for (char b = 'A'; b <= MAXROW; b++) {
			System.out.print(b + "  ");
			for (int j = 1; j <= MAXCOL; j++) {
				rowCol temp = new rowCol();
				temp.col = j;
				temp.row = b;
				System.out.print(seatMapping.get(temp).booked ? "1  " : "0  ");
			}
			System.out.println();
		}
		(new Scanner(System.in)).nextLine();
	}

	// public float showRevenue() {
	// 	return 0;
	// }
}
