package movieBooking.ui;

import java.io.IOException;
import java.util.*;
import movieBooking.user.User;
import movieBooking.common.MissingField;
import movieBooking.common.TimeInvalid;
import movieBooking.common.TimingClash;
import movieBooking.user.Admin;

public class Menu {
	int choice = 0;

	public int welcomePage() throws Exception {
		while (true) {
			System.out.print("\033[H\033[2J");  
			System.out.println("1. User");
			System.out.println("2. Admin");
			try {
				try {
					Scanner scn = new Scanner(System.in);
					choice = Integer.parseInt(scn.next());
					if (choice != 1 && choice != 2)
					{
						System.out.println("1 or 2 is invalid. Hit Enter to try again");
						(new Scanner(System.in)).nextLine();
					}
				} catch (Exception e) {
					// Block of code to handle errors
					System.out.println("1 or 2 is invalid. Hit Enter to try again");
					(new Scanner(System.in)).nextLine();
					continue;
				}
			} catch (Exception e) {
				// Block of code to handle errors
				System.out.println(e);
				(new Scanner(System.in)).nextLine();
				return -1;
			}

			switch (choice) {
				case 1:

					User usr = new User();
					if (usr.userCredentialPage() == 0) {
						return usr.menu();
					}
					break;
				case 2:
					Admin admin = new Admin();
					// System.out.println("999999999999");
					// (new Scanner(System.in)).nextLine();
					if (admin.userCredentialPage() == 0) {
						return admin.menu();
					}
					break;
				case 3:
					continue;
				// break;
				default:
					continue;
				// break;
			}
			// scn.close();
			return choice;
		}
	}
}
