package Phone_Directory;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Trie {
	private TrieNode root;

	/**
	 * Add the give word into the Trie
	 * 
	 * @param word
	 */
	public void addWord(String word) {
		if (root == null) {
			root = new TrieNode(' ');
		}
		TrieNode start = root;
		char[] characters = word.toCharArray();
		for (char c : characters) {
			if (start.getChilds().size() == 0) { // if there is no childs in the node
				TrieNode newNode = new TrieNode(c); // create a new node
				start.getChilds().add(newNode); // add the new node to the childs list 
				start = newNode;
			} else {
				ListIterator<TrieNode> iterator = start.getChilds().listIterator(); 
				TrieNode node = null;
				while (iterator.hasNext()) { // iterate through the childs list
					node = iterator.next();  
					if (node.getCharacter() >= c) // if the node character is greater than the current character 
						break; 
				} 
				if (node.getCharacter() == c) { // if the node character is equal to the current character
					start = node; // set the start node to the current node
				} else {
					TrieNode newNode = new TrieNode(c);  
					iterator.add(newNode);
					start = newNode;

				}
			}
		}
		start.isEnd = true;

	}

	/**
	 * This method takes and prefix string and returns all possible string that can
	 * be formed from the given prefix
	 * 
	 * @param prefix
	 * @return
	 */
	public List<String> search(String prefix) { 
		if (prefix == null || prefix.isEmpty())
			return null;

		char[] chars = prefix.toCharArray();
		TrieNode start = root;
		boolean flag = false;
		for (char c : chars) {
			if (start.getChilds().size() > 0) { // if there is childs in the node
				List<TrieNode> list = new LinkedList<TrieNode>();

				list = start.getChilds(); // get the childs list

				for (TrieNode node : list) {
					if (node.getCharacter() == c) {
						start = node;
						flag = true;
						break; // if the current character is equal to the node character, set the start node to the current node and break the loop
					}
				}
			} else { // if there is no childs in the node
				flag = false;
				break;
			}
		}
		if (flag) {
			List<String> matches = getAllWords(start, prefix); // get all the possible words from the start node
			return matches;
		}

		return null;
	}

	/**
	 * This method returns list string that can be formed from the given node
	 * 
	 * @param start  : Node from to start
	 * @param prefix : String prefix that was formed till start node
	 * @return
	 */
	private List<String> getAllWords(TrieNode start, final String prefix) {
		List<String> list = new LinkedList<String>();

		if (start.isEnd) { // if the node is end node
			list.add(prefix);
		}

		if (start.getChilds().size() != 0) { // if there is childs in the node 
			List<TrieNode> listreturn = new LinkedList<TrieNode>();

			listreturn.addAll(start.getChilds()); // get the childs list and add it to the listreturn list 

			for (TrieNode n : listreturn) {

				list.addAll(getAllWords(n, prefix + n.getCharacter())); // get all the possible words from the childs and add it to the list
			}

		}
		return list;

	}
}