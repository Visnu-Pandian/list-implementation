package project2.lists;

/**
* A generic list collection that stores ArrayLists using internal arrays.
*
* @author Visnu Pandian
* @version 1.0
* @since 2023-03-19 
*/

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {

	static final int DEFAULT_CAP = 10; // default size of array.
	static final int INCREMENT = 10; // default size of increments to array.

	private int capacity; // initial capacity of array.
	private int currentSize; // size variable of array.
	private Object[] array; // internal array used to implement ArrayList.

	/**
	 * ArrayList
	 * 
	 * Constructor for ArrayList.
	 */

	public ArrayList() { // initializing values.
		capacity = 0;
		currentSize = 0;
		array = new Object[DEFAULT_CAP];
	}

	/**
	 * size
	 * 
	 * Returns the size of the array.
	 * 
	 * @param none
	 * 
	 * @return currentSize
	 */

	@Override
	public int size() {
		currentSize = 0; // reset size to 0.
		for (int i = 0; i < array.length; i++) { // traversing array.
			if (array[i] != null) // counting non-null values to get effective size.
				currentSize += 1;
		}
		return currentSize;
	}

	/**
	 * expand
	 * 
	 * Increases the size of the array when needed by using defined increments.
	 * 
	 * @param expandTo Threshold to increase the array size past.
	 * 
	 * @return void
	 */

	private void expand(int expandTo) {
		while (capacity < expandTo)
			// incrementing capacity by a default value until it is greater than the
			// required minimum size of array.
			capacity += INCREMENT;

		Object[] temp = new Object[currentSize]; // copying current array to temporary space.
		for (int i = 0; i < currentSize; i++)
			temp[i] = array[i];

		array = (E[]) new Object[capacity]; // creating new array with updated capacity.
		for (int j = 0; j < currentSize; j++)
			// copying values from temp array to new array to create new array with old
			// values and increased capacity.
			array[j] = temp[j];
	}

	/**
	 * isEmpty
	 * 
	 * Returns true if the array is empty and false if not.
	 * 
	 * @param none
	 * 
	 * @return this.size == 0
	 */

	@Override
	public boolean isEmpty() {
		return this.size() == 0; // checking for zero size of list.
	}

	/**
	 * contains
	 * 
	 * Returns true if the object exists in the array and false if not.
	 * 
	 * @param o Object to check for.
	 * 
	 * @return this.indexOf(o) != -1
	 */

	@Override
	public boolean contains(Object o) {
		return this.indexOf(o) != -1; // checking for absence of object in array.
	}

	/**
	 * iterator
	 * 
	 * Returns an iterator for the arraylist.
	 * 
	 * @param none
	 * 
	 * @return itr Iterator for arraylist.
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
	 * @param none
	 */

	private class Itr implements Iterator<E> {
		int index;
		int size;

		/**
		 * Itr
		 * 
		 * Constructor for Iterator.
		 */

		Itr() { // initializing values.
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
		 * @return index < currentSize
		 */

		@Override
		public boolean hasNext() {
			return (index < size); // checking for next value.
		}

		/**
		 * next
		 * 
		 * Returns the next value in the iterator.
		 * 
		 * @param none
		 * 
		 * @return val Value of the current index in the array.
		 */

		@Override
		public E next() {
			E val = (E) array[index]; // getting value of current index.
			index++; // incrementing index.
			return val;
		}
	}

	/**
	 * add
	 * 
	 * Adds element at the end of the array.
	 * 
	 * @param e The element to be added.
	 * 
	 * @return true
	 */

	@Override
	public boolean add(E e) { // wrapper class.
		add(currentSize, e); // adding at end of array.
		return true;
	}

	/**
	 * add
	 * 
	 * Adds element at the specified index of the array.
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
		if (element == null) // checking for null element.
			throw new NullPointerException();

		if (index < 0 || index > currentSize) // checking for index within bounds.
			throw new IndexOutOfBoundsException();

		expand(currentSize + 1); // increasing capacity of array to hold at least 1 more value.
		if (index == currentSize) { // if called from wrapper class.
			array[index] = element; // adding at end of array.
			currentSize++;
		} else { // if element is to added at any index but last.
			List<E> toAdd = subList(index, currentSize); // take all elements from index to last into new sublist.
			Iterator<? extends E> itr = toAdd.iterator();

			array[index] = element; // setting element at index.
			index++; // incrementing index.
			currentSize++;
			for (; index < currentSize; index++)
				array[index] = itr.next(); // adding back elements from sublist after new element.
		}
	}

	/**
	 * remove
	 * 
	 * Removes first instance of element from the array.
	 * 
	 * @param o The element to be removed.
	 * 
	 * @return true
	 */

	@Override
	public boolean remove(Object o) { // wrapper class.
		remove(indexOf(o)); // finding index to remove at and passing index.
		return true;
	}

	/**
	 * remove
	 * 
	 * Removes element at specified index from the array.
	 * 
	 * @param index The index to remove element from.
	 * 
	 * @throws IndexOutOfBoundsException
	 * 
	 * @return removed Value of element removed.
	 */

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// null is checked from wrapper class -> indexOf method call.
		if (index < 0 || index > currentSize) // checking for index within bounds.
			throw new IndexOutOfBoundsException();

		E removed = (E) array[index]; // storing value of element to remove.
		if (index == currentSize) { // if last element is to be removed.
			array[index] = null; // removing element.
		} else { // if element to be removed is any index but last.
			List<E> toAdd = subList(index + 1, currentSize);
			// take all elements from one after index to last into new sublist.
			Iterator<? extends E> itr = toAdd.iterator();

			for (; index < currentSize; index++) {
				array[index] = itr.next(); // replacing every value from index to last with values from sublist.
			}
		}
		currentSize--; // decrementing size
		return removed;

	}

	/**
	 * addAll
	 * 
	 * Adds all elements in a collection to the end of the array.
	 * 
	 * @param c The collection to add.
	 * 
	 * @return addAll(currentSize, c)
	 */

	@Override
	public boolean addAll(Collection<? extends E> c) { // wrapper class
		return addAll(currentSize, c); // adding collection to end of array.
	}

	/**
	 * addAll
	 * 
	 * Adds all elements in a collection at a specified index in the array.
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

		Iterator<? extends E> itr = c.iterator(); // creating iterator for collection.
		int totalSize = currentSize + c.size(); // getting value of future size.
		if (totalSize > capacity)
			expand(totalSize); // increasing capacity of array to at least future size.

		if (index == currentSize) { // if called from wrapper class.
			currentSize += c.size();
			for (; index < totalSize; index++)
				if (itr.hasNext())
					array[index] = itr.next();
		} else { // if collection is to be added at any element but last.
			List<E> toAdd = subList(index, currentSize);
			Iterator<? extends E> itr2 = toAdd.iterator(); // second iterator for adding back pre-existing elements.
			currentSize += c.size(); // updating size.
			for (; index < totalSize; index++)
				if (itr.hasNext())
					array[index] = itr.next(); // adding new elements until the collection runs out.
				else
					array[index] = itr2.next(); // adding pre-existing elements after collection runs out.
		}
		return true;
	}

	/**
	 * clear
	 * 
	 * Turns array into an empty array.
	 * 
	 * @param none
	 * 
	 * @return void
	 */

	@Override
	public void clear() {
		for (int i = 0; i < array.length; i++) // traversing the array.
			array[i] = null; // setting all elements to null.

		currentSize = 0; // updating size.
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
	 * @return (E) array[index]
	 */

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= currentSize) // checking for index within bounds.
			throw new IndexOutOfBoundsException();

		return (E) array[index]; // getting element at index.
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
		if (element == null) // checking for null input.
			throw new NullPointerException();

		if (index < 0 || index >= currentSize) // checking for index within bounds.
			throw new IndexOutOfBoundsException();

		E replaced = get(index); // getting value which is replaced.
		array[index] = element; // setting element at index.
		return replaced;
	}

	/**
	 * indexOf
	 * 
	 * Returns the index of the first instance of a specified object in the array.
	 * 
	 * @param o Object to get index of.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return index
	 */

	@Override
	public int indexOf(Object o) throws NullPointerException {
		int index = -1; // initialize index to not found.

		if (o == null) // checking for null input.
			throw new NullPointerException();

		for (int i = 0; i < currentSize; i++) { // traverse the array.
			if (o.equals(array[i])) { // if found.
				index = i; // update index.
				return index; // return first instance.
			}
		}
		return index; // if not found, return without updating.

	}

	/**
	 * lastIndexOf
	 * 
	 * Returns the index of the last instance of a specified object in the array.
	 * 
	 * @param o Object to get index of.
	 * 
	 * @throws NullPointerException
	 * 
	 * @return index
	 */

	@Override
	public int lastIndexOf(Object o) throws NullPointerException {
		int index = -1; // initialize index to not found.

		if (o == null) // checking for null input.
			throw new NullPointerException();

		for (int i = 0; i < currentSize; i++) { // traverse the array.
			if (o.equals(array[i])) // if found.
				index = i; // update but do not return.
		}
		return index; // return last instance, -1 if not updated.
	}

	/**
	 * subList
	 * 
	 * returns a list of elements between specified indexes in the array.
	 * 
	 * @param fromIndex The index to start creating sub list from.
	 * @param toIndex   The index to stop creating sub list at.
	 * 
	 * @return list
	 */

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		ArrayList<E> list = new ArrayList<E>();
		int index = 0;

		while (index < fromIndex) // traversing array to reach starting index.
			index++;

		while (index < toIndex) { // after reaching starting index, until reaching ending index.
			E val = (E) array[index];
			list.add(val); // adding values to sublist.
			index++;
		}
		return list;
	}

	/**
	 * toString
	 * 
	 * Custom string method for ArrayList.
	 * 
	 * @param none
	 * 
	 * @return str.toString()
	 */

	public String toString() { // custom method to get list in array format when printing.
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (E e : this) {
			str.append(e);
			str.append(", ");
		}
		str.deleteCharAt(str.length() - 1);
		str.deleteCharAt(str.length() - 1);
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
