package Phone_Directory;

import java.io.IOException;

public class Driver { // Driver class 

	public static void main(String[] args) throws IOException {
		Contact_Dictionary contact = new Contact_Dictionary();
		MainMenu.main(args); // call the main menu
	}
}