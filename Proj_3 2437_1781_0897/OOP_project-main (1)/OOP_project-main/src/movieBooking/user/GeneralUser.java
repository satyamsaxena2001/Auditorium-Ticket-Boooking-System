package movieBooking.user;

import java.io.*;
import java.util.Scanner;

import java.util.HashMap;

import movieBooking.common.CHECK;
import movieBooking.common.COMPLETE;
import movieBooking.common.MissingField;
import movieBooking.common.Multithreaded;
import movieBooking.common.TODO;
import movieBooking.common.TimeInvalid;
import movieBooking.common.TimingClash;
import movieBooking.common.UserNotFound;
import movieBooking.events.EventViewer;
//import movieBooking.ui.Admin;
import movieBooking.ui.*;

public abstract class GeneralUser extends EventViewer implements Serializable {
	// this implements login and credential checking for Admins and Users
	int choice = 0;
	int userID = -1;
	String username;
	protected String password;
	public static String userFile;
	
	@COMPLETE
	GeneralUser() {
		super();
		this.choice = 0;
		this.username = null;
		this.password = null;
	}

	@COMPLETE
	GeneralUser(String eventFile) throws ClassNotFoundException, IOException {
		super(eventFile);
		GeneralUser.setEventFile(eventFile);
		this.choice = 0;
		this.username = null;
		this.password = null;
	}

	@COMPLETE
	GeneralUser(String eventFile, String userFile) throws ClassNotFoundException, IOException {
		super(eventFile);
		this.choice = 0;
		this.username = null;
		GeneralUser.userFile = userFile;
		this.password = null;
		// try {
		// 	GeneralUser.loadUsers();
		// } catch (FileNotFoundException e) {
		// 	// System.out.println(e.getMessage());
		// 	throw e;
		// }
	}

	@COMPLETE
	GeneralUser(boolean load) throws Exception {
		super(load);
	}

	@COMPLETE
	public String getUsername() {
		return username;
	}

	@COMPLETE
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	@COMPLETE
	public int menu() throws UnsupportedOperationException, TimingClash, TimeInvalid, MissingField,
			ClassNotFoundException, IOException {
		// This is for Guest user, need not implement right now, but just in case
		// this will be overriden in child classes
		throw new UnsupportedOperationException("menu not valid for abstract class");
		// return 0;
	}
}
