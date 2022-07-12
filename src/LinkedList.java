package Phone_Directory;

import java.util.*;

//import javax.mail.internet;
class LinkedList {
	LinkedNode head;

	LinkedList() {
		head = null;
	}

	public void insertAtLast(String name, Contact contact) { // insert at last 
		LinkedNode object = new LinkedNode(name, contact); 
		if (head == null) { // if the list is empty
			head = object;
		} else {
			LinkedNode temp = head;
			while (temp.getNext() != null) { // iterate through the list
				temp = temp.getNext();
			}
			temp.setNext(object); // set the next node of current node to the new node
		}
	}

	public void insetAtMiddle(String name, Contact contact) { // insert at middle
		LinkedNode object = new LinkedNode(name, contact);
		LinkedNode temp = head;
		if (temp == null) { // if the list is empty
			object.setName(name);
			object.setContact(contact);
			head = object;
			return;
		}
		while (temp.getName().compareTo(name) < 0) { // iterate through the list until the name is greater than the current name 
			if (temp.getNext() == null) // if the next node is null
				break;
			temp = temp.getNext(); // set the current node to the next node
		}
		object.setNext(temp.getNext()); // set the next node of the new node to the next node of the current node
		temp.setNext(object); // set the next node of the current node to the new node
	}

	public TreeMap<String, Contact> updatedData() { // update the data in the list
		LinkedNode temp = head;
		TreeMap<String, Contact> updatedData = new TreeMap<String, Contact>();
		while (temp != null) { // iterate through the list
			updatedData.put(temp.getName(), temp.getContact()); // add the data to the TreeMap
			temp = temp.getNext();	
		}
		return updatedData;
	}
}
