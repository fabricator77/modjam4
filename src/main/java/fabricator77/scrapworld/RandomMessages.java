package fabricator77.scrapworld;

import java.util.Random;

public class RandomMessages {
	public static String scrapMessages (Random random) {
		int choice = random.nextInt(5);
		if (choice == 0) {
			return "Nothing of value in this.";
		}
		else if (choice == 1) {
			return "A lot of nothing in particular.";
		}
		else if (choice == 2) {
			return "Excellent paperweight, assuming you have paper.";
		}
		else if (choice == 3) {
			return "It's a heavy lump... of something.";
		}
		else if (choice == 4) {
			return "Tie string to it, and use as a boat anchor.";
		}
		return "";
	}
}
