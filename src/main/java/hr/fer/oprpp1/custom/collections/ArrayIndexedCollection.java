package hr.fer.oprpp1.custom.collections;

/**
 * 
 * Class <code>ArrayIndexedCollection</code> is resizable array-backed
 * collection of objects. It extends class <code> Collection. </code>
 * 
 * @author Leo Goršić
 */
public class ArrayIndexedCollection extends Collection {

	/** Keeps count of elements that are currently in collection. */
	private int occupied;
	
	/** Size of internal array*/
	private int size;

	/** Used array for collection memory */
	private Object[] elements;

	/**
	 * Constructor.
	 * 
	 * @param initialCapacity Must not be less than 1.
	 * @param collection      Must not be null.
	 * @throws IllegalArgumentException if initialCapacity is less than 1
	 * @throws NullPointerException     if collection is null.
	 */
	public ArrayIndexedCollection(int initialCapacity, Collection collection) {
		checkValidity(initialCapacity, collection);
		initializeSize(initialCapacity, collection);
		elements = new Object[size];
		occupied = collection.size();
		copyCollectionToElements(collection);
	}

	/**
	 * Constructor.
	 * 
	 * @param initialCapacity Must not be less than 1.
	 * @throws IllegalArgumentException if initialCapacity is less than 1
	 */
	public ArrayIndexedCollection(int initialCapacity) {
		checkValidityInitialCapacity(initialCapacity);
		size = initialCapacity;
		occupied = 0;
		elements = new Object[size];
	}

	/** Constructor. Creates ArrayIndexedCollection of size 16. */
	public ArrayIndexedCollection() {
		this(16);
	}

	/**
	 * Converts collection to array.
	 * 
	 * @return array version of collection
	 */
	@Override
	public Object[] toArray() {
		return elements;
	}

	/**
	 * Constructor.
	 * 
	 * @param collection Must not be null.
	 * @throws NullPointerException if collection is null
	 */
	public ArrayIndexedCollection(Collection collection) {
		this(collection.size(), collection);
	}

	/**
	 * Method for checking validity of constructor arguments.
	 * 
	 * @param initialCapacity
	 * @param collection
	 */
	private void checkValidity(int initialCapacity, Collection collection) {
		checkValidityCollection(collection);
		checkValidityInitialCapacity(initialCapacity);

	}

	/**
	 * Used to throw exception if <code>initialCapacity</code> is smaller than 1
	 * 
	 * @param initialCapacity
	 * @throws IllegalArgumentException if <code>initialCapacity</code> is smaller
	 *                                  than 1
	 */
	private static void checkValidityInitialCapacity(int initialCapacity) {
		if (initialCapacity < 1) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Used to throw exception if <code>collection</code> is null
	 * 
	 * @param collection
	 * @throws NullPointerException when collection is null
	 */
	private static void checkValidityCollection(Collection collection) {
		if (collection == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Function determines size by using given collection, or by given
	 * initialCapacity if it is greater than size of the collection.
	 * 
	 * @param initialCapacity
	 * @param collection
	 */
	private void initializeSize(int initialCapacity, Collection collection) {
		if (initialCapacity < collection.size()) {
			size = collection.size();
		} else {
			size = initialCapacity;
		}
	}

	/**
	 * Function copies all elements from given collection into this collection.
	 * 
	 * @param collection
	 */
	private void copyCollectionToElements(Collection collection) {

		if (collection != null && collection instanceof ArrayIndexedCollection) {
			for (int i = 0; i < collection.size(); i++) {
				elements[i] = ((ArrayIndexedCollection) collection).get(i);
			}
		}

	}

	/** Expands internal array size by doubling it every time it is full. */
	private void expandArraySize() {
		size *= 2;
		Object[] newElements = new Object[size];
		for (int i = 0; i < size / 2; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}

	/**
	 * Shifts elements in internal array one place from given position
	 * 
	 * @param position from which elements will be shifted.
	 */
	private void shiftElelemnts(int position) {
		for (int i = size; i > position; i--) {
			elements[i - 1] = elements[i - 2];
		}
	}

	/**
	 * Used to throw exception if <code>index</code> is smaller than 0 or greater
	 * than size - 1.
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException if <code>index</code> is smaller than 0 or
	 *                                   greater than size - 1.
	 */
	private void checkIndexOutOfBounds(int index) {
		if (index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * user to throw exception if <code>value</code> is null
	 * 
	 * @param value to be checked
	 * @throws NullPointerException if <code>value</code> is null.
	 */
	private static void checkIfValueIsNull(Object value) {
		if (value == null) {
			throw new NullPointerException();
		}
	}

	/**
	 * Adds <b>non-null</b> object <code>value</code> into collection with time
	 * complexity of O(N).
	 * <p>
	 * In case when collection is full, method will double the size of the
	 * collection and add <code>value</code>.
	 * 
	 * @param value Non-null object
	 * @throws NullPointerException when <code>value</code> <b>is null</b>
	 */
	@Override
	public void add(Object value) {
		boolean added = false;
		checkIfValueIsNull(value);
		occupied++;
		for (int i = 0; i < size; i++) {
			if (elements[i] == null) {
				elements[i] = value;
				added = true;
				break;
			}
		}
		if (added == false) {
			expandArraySize();
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) {
					elements[i] = value;
					added = true;
					break;
				}
			}
		}

	}

	/**
	 * Returns <code>object</code> or <code>null</code> (in case of absence) from
	 * collection at given index with time complexity of O(1)
	 * 
	 * @param index must be between 0 and size - 1
	 * @return <code>Object</code> or <code>null</code> if there is no object at
	 *         given index
	 * 
	 * @throws IllegalArgumentException when <code>index</code> is out of bounds
	 */
	public Object get(int index) {
		checkIndexOutOfBounds(index);
		return elements[index];
	}

	/**
	 * Returns size of the collection.
	 * 
	 * @return size of the collection.
	 */
	@Override
	public int size() {
		return occupied;
	}

	/**
	 * Clears collection by setting every element to null.
	 * <p>
	 * Time complexity is O(n), n is size of collection.
	 *
	 */
	public void clear() {
		occupied = 0;
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
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
		checkIndexOutOfBounds(position);
		checkIfValueIsNull(value);
		occupied++;
		if (elements[size - 1] == null) {
			shiftElelemnts(position);
		} else {
			expandArraySize();
			shiftElelemnts(position);
		}
		elements[position] = value;
	}

	/**
	 * Returns index of value in a collection with time complexity of O(n).
	 * 
	 * @param value object that you search index for. <b>Null value is allowed!</b>
	 * @return index of given <code>value</code> in collection or -1 if
	 *         <code>value</code> cannot be found
	 */
	public int indexOf(Object value) {
		for (int i = 0; i < size; i++) {
			if (elements[i].equals(value)) {
				return i;
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
		checkIndexOutOfBounds(index);
		occupied--;
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[size - 1] = null;
	}

	/**
	 * Iterates over the list and returns true if collection contains
	 * <code>value</code> object.
	 * 
	 * @return true if collection contains value.
	 *
	 */
	public boolean contains(Object value) {
		for (int i = 0; i < size; i++) {
			if (value.equals(elements[i])) {
				return true;
			}
		}
		return false;
	}
}
