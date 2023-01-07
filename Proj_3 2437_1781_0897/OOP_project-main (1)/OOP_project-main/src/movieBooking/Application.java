package movieBooking;

import java.io.IOException;

import movieBooking.common.MissingField;
import movieBooking.common.TimeInvalid;
import movieBooking.common.TimingClash;
import movieBooking.ui.Menu;

public class Application {

	public static void main(String[] args) throws Exception {
		while (true) {
			(new Menu()).welcomePage();
		}

	}

}
