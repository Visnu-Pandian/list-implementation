package project2.lists;

/**
 * Represents a node in a linked list
 * 
 * @author Visnu Pandian
 *
 * @param <E> Type variable
 */

public class Node<E> {
	E item;
	Node<E> next;

	/**
	 * Node
	 * 
	 * Constructor for Node.
	 * 
	 * @param i Value of node.
	 * 
	 * @return void
	 */

	public Node(E i) { // singular node without connection.
		this.item = i;
		next = null;
	}

	/**
	 * Node
	 * 
	 * Constructor for Node.
	 * 
	 * @param i    Value of node.
	 * @param next Next node to connect to.
	 * 
	 * @return void
	 */

	public Node(E i, Node<E> next) { // node with one connection to next element.
		this.item = i;
		this.next = next;
	}

	/**
	 * createNode
	 * 
	 * Method to call constructor.
	 * 
	 * @param i Value of node.
	 * 
	 * @return new Node<I>(i)
	 */

	public static <I> Node<I> createNode(I i) { // creating new node without connection.
		return new Node<I>(i);
	}

	/**
	 * createNode
	 * 
	 * Method to call constructor.
	 * 
	 * @param i    Value of node.
	 * @param next Next node to connect to.
	 * 
	 * @return new Node<I>(i, next)
	 */

	public static <I> Node<I> createNode(I i, Node<I> next) { // creating new node with connection.
		return new Node<I>(i, next);
	}

	/**
	 * getItem
	 * 
	 * Returns the value of the node.
	 * 
	 * @param none
	 * 
	 * @return item Value of the node.
	 */

	public E getItem() { // returning element.
		return item;
	}

	/**
	 * getNext
	 * 
	 * Returns the next node in the linkedList.
	 * 
	 * @param none
	 * 
	 * @return this.next
	 */

	public Node<E> getNext() { // moving to next element.
		return this.next;
	}

	/**
	 * setNext
	 * 
	 * Replaces the link to the next node in the linkedList.
	 * 
	 * @param next Next node to link to.
	 * 
	 * @return void
	 */

	public void setNext(Node<E> next) { // setting next element with new value.
		this.next = next;
	}

	/**
	 * hasNext
	 * 
	 * Checks if next element exists.
	 * 
	 * @param none
	 * 
	 * @return this.getNext() != null
	 */

	public Boolean hasNext() { // checking for next element.
		return this.getNext() != null;
	}
}
