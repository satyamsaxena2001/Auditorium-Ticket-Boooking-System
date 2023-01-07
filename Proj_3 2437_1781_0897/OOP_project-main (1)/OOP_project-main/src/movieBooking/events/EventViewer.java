package movieBooking.events;

import java.util.Scanner;
import java.util.TreeMap;
import java.io.*;
import java.util.InputMismatchException;

import movieBooking.common.CHECK;
import movieBooking.common.COMPLETE;
import movieBooking.common.InvalidInput;
import movieBooking.common.LOW_PRIORITY;
import movieBooking.common.TODO;

public abstract class EventViewer {

    public static TreeMap<Integer, ModifiableEvent> eventMap = new TreeMap<Integer, ModifiableEvent>();
    public static String eventFile;

    public EventViewer() {
        eventFile = "C:\\Users\\Vedang\\OOP\\OOP_Project\\src\\movieBooking\\files\\Events.dat";
    }
    public EventViewer(boolean load) throws Exception {
        eventFile = "C:\\Users\\Vedang\\OOP\\OOP_Project\\src\\movieBooking\\files\\Events.dat";
        try {
            EventViewer.updateEventsFromFile(eventFile);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public EventViewer(String eventFile) throws ClassNotFoundException, IOException {
        try {
            EventViewer.updateEventsFromFile(eventFile);
        } catch (FileNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @TODO("Populate eventMap again with changes to the events file")
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

    // *update* implementing one page instead with a max of 10 events
    /**
     * @param showRevenue
     * @return
     * @throws InvalidInput
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @CHECK
    public int viewEvents(boolean showRevenue) throws InvalidInput, ClassNotFoundException, IOException {
        // updateEventsFromFile();
        for (int i = 1; i <= eventMap.size(); i++) {
            ModifiableEvent v = eventMap.get(i);
            if (showRevenue) {
                System.out.println(i + ". " + v.Title + "\t"+v.getRevenue());
            } else {
                System.out.println(i + ". " + v.Title);
            }
        }
        System.out.print("Choose an Event number: ");
        try {
            Scanner scn = new Scanner(System.in);
            int userIn = -1;
            char userNav = 0;

            try {
                userIn = scn.nextInt();
            } catch (InputMismatchException a) {
                try {
                    userNav = (char) scn.next().charAt(0);
                } catch (Exception e) {
                    throw e;
                }
            } catch (Exception e) {
                throw e;
            }

            try {
                if (userIn > 0 && userIn <= eventMap.size()) {
                    return eventMap.get(userIn).ID;
                } else if (userNav == 'n') {
                    System.out.println("NEXT");
                    return 0;
                } else if (userNav == 'p') {
                    System.out.println("Previous");
                    return 0;
                } else {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }

            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                return -3;
            } catch (Exception e) {
                throw e;
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
