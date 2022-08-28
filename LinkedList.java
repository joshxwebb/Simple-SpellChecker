/**
 * class to represent a linked list with a reference to the head and tail node
 * @author Josh Webb
 *
 */
public class LinkedList {
	private Node head;
	private Node tail;
	
	public LinkedList() {
		head = null;
		tail = null;
	}
	
	public boolean isEmpty() {
		return (head == null); // also assumes tail is null as well
	}
	
	// addToEnd (add a new Node to tail end)
	public void addToEnd(String d) {
		Node nodeToAdd = new Node(d);
		if (isEmpty()) {
			head = nodeToAdd;
		} else {
			tail.next = nodeToAdd;
		}
		tail = nodeToAdd;
	}

	// removeFromBeginning (remove the head)
	public String removeFromBeginning() {
		if (isEmpty()) {
			return null; // have to return something
		} else {
			String returnVal = head.data;
			if (head == tail) {
				head = null;
				tail = null;
				
			} else {
				head = head.next;
			}
			return returnVal;
		}
	}
	
	public boolean search(String lookFor) {
		Node temp = head;
		while (temp != null) {
			if (temp.data.equals(lookFor)) {
				return true;
			} else {
				temp = temp.next;
			}
		}
		return false;
	}
}
