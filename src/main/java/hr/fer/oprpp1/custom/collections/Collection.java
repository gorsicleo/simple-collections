package hr.fer.oprpp1.custom.collections;

/**Class Collection represents some general collection of objects.
 * @author Leo Goršić
 *
 */
public class Collection {
	
	/** Returns size of the collection.
	 *@return size of the collection.
	 */
	public int size() {
		return 0;
	}
	
	
	/**Returns true if collection is empty, false otherwise.
	 * @return true if array is empty.
	 */
	public boolean isEmpty() {
		if (size()==0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Adds object <code>value</code> into collection.
	 * 
	 * @param value object
	 */
	public void add(Object value) {
		
	}
	
	/**Returns true if collection contains <code>value</code> object.
	 * @return true if collection contains value.
	 *
	 */
	public boolean contains(Object value) {
		return false;
	}
	/**Removes element at <code>index</code> 
	 * 
	 * @param value to be removed
	 * @return false
	 */
	public boolean remove(Object value) {
		return false;
	}
	/**Converts collection to array.
	 *@return array version of collection
	 */
	Object[] toArray() {
		throw new UnsupportedOperationException();
	}
	
	public void forEach(Processor processor) {
		
	}
	
	
	/**Copies all elements from collection <code>other</code> into this collection.
	 * @param other
	 */
	public void addAll(Collection other) {
		class ConcreteProcessor extends Processor{
			public void process(Object value) {
				add(value);
			}
		}
		Processor processor = new ConcreteProcessor();
		other.forEach(processor);;
	}
	
	
	/**
	 * Clears collection by setting every element to null.
	 *
	 */
	public void clear() {
		
	}

}


