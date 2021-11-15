package hr.fer.oprpp1.custom.collections;

/**
 * Class <code>LinkedListIndexedCollection</code> is linked list-backed
 * collection of objects. It extends class <code> Collection. </code>
 * 
 * @author Leo Goršić
 */
public class LinkedListIndexedCollection extends Collection {

	/**
	 * Class which stores individual element of the list. List node.
	 * 
	 * @author Leo Goršić
	 */
	private static class ListNode {
		ListNode previousNode;
		ListNode nextNode;
		Object value;

		public ListNode(ListNode previousNode, ListNode nextNode, Object value) {
			this.nextNode = nextNode;
			this.previousNode = previousNode;
			this.value = value;
		}
	}

	/** Keeps count of elements that are currently in list. */
	private int size;

	/** Reference to the first element in node chain */
	private ListNode firstNode;

	/** Reference to the last element in node chain */
	private ListNode lastNode;

	/**
	 * Used to throw exception if <code>value</code> is null
	 * 
	 * @param value
	 * @throws NullPointerException when value is null
	 */
	private static void checkIfValueIsNull(Object value) {
		if (value == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Used to throw exception if <code>index</code> is smaller than 0, or greater
	 * than size-1
	 * 
	 * @param value
	 * @throws IndexOutOfBoundsException when <code>index</code> is out of range
	 */
	private void checkIndexValidityForGet(int index) {
		if (index < 0 || index > (size - 1)) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Used to throw exception if <code>index</code> is smaller than 0, or greater
	 * than size
	 * 
	 * @param value
	 * @throws IndexOutOfBoundsException when <code>index</code> is out of range
	 */
	private void checkIndexValidityForInsert(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Constructor. Creates empty linked list.
	 */
	public LinkedListIndexedCollection() {
		size = 0;
		firstNode = lastNode = null;
	}

	/**
	 * Constructor. Copies all elements from <code>collection</code> into created
	 * list.
	 * 
	 * @param collection
	 */
	public LinkedListIndexedCollection(Collection collection) {
		Object[] collectionArray = collection.toArray();
		for (int i = 0; i < collectionArray.length; i++) {
			add(collectionArray[i]);
		}
	}

	/**
	 * Converts collection to array.
	 * 
	 * @return array version of collection
	 */
	@Override
	public Object[] toArray() {
		Object[] elements = new Object[size];
		ListNode currentNode = firstNode;
		for (int i = 0; i < size; i++) {
			elements[i] = currentNode.value;
			currentNode = currentNode.nextNode;
		}
		return elements;
	}

	/**
	 * Adds <b>non-null</b> object <code>value</code> into collection with time
	 * complexity of O(1).
	 * 
	 * @param value Non-null object
	 * @throws NullPointerException when <code>value</code> <b>is null</b>
	 */
	public void add(Object value) {
		checkIfValueIsNull(value);
		size++;
		if (firstNode == null) {
			ListNode newNode = new ListNode(null, null, value);
			firstNode = lastNode = newNode;
		} else {
			ListNode newNode = new ListNode(lastNode, null, value);
			lastNode.nextNode = newNode;
			lastNode = newNode;
		}
	}

	/**
	 * Returns <code>object</code> from collection at given index with time
	 * complexity of O(n/2)
	 * 
	 * @param index must be between 0 and size - 1
	 * @return <code>Object</code> at given index
	 * 
	 * @throws IndexOutOfBoundsException when <code>index</code> is out of bounds
	 */
	public Object get(int index) {
		checkIndexValidityForGet(index);
		ListNode currentNode = firstNode;
		if (index < size / 2) {
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.nextNode;
			}
		} else {
			currentNode = lastNode;
			for (int i = size - 1; i > index; i--) {
				currentNode = currentNode.previousNode;
			}
		}
		return currentNode.value;
	}

	/**
	 * Clears collection.
	 * <p>
	 * Time complexity is O(1).
	 *
	 */
	public void clear() {
		size = 0;
		firstNode = lastNode = null;
	}

	/**
	 * Inserts non-null <code>value</code> at given <code>position</code> in
	 * collection with time complexity O(n). Elements that are on position and
	 * greater positions are being up shifted for one place.
	 * 
	 * @param value
	 * @param position
	 * 
	 * @throws IndexOutOfBoundsException for indexes that are out of range.
	 * @throws NullPointerException      for <code>value</code> that is null.
	 */
	public void insert(Object value, int position) {
		checkIndexValidityForInsert(position);
		checkIfValueIsNull(value);

		if (firstNode == null) {
			add(value);
		} else {
			ListNode currentNode = firstNode;

			for (int i = 0; i < position - 1; i++) {
				currentNode = currentNode.nextNode;
			}
			ListNode newNode = new ListNode(currentNode, currentNode.nextNode, value);
			currentNode.nextNode.previousNode = newNode.nextNode;
			currentNode.nextNode = newNode;
		}
		size++;
	}

	/**
	 * Returns index of value in a collection with time complexity of O(n).
	 * 
	 * @param value object that you search index for. <b>Null value is allowed!</b>
	 * @return index of given <code>value</code> in collection or -1 if
	 *         <code>value</code> cannot be found
	 */
	public int indexOf(Object value) {
		ListNode currentNode = firstNode;
		for (int i = 0; i < size; i++) {
			if (currentNode.value.equals(value)) {
				return i;
			} else {
				currentNode = currentNode.nextNode;
			}
		}
		return -1;
	}

	/**
	 * Removes element at <code>index</code> and shifts elements at greater
	 * positions one place left.
	 * 
	 * @param index that must be between 0 and size-1
	 * @throws IndexOutOfBoundsException when index is greater than size or smaller
	 *                                   than 0.
	 */
	public void remove(int index) {
		checkIndexValidityForGet(index);
		ListNode currentNode = firstNode;

		if (index == size - 1) {
			lastNode = currentNode.previousNode;

		} else {
			for (int i = 0; i < index - 1; i++) {
				currentNode = currentNode.nextNode;
			}
			if (currentNode.nextNode == lastNode) {
				lastNode.previousNode = lastNode.previousNode.previousNode;
				currentNode.previousNode = lastNode;
			} else {
				currentNode.nextNode = currentNode.nextNode.nextNode;
				currentNode.nextNode.nextNode = currentNode;
			}
		}
		size--;
	}

	/**
	 * Returns size of the collection.
	 * 
	 * @return size of the collection.
	 */
	@Override
	public int size() {
		return size;
	}

}
