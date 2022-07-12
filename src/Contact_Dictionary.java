package Phone_Directory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Contact_Dictionary {
	// HashMap contact
	static TreeMap<String, Contact> contact;
	// Br is use to fetch data from the file
	BufferedReader br;

	// initialize value from the file
	Contact_Dictionary() throws IOException { // initialize the contact dictionary from the file 
		contact = new TreeMap<String, Contact>();
		File newfile = new File("contact_Main.csv");
		if (newfile.createNewFile()) {
			System.out.println("File created: " + newfile.getName());
		}

		try {
			br = new BufferedReader(new FileReader(newfile));
			String str = new String();
			while ((str = br.readLine().toString()) != null) {
				String[] data = str.split("-");
				Contact obj = new Contact();
				obj.setAddress(data[1]);
				obj.setMobile(data[2]);
				obj.setEmail(data[3]);
				contact.put(data[0], obj);
			}
			br.close();
		} catch (Exception e) {
			return;
		}
	}

	public static String filtername(String prefix) { // Trie Functionality is used for the searching by the name
														// prefix...
		Trie objc = new Trie();
		for (String key : contact.keySet()) { // iterate through the contact dictionary
			objc.addWord(key);
		}
		String ansfilter = "";
		List<String> display = objc.search(prefix); // search the prefix in the Trie
		// int i = 0;
		if (display != null) {
			for (String str : display) {
				Contact a = contact.get(str); // get the contact object from the contact dictionary
				ansfilter = ansfilter + "\n" + str + "-" + a.getMobile() + "-" + a.getAddress() + "-" + a.getEmail();
			}
			ansfilter = ansfilter.replaceAll("(?m)^[ \t]*\r?\n", ""); // remove empty line of the string
			return ansfilter;
		} else {
			return null;
		}
	}

	// Delete Key Done
	public static void deleteData(String name) {
		contact.remove(name); // remove the contact from the contact dictionary
		return;
	}

	// Insert DOne
	public static void insertData(String name, String number, String address, String emailid) { // insert the contact into the
																								// contact dictionary
		LinkedList obj = new LinkedList();
		boolean flag = true;
		for (Map.Entry<String, Contact> a : contact.entrySet()) {
			if (a.getKey().compareTo(name) == 0) { // check if the name is already present in the contact dictionary
				flag = false;
				break;
			}
			obj.insertAtLast(a.getKey(), a.getValue()); // insert the contact into the linked list in the sorted order of
														// the name
		}
		if (flag == true) { // if the name is not present in the contact dictionary
			if (address.compareTo("") == 0)
				address = "NA";
			if (emailid.compareTo("") == 0)
				emailid = "NA";
			Contact newContact = new Contact();
			newContact.setAddress(address);
			newContact.setEmail(emailid);
			newContact.setMobile(number);
			obj.insetAtMiddle(name, newContact); // insert the contact into the linked list in the sorted order of the name 
			contact = obj.updatedData(); // update the contact dictionary with the new contact
			return;
		}
	}

	public static void exportData() { // export the contact dictionary to the file 
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter("contact_Main.csv"));
			for (Map.Entry<String, Contact> ob : contact.entrySet()) { // iterate through the contact dictionary
				String str = new String();
				str = ob.getKey() + "-" + ob.getValue().getAddress() + "-" + ob.getValue().getMobile() + "-"
						+ ob.getValue().getEmail(); // get the contact object from the contact dictionary
				bw.write(str);
				bw.newLine();
			}
		} catch (Exception e) {
			return;
		}
		try {
			bw.close();
		} catch (Exception e) {
			return;
		}
	}

	public static boolean checkcontact(String name) { // check if the name is present in the contact dictionary
		Contact updatecontact = contact.get(name); // get the contact object from the contact dictionary
		if (updatecontact == null) {
			return false;
		} else {
			return true;
		}
	}

	public static void updatephone(String name, String num) { // update the phone number of the contact
		Contact updatecontact = contact.get(name); // get the contact object from the contact dictionary
		if (updatecontact == null) {
			return;
		} else {
			updatecontact.setMobile(num); // update the phone number of the contact
		}
		exportData(); // export the contact dictionary to the file
		return;
	}

	public static void updateaddress(String name, String add) {
		Contact updatecontact = contact.get(name);
		if (updatecontact == null) {
			return;
		} else {
			updatecontact.setAddress(add);
		}
		exportData();
		return;
	}

	public static void updatemail(String name, String email) {
		Contact updatecontact = contact.get(name);
		if (updatecontact == null) {
			return;
		} else {
			updatecontact.setEmail(email);
		}
		exportData();
		return;
	}
}