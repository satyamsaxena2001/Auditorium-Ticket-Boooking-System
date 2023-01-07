package movieBooking.events;

import java.io.Serializable;
import java.util.*;

import movieBooking.common.COMPLETE;

public class ModifiableEvent extends BookableEvent implements Serializable// NOTE: Ideal way to implement this was with multiple inheritance
// Where we can have a child event, which can double inherit from both
// ModifyableEvent and BookableEvent
// We avoid Diamond of Death by ensuring that the child however, is only access
// explicitly by casting into a Modifyable
// or Bookable event (mutually exclusive and exhaustive, hence complete)
{
	// A 2d list or something to store each seat
	// use Seat class as the datatype
	boolean over;

	// Best would be a 2D map (like Hashmap), whose keys are String:row and
	// int:column
	@COMPLETE
	public ModifiableEvent() {
		super();
	}

	@COMPLETE 
	float getRevenue() throws UnsupportedOperationException {
		return ticketPrice * (totalSeats - remainingSeats);
	}

	// Taken care in event file public void showSeats() {
	// write a nice print method to print the rows and columns
	// also print row and column titles (like A,B,C)
	// use an appropriate character to indicate booked and unbooked
	// show legend at the bottom
	// return;

	// }

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public void setTitle(String title) {
		Title = title;
	}
	
	public void setTicketPrice(float ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public void setRemainingSeats(int remainingSeats) {
		this.remainingSeats = remainingSeats;
	}

	public float showRevenue() throws UnsupportedOperationException {
		return getRevenue();
	}

	public int modifyEvent() {

		while (true) {
			System.out.println("1. Set Description");
			System.out.println("2. Set End");
			System.out.println("3. Set ID");
			System.out.println("4. Set Start");
			System.out.println("5. Set Title");
			System.out.println("6. Exit");

			try  {
				Scanner scan = new Scanner(System.in);
				String choice = scan.next();
				int check = -1;
				try {
					check = Integer.parseInt(choice);
				} catch (Exception e) {
					System.out.println("Input Invalid. Try again.");
					continue;
				}
				switch (check) {
					case 1:
						setDescription(description);
						break;
					case 2:
						setEnd(end);
						break;
					case 3:
						setID(ID);
						break;
					case 4:
						setStart(start);
						break;
					case 5:
						setTitle(Title);
						break;
					case 6:
						return 0;
					default:
						System.out.println("Invalid input. Try another integer");
						continue;
				}
			} catch (Exception e) {
				// System.out.println(e.getMessage());
				throw e;
				// return -1;
			}
			// create a menu to ask what to modify,
			// use appropriate setter in a switch case ladder
			// return 0 for success
			// Done
			// return 0;
		}
	}
}