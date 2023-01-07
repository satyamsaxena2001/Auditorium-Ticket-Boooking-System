package movieBooking.ui;

import java.io.IOException;
import java.util.Scanner;

import movieBooking.common.MissingField;
import movieBooking.common.TimeInvalid;
import movieBooking.common.TimingClash;

//COMPLETED
public interface loginOrSignup {
	default int menu() throws UnsupportedOperationException, TimingClash, TimeInvalid, MissingField,
			ClassNotFoundException, IOException {
		while (true) {
			System.out.print("\033[H\033[2J");
			System.out.println("1. Login");
			System.out.println("2. Signup");
			System.out.println("3. Go Back");
			String input;
			int choice = -1;
			try {
				Scanner sc = new Scanner(System.in);
				input = sc.next();
				System.out.println(choice);
				System.out.println(input);
				choice = Integer.parseInt(input);
			} catch (Exception e) {
				System.out.println("Input Invalid. Try again.");
				System.out.println(e);
				(new Scanner(System.in)).nextLine();
				continue;
			}
			switch (choice) {
				case 1:
					// sc.close();
					System.out.println("loSln35");
					(new Scanner(System.in)).nextLine();
					return 1;
				case 2:
					System.out.println("loSln39");
					(new Scanner(System.in)).nextLine();
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
		// return -1;
	}
}