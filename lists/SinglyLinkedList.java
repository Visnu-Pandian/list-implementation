package project2.lists;

/**
* A generic list collection that stores Node Objects linked by reference.
*
* @author Visnu Pandian
* @version 1.0
* @since 2023-03-03 
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

	Node<E> head;
	Node<E> tail;
	int currentSize;

	/**
	 * SinglyLinkedList
	 * 
	 * Constructor for SinglyLinkedList.
	 */

	public SinglyLinkedList() { // initializing values.
		head = null;
		tail = null;
		currentSize = 0;
	}

	/**
	 * size
	 * 
	 * Returns the size of linkedlist.
	 * 
	 * @param none
	 * 
	 * @return currentSize
	 */

	@Override
	public int size() {
		Node<E> curr = head;
		currentSize = 0; // reset size to 0.
		while (curr != null) { // traversing the list.
			curr = curr.next;
			currentSize += 1; // incrementing size count.
		}
		return currentSize;
	}

	/**
	 * isEmpty
	 * 
	 * Returns true if the list is empty and false if not.
	 * 
	 * @param none
	 * 
	 * @return head == null
	 */

	@Override
	public boolean isEmpty() {
		return head == null; // if empty returns true.
	}

	/**
	 * contains
	 * 
	 * Returns true if the object exists in the list and false if not.
	 * 
	 * @param o Object to check for.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return true/false
	 */

	@Override
	public boolean contains(Object o) throws NullPointerException {
		if (o == null) // checking for null.
			throw new NullPointerException();

		if (currentSize == 0) // checking for actual list
			return false;

		Object find = head.getItem().getClass().cast(o); // getting object
		Node<E> curr = head;

		while (curr.hasNext()) {
			E scan = curr.getItem();
			curr = curr.next;
			if (find == scan) { // checking if object exists in list
				return true;
			}
		}
		return false;
	}

	/**
	 * iterator
	 * 
	 * Returns an iterator for the linkedlist.
	 * 
	 * @param none
	 * 
	 * @return Itr Iterator for linkedlist.
	 */

	@Override
	public Iterator<E> iterator() {
		return new Itr();
	}

	/**
	 * Itr
	 * 
	 * Iterator for the linkedlist.
	 * 
	 */

	private class Itr implements Iterator<E> {
		private Node<E> curr;
		int index;
		int size;

		/**
		 * Itr
		 * 
		 * Constructor for Iterator.
		 */

		Itr() { // initializing values.
			curr = head;
			index = 0;
			size = size();
		}

		/**
		 * hasNext
		 * 
		 * Returns true if the iterator has another value left to iterate over.
		 * 
		 * @param none
		 * 
		 * @return index < size()
		 */

		@Override
		public boolean hasNext() {
			return (index < size); // true if next element is present.
		}

		/**
		 * next
		 * 
		 * Returns the next value in the iterator.
		 * 
		 * @param none
		 * 
		 * @throws NoSuchElementException
		 * 
		 * @return val Value of the current index in the linkedlist.
		 */

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) // checking for node.
				throw new NoSuchElementException();
			index++;

			E val = curr.getItem(); // getting value of current index.
			curr = curr.next; // moving to next value.
			return val;
		}

	}

	/**
	 * add
	 * 
	 * Adds element at the end of the linkedlist.
	 * 
	 * @param e The element to be added.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return true
	 */

	@Override
	public boolean add(E e) throws NullPointerException {

		if (e == null) // checking for null.
			throw new NullPointerException();

		Node<E> toAdd = Node.createNode(e);
		if (head == null) { // checking for empty list
			head = toAdd;
		} else {
			Node<E> curr = head;
			while (curr.hasNext()) {
				curr = curr.next; // traversing to end of list
			}
			curr.setNext(toAdd); // setting item
		}
		currentSize++;
		return true;
	}

	/**
	 * add
	 * 
	 * Adds element at the specified index of the linkedlist.
	 * 
	 * @param index   The index to add element at.
	 * @param element The element to be added.
	 * 
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 * 
	 * @return void
	 */

	@Override
	public void add(int index, E element) throws NullPointerException, IndexOutOfBoundsException {
		if (element == null) // checking for null.
			throw new NullPointerException();

		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException(); // checking for out of bounds
		}

		if (index == 0) { // adding at beginning of list
			head = new Node<E>(element, head);
			if (tail == null) // updating position of last element
				tail = head;
			currentSize++;
			return;
		}

		// adding at any element except beginning
		Node<E> pred = head;
		for (int i = 1; i <= index - 1; i++) {
			pred = pred.next;
		}
		pred.next = new Node<E>(element, pred.next);
		if (pred.next.next == null) // updating position of last element
			tail = pred;
		currentSize++;
	}

	/**
	 * remove
	 * 
	 * Removes first instance of element from the linkedlist.
	 * 
	 * @param o The element to be removed.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return true
	 */

	@Override
	public boolean remove(Object o) throws NullPointerException {
		if (o == null) // checking for null value.
			throw new NullPointerException();

		Node<E> curr = head;
		while (curr.hasNext()) {
			if (curr.getItem().equals(o)) { // checking for element
				head = head.next; // re routing the node
				currentSize--; // decrementing size
				return true; // moving out of loop if removed.
			}
			curr = curr.next;
		}
		return false;
	}

	/**
	 * remove
	 * 
	 * Removes element at specified index from the linkedlist.
	 * 
	 * @param index The index to remove element from.
	 * 
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 * 
	 * @return element Value of element removed.
	 */

	@Override
	public E remove(int index) throws NullPointerException, IndexOutOfBoundsException {
		if ((Integer) index == null) // checking for null value.
			throw new NullPointerException();

		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException(); // checking for index out of bounds
		}

		E element = null;

		if (index == 0) { // if element is at beginning
			Node<E> curr = head;
			element = curr.getItem();
			head = head.next;
			if (head == null) { // if node is empty
				tail = null;
			}
			currentSize--;
			return element;
		}

		// if element is not at beginning
		Node<E> pred = head;
		for (int i = 1; i <= index - 1; i++) {
			pred = pred.next;
		}
		element = pred.next.getItem();
		pred.next = pred.next.next; // re routing nodes
		if (pred.next == null) // setting tail to last node
			tail = pred;
		return element;
	}

	/**
	 * addAll
	 * 
	 * Adds all elements in a collection to the end of the linkedlist.
	 * 
	 * @param c The collection to add.
	 * 
	 * @return true
	 */

	@Override
	public boolean addAll(Collection<? extends E> c) throws NullPointerException {
		if (c == null) // checking for null
			throw new NullPointerException();
		c.forEach(x -> this.add(x)); // adding collection to linked list
		currentSize += c.size();

		return true;
	}

	/**
	 * addAll
	 * 
	 * Adds all elements in a collection at a specified index in the linkedlist.
	 * 
	 * @param index The index to add the collection at.
	 * @param c     The collection to add.
	 * 
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 * 
	 * @return true
	 */

	@Override
	public boolean addAll(int index, Collection<? extends E> c) throws NullPointerException, IndexOutOfBoundsException {
		if (c == null) // checking for null
			throw new NullPointerException();
		if (index < 0 || index > currentSize) // checking for index within bounds
			throw new IndexOutOfBoundsException();

		SinglyLinkedList<E> toAdd = new SinglyLinkedList<E>();
		c.forEach(x -> toAdd.add(x));

		Node<E> curr = head;
		Node<E> prev = null;
		int i = 0;
		while (i < index && curr.hasNext()) {
			prev = curr; // iterating until node to add at.
			curr = curr.next; // iterating until after node to add at.
			i++;
		}
		Node<E> addToTail = curr; // switching name of pre-existing node.
		curr = toAdd.head;
		prev.setNext(curr); // adding collection to node route.
		while (curr.hasNext()) {
			curr = curr.next; // traversing to end of collection.
		}
		curr.setNext(addToTail); // adding back pre-existing nodes.

		currentSize += c.size(); // updating size.
		return true;
	}

	/**
	 * clear
	 * 
	 * Turns linkedlist into an empty linkedlist.
	 * 
	 * @param none
	 * 
	 * @return void
	 */

	@Override
	public void clear() {

		head = null; // setting first element to null
		tail = head; // routing first element to last
		currentSize = 0;

	}

	/**
	 * get
	 * 
	 * Returns the element at a specified index.
	 * 
	 * @param index The index to get the value from.
	 * 
	 * @throws IndexOutOfBoundsException
	 * 
	 * @return value
	 */

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= currentSize)
			throw new IndexOutOfBoundsException();

		Node<E> curr = head;
		E value = null;
		int i = 0;
		if (index == 0) { // if element is at beginning of list
			value = curr.getItem();
			return value;
		}

		for (i = 1; i <= index; i++) { // traversing the list to index
			curr = curr.next;
		}
		value = curr.getItem(); // getting value
		return value;
	}

	/**
	 * set
	 * 
	 * Sets the value of an element at a specified index.
	 * 
	 * @param index   The index to set the value at.
	 * @param element The element to set the value to.
	 * 
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 * 
	 * @return replaced value of element replaced.
	 */

	@Override
	public E set(int index, E element) throws NullPointerException, IndexOutOfBoundsException {
		if (element == null) // checking for null.
			throw new NullPointerException();

		if (index < 0 || index >= size()) // checking for out of bounds
			throw new IndexOutOfBoundsException();

		Node<E> curr = head;
		E replaced = curr.getItem();
		if (index == 0) {
			curr.item = element; // setting element.
		} else {
			for (int i = 0; i < index; i++)
				curr = curr.next; // traversing until right node.
			curr.item = element; // setting element.
		}
		return replaced;
	}

	/**
	 * indexOf
	 * 
	 * Returns the index of the first instance of a specified object in the
	 * linkedlist.
	 * 
	 * @param o Object to get index of.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return index
	 */

	@Override
	public int indexOf(Object o) {
		int index = -1;
		Node<E> curr = head;
		if (this.contains(o)) { // checking for element in list
			int i = 0;
			while (i < this.size() - 1) { // traversing list

				if (curr.getItem() == o) { // when item is found
					index = i;
					return index;
				}
				curr = curr.next;
				i++;
			}
		}
		return index; // if item is not in list, returns -1
	}

	/**
	 * lastIndexOf
	 * 
	 * Returns the index of the last instance of a specified object in the
	 * linkedlist.
	 * 
	 * @param o Object to get index of.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return index
	 */

	@Override
	public int lastIndexOf(Object o) {
		int index = -1;
		Node<E> curr = head;
		if (this.contains(o)) { // checking for element in list
			int i = 0;
			while (i < this.size() - 1) {
				if (curr.getItem().equals(o))
					index = i;
				curr = curr.next;
				i++;
				// skipping the return to get the last index by overriding
			}
		}
		return index; // if item is not in list, return -1
	}

	/**
	 * subList
	 * 
	 * returns a list of elements between specified indexes in the linkedlist.
	 * 
	 * @param fromIndex The index to start creating sub list from.
	 * @param toIndex   The index to stop creating sub list at.
	 * 
	 * @return list
	 */

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		List<E> list = new ArrayList<E>();
		var curr = head;
		var index = 0;

		while (index < fromIndex) { // traversing until hitting start
			curr = curr.next;
			index++;
		}

		while (index < toIndex && curr != null) { // creating new sublist and adding values until end
			E item = curr.getItem();
			list.add(item);
			curr = curr.next;
			index++;
		}
		return list; // final sublist
	}

	/**
	 * toString
	 * 
	 * Custom string method for linkedlist.
	 * 
	 * @param none
	 * 
	 * @return str.toString()
	 */

	public String toString() { // custom method to get list in linkedlist format when printing.
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < this.size(); i++) {
			str.append(this.get(i));
			str.append(", ");
		}
		str.delete(str.length() - 2, str.length());
		str.append("]");
		return str.toString();
	}

	// Methods below should not be implemented
	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

}
