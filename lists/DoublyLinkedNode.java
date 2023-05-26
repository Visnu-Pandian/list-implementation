package project2.lists;

/**
 * Represents a node in a doubly linked list
 * 
 * @author Visnu Pandian
 *
 * @param <E> Type variable
 */

public class DoublyLinkedNode<E> {
	E item;
	DoublyLinkedNode<E> next; // link to next node.
	DoublyLinkedNode<E> prev; // link to previous node.

	/**
	 * DoublyLinkedNode
	 * 
	 * Constructor for DoublyLinkedNode.
	 * 
	 * @param i Value of node.
	 * 
	 * @return void
	 */

	public DoublyLinkedNode(E i) {
		this.item = i;
		next = prev = null;
	}

	/**
	 * DoublyLinkedNode
	 * 
	 * Constructor for DoublyLinkedNode.
	 * 
	 * @param i    Value of node.
	 * @param prev Previous node.
	 * @param next Next node
	 * 
	 * @return void
	 */

	public DoublyLinkedNode(E i, DoublyLinkedNode<E> prev, DoublyLinkedNode<E> next) {
		this.item = i;
		this.prev = prev;
		this.next = next;
	}

	/**
	 * createDoublyLinkedNode
	 * 
	 * Method to call constructor.
	 * 
	 * @param i Value of node.
	 * 
	 * @return new DoublyLinkedNode<I>(i)
	 */

	public static <I> DoublyLinkedNode<I> createDoublyLinkedNode(I i) {
		return new DoublyLinkedNode<I>(i);
	}

	/**
	 * createDoublyLinkedNode
	 * 
	 * Constructor for DoublyLinkedNode.
	 * 
	 * @param i    Value of node.
	 * @param prev Previous node.
	 * @param next Next node
	 * 
	 * @return new DoublyLinkedNode<I>(i, prev, next)
	 */

	public static <I> DoublyLinkedNode<I> createDoublyLinkedNode(I i, DoublyLinkedNode<I> prev,
			DoublyLinkedNode<I> next) {
		return new DoublyLinkedNode<I>(i, prev, next);
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

	public E getItem() {
		return item;
	}

	/**
	 * getNext
	 * 
	 * Returns the next node in the doublylinkedList.
	 * 
	 * @param none
	 * 
	 * @return this.next
	 */

	public DoublyLinkedNode<E> getNext() {
		return this.next;
	}

	/**
	 * getPrev
	 * 
	 * Returns the previous node in the doublylinkedList.
	 * 
	 * @param none
	 * 
	 * @return this.prev
	 */

	public DoublyLinkedNode<E> getPrev() {
		return this.prev;
	}

	/**
	 * setNext
	 * 
	 * Replaces the link to the next node in the doublylinkedList.
	 * 
	 * @param next Next node to link to.
	 * 
	 * @return void
	 */

	public void setNext(DoublyLinkedNode<E> next) {
		this.next = next;
	}

	/**
	 * setPrev
	 * 
	 * Replaces the link to the previous node in the doublylinkedList.
	 * 
	 * @param prev Previous node to link to.
	 * 
	 * @return void
	 */

	public void setPrev(DoublyLinkedNode<E> prev) {
		this.prev = prev;
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

	public Boolean hasNext() {
		return this.getNext() != null;
	}

	/**
	 * hasPrev
	 * 
	 * Checks if previous element exists.
	 * 
	 * @param none
	 * 
	 * @return this.getPrev() != null
	 */

	public Boolean hasPrev() {
		return this.getPrev() != null;
	}

}
