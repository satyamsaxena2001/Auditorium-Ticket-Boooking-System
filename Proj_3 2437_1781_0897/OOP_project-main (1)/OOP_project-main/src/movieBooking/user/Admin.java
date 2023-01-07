package movieBooking.user;

import java.io.*;
import java.time.Month;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.xml.stream.EventFilter;

import movieBooking.common.*;
import movieBooking.events.*;
import movieBooking.ui.loginOrSignup;

public class Admin extends GeneralUser implements loginOrSignup, Serializable{
	static HashMap<String, Admin> userBase = new HashMap<String, Admin>();
	public Admin(){
		super();
		userFile = "C:\\Users\\Vedang\\OOP\\OOP_project\\src\\movieBooking\\files\\Admins.dat";
		
	}
	@TODO("Implement a multithreaded way to modify the events function parameter (passed by value) and return it")
	// This function is just the UI implementation, where you ask for which event,
	// and what modification
	// unless that has already been implemented
	// We use another function to do the actual multithreaded add
	@Multithreaded
	public int modifyEvent(int eventID)
			throws SeatFull, SeatInvalid, EventOver {
		
		// If seat booked, throw the error, catch it later
		// update to bookedEvents
		// update the events and return it
		return 0;
	}
	
	@COMPLETE
	@Multithreaded
	public int addEvent(int newID)
			throws TimingClash, TimeInvalid, MissingField, ClassNotFoundException, IOException {
		// If seat booked, throw the error, catch it later
		// update to bookedEvents
		// update the events and return it
		ModifiableEvent newEvent = new ModifiableEvent();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter details of the new event when prompted: ");
		System.out.print("\nTitle: ");
		newEvent.setID(newID);
		newEvent.setTitle(in.nextLine());
		System.out.print("\nDescription: ");
		newEvent.setDescription(in.nextLine());
		System.out.print("\nStart Date (DD/MM/YYYY): \nDD:");
		Calendar temp = Calendar.getInstance();
		int yr = 0;
		int month = 0;
		int day = 0;
		int hr = 0;
		int min = 0;
		int sec = 0;
		yr = Integer.parseInt(in.next());
		System.out.print("\nMM: ");
		month = Integer.parseInt(in.next());
		System.out.print("\nYYYY: ");
		day = Integer.parseInt(in.next());
		System.out.print("\nStart Time (hh:mm:ss) in 24 hr format): \nhh: ");
		hr = Integer.parseInt(in.next());
		System.out.print("\nmm: ");
		min = Integer.parseInt(in.next());
		System.out.print("\nss: ");
		sec = Integer.parseInt(in.next());
		temp.set(hr, month, day, day, min, sec);
		newEvent.setStart(temp);
		// System.out.println(newEvent.getStart().getTime().toString());
		// System.out.println(temp.getTime().toString());

		System.out.print("\nEnd Date (DD/MM/YYYY): \nDD:");
		yr = Integer.parseInt(in.next());
		System.out.print("\nMM: ");
		month = Integer.parseInt(in.next());
		System.out.print("\nYYYY: ");
		day = Integer.parseInt(in.next());
		System.out.print("\nEnd Time (hh:mm:ss) in 24 hr format): \nhh: ");
		hr = Integer.parseInt(in.next());
		System.out.print("\nmm: ");
		min = Integer.parseInt(in.next());
		System.out.print("\nss: ");
		sec = Integer.parseInt(in.next());
		temp.set(hr, month, day, day, min, sec);
		newEvent.setEnd(temp);
		newEvent.setID(newID);

		System.out.print("\nPrice: ");
		newEvent.setTicketPrice(in.nextFloat());
		newEvent.setTotalSeats((Event.MAXCOL)*(Event.MAXROW-'A'+1));
		newEvent.setRemainingSeats(0);
		if (eventMap.containsKey(newID)) return -2;
		Admin.eventMap.put(newID, newEvent);
		FileOutputStream fout = new FileOutputStream(Admin.eventFile);;
			
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		
		Admin.eventMap.forEach((k,v)->
		{
			try {
				oos.writeObject(v);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e){
				throw e;
			}
		});
		return 0;
	}

	@TODO("Create a menu to display all Admin options: https://docs.google.com/document/d/1NZMI5iM1FWGEaok1i-1FHB6RDJZbmt4Jd50OEH4DMtA/edit")
	// @CHECK //comment the above line and uncomment this when you're done
	@Override
	public int menu() throws TimingClash, TimeInvalid, MissingField, ClassNotFoundException, IOException {
		while (true) {
			while (true) {
				System.out.print("\033[H\033[2J");  
				System.out.println("1. View events and modify");
				System.out.println("2. Add events");
				System.out.println("3. Go Back");
				String input;
				int choice = -1;
				try {
					Scanner sc = new Scanner(System.in);
					input = sc.next();
					choice = Integer.parseInt(input);
				} catch (Exception e) {
					System.out.println("Input Invalid. Try again.");

					continue;
				}
				switch (choice) {
					case 1:
						int event = viewEvents(true);
						eventMap.get(event).displayEvent();						
						return 1;
					case 2:
						int subChoice = addEvent(eventMap.size()+1);
						
						// sc.close();
						return 2;
					case 3:
						// sc.close();
						return 3;
					default:
						break;
					
				}
				return -1;
				
			}
		}
	}


	@COMPLETE
	public int userCredentialPage() throws Exception {
		// call loginOrSignup
		// menu();
		int skip = 0;
		int a = 0;
		while (true) {		
			
			if (skip==0)
				a = loginOrSignup.super.menu();
			
			switch (a) {
				case 1:
					int login_ = login();
					if (login_ == 0)
						return 0;
					else if (login_ == 2) {
						System.out.println("Do you want to sign up?(y for yes, anything else for no)");
						Scanner scn = new Scanner(System.in);
						try {
							if (scn.next().toLowerCase().charAt(0) == 'y')
								{a = 2;
								skip = 0;}
							else
								a = 1;
								skip++;
								continue;
						} catch (Exception e) {
							throw e;
						}
					} else if (login_ == 3) {
						continue;
					} else if (login_ == -1) {
						return -1;
					} else
						return -1;
					// continue;
				case 2:
					if (signup() == 0) {
						return 0;
					} else
						break;
				case 3:
					return 3;
				default:
					continue;
			}
		}
	}



	private int login() throws Exception {
		// implement login here
		// accept username
		FileInputStream streamIn = new FileInputStream(userFile);
		ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
		Admin read = new Admin();
		try{
			while(true) {
				read = (Admin) objectinputstream.readObject();
				if (read==null) break;
				// System.out.println(read);
				userBase.put(read.username, (Admin) read);
			}
		} catch (EOFException eofEx){
			System.out.println("Loaded all Admins from File!");
			(new Scanner(System.in)).nextLine();
			
			System.out.flush();
		} catch (Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
		try {
			System.out.print("\033[H\033[2J");
			Scanner scn = new Scanner(System.in);
			System.out.println("Please Enter Username");
			username = scn.nextLine();

			while (userBase.containsKey(username) == false) {
				try {
					System.out.println("username");
						throw new UserNotFound("User doesn't Exist");
				} catch (UserNotFound e) {
					System.out.println(e.getMessage());
					// throw e;
					return 2;
				} catch (Exception e) {
					// System.out.println(e.getMessage());
					throw e;
					// return -1;
				}

			}

			System.out.println("Please Enter Password");

			String tempPass = scn.nextLine();
			try {

				if (userBase.get(username).checkPassword(tempPass))
					{(new Scanner(System.in)).nextLine();
					return 0;
				}
				else {
					System.out.println("Wrong Password!!");
					System.out.println("Would you like to exit? (y for yes, anything else for no)");
					try {
						if (scn.next().toLowerCase().charAt(0) == 'y')
							return 3;
					} catch (Exception e) {
						throw e;
					}
				}

			} catch (Exception e) {
				throw e;
			}
			// return 0;

		} catch (Exception e) {
			throw e;
		}
		return -1;
	}

	@Multithreaded
	@COMPLETE
	private int signup() throws Exception {
		try {
			FileInputStream streamIn = new FileInputStream(userFile);
		Admin read = new Admin();
		try{
			ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
			while(true) {
				read = (Admin) objectinputstream.readObject();
				if (read==null) break;
				// System.out.println(read);
				userBase.put(read.username, (Admin) read);
			}
		} catch (EOFException eofEx){
			// eofEx.printStackTrace();
			System.out.println("Loaded all Admins from File!");
			(new Scanner(System.in)).nextLine();
			System.out.flush();
		} 
		catch (Exception e){
			System.out.println(e.getMessage());
			throw e;
			// System.out.flush();
		}
			System.out.print("\033[H\033[2J");
			Scanner scn = new Scanner(System.in);
			System.out.println("Please Create a Username: ");
			String newUsername = scn.nextLine();
			while (userBase.containsKey(newUsername) == true) {
				System.out.println("Username already exists\n Please Create a Username:");
				newUsername = scn.nextLine();
			}
			username = newUsername;

			System.out.println("Please Create a Password: ");

			password = scn.nextLine();
			userID = userBase.size() + 1;
			userBase.put(newUsername, this);
			FileOutputStream fout = new FileOutputStream(userFile);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			userBase.put(newUsername, this);
			userBase.forEach((k,v)->{
				try {
					oos.writeObject(v);
				} catch (Exception e)
				{
					try {
						throw e;
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
			System.out.println("Signup Completed");
			(new Scanner(System.in)).nextLine();
			return 0;
		} 
		catch (Exception e) {
			throw e;
		}
	}
}
