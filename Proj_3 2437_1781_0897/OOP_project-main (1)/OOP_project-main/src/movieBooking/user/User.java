package movieBooking.user;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import movieBooking.common.*;
import movieBooking.events.BookableEvent;
import movieBooking.events.EventViewer;
import movieBooking.events.ModifiableEvent;
import movieBooking.ui.loginOrSignup;

public class User extends GeneralUser  implements loginOrSignup, Serializable{
	static HashMap<String, User> userBase = new HashMap<String, User>();
	// HashSet<BookableEvent> bookedEvents = new HashSet<BookableEvent>();
	public User(){
		super();
		userFile = "C:\\Users\\Vedang\\OOP\\OOP_project\\src\\movieBooking\\files\\Users.dat";
	}

	// @TODO("Implement a multithreaded way to modify the events function parameter (passed by value) and return it")
	// @Multithreaded

	public static class BookEvent {
        public int bookEventFunc(int eventID)
			throws SeatFull, SeatInvalid, EventOver, ClassNotFoundException, IOException {
		ModifiableEvent bookEve = eventMap.get(eventID);
		System.out.println("Which seat would you like to book?");
		System.out.print("Row(A to L): ");
		Scanner in = new Scanner(System.in);
		String row = in.nextLine().toUpperCase();
		System.out.print("Colmn(1 to 20): ");
		int col = Integer.parseInt(in.nextLine());
		// updateEventsFromFile();
		bookEve.book(row, col);
		eventMap.put(eventID, bookEve);
		// If seat booked, throw the error, catch it later
		// update to bookedEvents
		// update the events and return it
		return 0;
	}
    }

	static class BookEventThread implements Runnable{
        String name; // name of thread
		Thread t;
		BookEvent target;
		int eventID;

		BookEventThread(BookEvent bookE, String threadName, int eventID) {
			this.name = threadName;
			this.t = new Thread(this, name);
			this.target = bookE;
			this.eventID = eventID;
			t.start(); 
		}

		@TODO("ADD file handling")
		public void run() {
			synchronized (target)
			{

				try {
                    target.bookEventFunc(eventID);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
		}
    }
	
	// public int bookEvent(int eventID)
	// 		throws SeatFull, SeatInvalid, EventOver, ClassNotFoundException, IOException {
	// 	ModifiableEvent bookEve = eventMap.get(eventID);
	// 	System.out.println("Which seat would you like to book?");
	// 	System.out.print("Row(A to L): ");
	// 	Scanner in = new Scanner(System.in);
	// 	String row = in.nextLine().toUpperCase();
	// 	System.out.print("Row(A to L): ");
	// 	int col = Integer.parseInt(in.nextLine());
	// 	// updateEventsFromFile();
	// 	bookEve.book(row, col);
	// 	eventMap.put(eventID, bookEve);
	// 	// If seat booked, throw the error, catch it later
	// 	// update to bookedEvents
	// 	// update the events and return it
	// 	return 0;
	// }

	// @TODO("Menu not needed, just compelete the siwtch case blocks")
	// @CHECK //comment the above line and uncomment this when you're done
	@TODO("Create a menu to display all Admin options: https://docs.google.com/document/d/1NZMI5iM1FWGEaok1i-1FHB6RDJZbmt4Jd50OEH4DMtA/edit")
	// @CHECK //comment the above line and uncomment this when you're done
	@Override
	public int menu() throws TimingClash, TimeInvalid, MissingField, ClassNotFoundException, IOException {
		while (true) {
			while (true) {
				System.out.print("\033[H\033[2J");  
				System.out.println("1. View events and modify");
				// System.out.println("2. Add events");
				System.out.println("2. Go Back");
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
						BookEvent target = new BookEvent();
						BookEventThread newThread = new BookEventThread(target, "Booker", event);
		try {
            newThread.t.join();
        } catch(InterruptedException e) {
            System.out.println("Interrupted");
        }	;				
						return 1;
					case 2:
						return 3;
					case 3:
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
		int skip = 0;
		int a = 0;
		while (true) {		
			
			if (skip==0)
				a = loginOrSignup.super.menu();
			switch (a) {
				case 1:
					int login_ = login();
					System.out.println(login_);
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

	public static int updateEventsFromFile() throws IOException, ClassNotFoundException {
        FileInputStream streamIn = new FileInputStream(eventFile);
        try
        {
            ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
        
            ModifiableEvent read = new ModifiableEvent();
            try {
                while (true) {
                    read = (ModifiableEvent) objectinputstream.readObject();
                    if (read == null)
                        break;
                    eventMap.put(read.ID, read);
                }
            } catch (EOFException eofEx) {
                System.out.println("Loaded all events from File!");
                System.out.flush();
            } catch (Exception e) {
                throw e;
            }
        } catch (EOFException e){
            System.out.println("Records Empty");
            (new Scanner(System.in)).nextLine();
        }
        
        return 0;

    }

    @TODO("Populate eventMap again with changes to the events file")
    static public int updateEventsFromFile(String eventFile) throws ClassNotFoundException, IOException {
        setEventFile(eventFile);
        updateEventsFromFile();
        return 0;
    }

    @COMPLETE
    public static void setEventFile(String eventFile) {
        EventViewer.eventFile = eventFile;
    }

	private int login() throws Exception {
		FileInputStream streamIn = new FileInputStream(userFile);
		User read = new User();
		try{
			ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
			while(true) {
				read = (User) objectinputstream.readObject();
				if (read==null) break;
				userBase.put(read.username, read);
			}
		} catch (EOFException eofEx){
			System.out.println("Loaded all Users from File!");
			(new Scanner(System.in)).nextLine();
			System.out.flush();
		} catch (Exception e){
			System.out.println(e.getMessage());
			throw e;
		}
		try {
			System.out.print("\033[H\033[2J");
			Scanner scn = new Scanner(System.in);
			System.out.println("Please Enter Username: ");
			username = scn.nextLine();

			while (userBase.containsKey(username) == false) {
				try {
					System.out.println("username");
						throw new UserNotFound("User doesn't Exist\nPlease Enter Username: ");
				} catch (UserNotFound e) {
					System.out.println(e.getMessage());
					return 2;
				} catch (Exception e) {
					throw e;
				}

			}

			System.out.println("Please Enter Password");

			String tempPass = scn.nextLine();
			try {

				if (userBase.get(username).checkPassword(tempPass))
				{
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

		} catch (Exception e) {
			throw e;
		}
		return -1;
	}

	@Multithreaded
	private int signup() throws Exception {
		try {
			FileInputStream streamIn = new FileInputStream(userFile);
		User read = new User();
		try{
			ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
			while(true) {
				read = (User) objectinputstream.readObject();
				if (read==null) break;
				userBase.put(read.username, (User) read);
			}
		} catch (EOFException eofEx){
			System.out.println("Loaded all Users from File!");
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
			System.out.println(userBase.size());
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
			(new Scanner(System.in)).nextLine();
			System.out.println("Signup Completed");
			return 0;
		} 
		catch (Exception e) {
			throw e;
		}
	}
}