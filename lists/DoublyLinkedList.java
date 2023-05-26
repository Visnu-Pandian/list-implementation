package project2.lists;

/**
* A generic list collection that stores DoublyLinkedNode Objects linked by references.
*
* @author Visnu Pandian
* @version 1.0
* @since 2023-03-23 
*/

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E> {

	DoublyLinkedNode<E> head;
	DoublyLinkedNode<E> tail;
	int currentSize;

	/**
	 * DoublyLinkedList
	 * 
	 * Constructor for DoublyLinkedList.
	 */

	public DoublyLinkedList() { // initializing values.
		head = null;
		tail = null;
		currentSize = 0;
	}

	/**
	 * size
	 * 
	 * Returns the size of doublylinkedlist.
	 * 
	 * @param none
	 * 
	 * @return currentSize
	 */

	@Override
	public int size() {
		DoublyLinkedNode<E> curr = head;
		currentSize = 0; // reset size to 0.
		while (curr != null) { // traversing the list.
			curr = curr.next;
			currentSize++; // incrementing size count.
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
	 * @return indexOf(o) != -1
	 */

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1; // true if found, false if not
	}

	/**
	 * iterator
	 * 
	 * Returns an iterator for the doublylinkedlist.
	 * 
	 * @param none
	 * 
	 * @return itr Iterator for doublylinkedlist.
	 */

	@Override
	public Iterator<E> iterator() {
		return new Itr(); // returning iterator.
	}

	/**
	 * iterator
	 * 
	 * Returns a listIterator for the doublylinkedlist.
	 * 
	 * @param none
	 * 
	 * @return itr listIterator for doublylinkedlist.
	 */

	@Override
	public ListIterator<E> listIterator() {
		return new Itr(); // returning iterator.
	}

	/**
	 * Itr
	 * 
	 * Iterator for the doublylinkedlist.
	 * 
	 */

	private class Itr implements ListIterator<E> {
		private int size;
		private int nextIndex; // index of node "next".
		private DoublyLinkedNode<E> next; // node to traverse collection.
		private DoublyLinkedNode<E> lastReturned; // node to track element to return.

		/**
		 * Itr
		 * 
		 * Constructor for Iterator.
		 */

		private Itr() { // initializing values.
			next = head;
			lastReturned = null;
			nextIndex = 0;
			size = size();
		}

		/**
		 * hasNext
		 * 
		 * Returns true if the iterator has another value left to iterate over.
		 * 
		 * @param none
		 * 
		 * @return nextIndex() < size
		 */

		@Override
		public boolean hasNext() { // true if element exists.
			return nextIndex() < size;
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
		 * @return val Value of the current index in the doublylinkedlist.
		 */

		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext()) // checking for node.
				throw new NoSuchElementException();
			lastReturned = next; // setting node to return value.
			next = next.next; // moving node forward.
			nextIndex++;
			E val = lastReturned.getItem(); // returning value.
			return val;
		}

		/**
		 * hasPrevious
		 * 
		 * Returns true if the iterator has another value left to iterate back over.
		 * 
		 * @param none
		 * 
		 * @return nextIndex() > size
		 */

		@Override
		public boolean hasPrevious() { // true is element has been passed.
			return nextIndex() > 0;
		}

		/**
		 * Previous
		 * 
		 * Returns the last value passed in the iterator.
		 * 
		 * @param none
		 * 
		 * @throws NoSuchElementException
		 * 
		 * @return val Value of the current index in the doublylinkedlist.
		 */

		@Override
		public E previous() throws NoSuchElementException {
			if (!hasPrevious()) // checking for node.
				throw new NoSuchElementException();
			if (next == null) // if node has gone over the existing nodes.
				next = tail; // reset node.

			lastReturned = next; // storing node in temp.
			next = next.prev; // moving node back.
			E val = lastReturned.getItem(); // getting value of node.
			nextIndex--;
			return val;
		}

		@Override
		public int nextIndex() {
			// TODO Auto-generated method stub
			return nextIndex; // to return index of next.
		}

		@Override
		public int previousIndex() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(E e) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(E e) {
			// TODO Auto-generated method stub
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * add
	 * 
	 * Adds element at the end of the doublylinkedlist.
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
		DoublyLinkedNode<E> toAdd = DoublyLinkedNode.createDoublyLinkedNode(e);
		if (head == null) { // checking for empty list.
			head = toAdd;
			tail = head;
		} else {
			DoublyLinkedNode<E> curr = head;
			while (curr.hasNext()) // traversing to end.
				curr = curr.next;
			curr.setNext(toAdd); // adding value.
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

		if (index < 0 || index > currentSize)
			throw new IndexOutOfBoundsException(); // checking for out of bounds

		DoublyLinkedNode<E> newNode = new DoublyLinkedNode<E>(element);

		if (isEmpty()) { // checking for empty list.
			head = newNode;
			tail = newNode;
		} else if (index == 0) { // adding at beginning of list.
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		} else {
			DoublyLinkedNode<E> curr = head;
			int i = 0;
			while (i < index - 1) { // traversing to end of list.
				curr = curr.next;
				i++;
			}

			// re routing nodes.
			newNode.next = curr.next;
			newNode.prev = curr;
			if (curr.next != null) {
				curr.next.prev = newNode;
			} else {
				tail = newNode;
			}

			curr.next = newNode;
		}
		currentSize++;
	}

	/**
	 * remove
	 * 
	 * Removes first instance of element from the doublylinkedlist.
	 * 
	 * @param o The element to be removed.
	 * 
	 * @return true
	 */

	@Override
	public boolean remove(Object o) {
		remove(indexOf(o));
		return true;
	}

	/**
	 * remove
	 * 
	 * Removes element at specified index from the doublylinkedlist.
	 * 
	 * @param index The index to remove element from.
	 * 
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 * 
	 * @return val Value of element removed.
	 */

	@Override
	public E remove(int index) throws NullPointerException, IndexOutOfBoundsException {
		int size = size();
		if ((Integer) index == null) // checking for null.
			throw new NullPointerException();

		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(); // checking for index out of bounds

		DoublyLinkedNode<E> curr;
		if (index < size / 2) { // checking for best access route.
			curr = head; // starting from head.
			if (index == 0) { // to remove from start of list.
				head = curr.next;
				return curr.item;
			}
			int i = 0;
			while (i < index) { // traversing to index.
				curr = curr.next;
				i++;
			}
		} else {
			curr = tail; // starting from tail.
			int i = size - 1;
			while (i > index) { // traversing in reverse to index.
				curr = curr.prev;
				i--;
			}
		}

		E val = curr.item; // getting value of removed element.
		// re routing nodes.
		if (curr.next == null) { // if last element is removed.
			curr.prev.next = null;
			tail = curr.prev;
		} else { // any element but last.
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
		}

		return val;
	}

	/**
	 * addAll
	 * 
	 * Adds all elements in a collection to the end of the doublylinkedlist.
	 * 
	 * @param c The collection to add.
	 * 
	 * @return true
	 */

	@Override
	public boolean addAll(Collection<? extends E> c) {
		return addAll(currentSize, c);
	}

	/**
	 * addAll
	 * 
	 * Adds all elements in a collection at a specified index in the
	 * doublylinkedlist.
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
		if (c == null) // checking for null.
			throw new NullPointerException();

		if (index < 0 || index > currentSize) // checking for index within bounds.
			throw new IndexOutOfBoundsException();

		int addIndex = index;
		for (E e : c) { // traversing collection.
			add(addIndex, e); // adding every element individually.
			addIndex++; // incrementing index to add in correct sequence.
		}
		return true;

	}

	/**
	 * clear
	 * 
	 * Turns doublylinkedlist into an empty doublylinkedlist.
	 * 
	 * @param none
	 * 
	 * @return void
	 */

	@Override
	public void clear() {
		head = null; // setting first element to null.
		tail = head; // routing first element to last.
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
		if (index < 0 || index >= currentSize) // checking for index within bounds.
			throw new IndexOutOfBoundsException();

		DoublyLinkedNode<E> curr = head;
		E value;

		if (index == 0) { // if element is at beginning of list.
			value = curr.getItem();
		} else {
			for (int i = 1; i <= index; i++) // traversing to index.
				curr = curr.next;
			value = curr.getItem(); // getting value of element.
		}

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
	 * @return value
	 */

	@Override
	public E set(int index, E element) throws NullPointerException, IndexOutOfBoundsException {
		if (element == null) // checking for null.
			throw new NullPointerException();

		if (index < 0 || index >= currentSize) {
			throw new IndexOutOfBoundsException(); // checking for out of bounds
		}

		DoublyLinkedNode<E> curr;
		E value;

		if (index == 0) // if element is at start.
			curr = head;
		else if (index == currentSize - 1) // if element is at end.
			curr = tail;
		else {
			curr = head;
			for (int i = 0; i <= index; i++) // traversing till index.
				curr = curr.next;
		}

		value = curr.getItem(); // getting value of element.
		curr.item = element; // setting value.
		return value;
	}

	/**
	 * indexOf
	 * 
	 * Returns the index of the first instance of a specified object in the
	 * doublylinkedlist.
	 * 
	 * @param o Object to get index of.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return index
	 */

	@Override
	public int indexOf(Object o) {

		if (o == null) // checking for null.
			throw new NullPointerException();

		int index = -1;
		DoublyLinkedNode<E> curr = head;

		for (int i = 0; i < currentSize; i++) { // traversing through the list.
			if (o.equals(curr.item)) { // if item is found.
				index = i;
				return index; // return updated index.
			}
			curr = curr.next;
		}
		return index;
	}

	/**
	 * lastIndexOf
	 * 
	 * Returns the index of the last instance of a specified object in the
	 * doublylinkedlist.
	 * 
	 * @param o Object to get index of.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return index
	 */

	@Override
	public int lastIndexOf(Object o) {
		if (o == null) // checking for null.
			throw new NullPointerException();

		int index = -1;
		DoublyLinkedNode<E> curr = head;

		int i = 0;
		while (curr.hasNext()) { // traversing through the list.
			if (curr.item.equals(o))
				index = i; // updating without returning.
			curr = curr.next;
			i++;
		}
		return index;
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
	 * Custom string method for doublylinkedlist.
	 * 
	 * @param none
	 * 
	 * @return str.toString()
	 */

	// method implemented to troubleshoot code for tests
	public String toString() {
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
