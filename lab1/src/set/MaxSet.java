package set;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Iterator;


public class MaxSet<E extends Comparable<E>> extends ArraySet<E> {
	private E maxElement;
	
	/**
	 * Constructs a new empty set.
	 */
	public MaxSet() {
		super();
	}
	
	/** Returns the currently largest element in this set. 
	pre: the set is not empty 
	post: the set is unchanged 
	@return the currently largest element in this set 
	@throws NoSuchElementException if this set is empty 
	*/ 
	public E getMax() {
        if(maxElement == null) throw (new NoSuchElementException());
		return maxElement;
	}
	
	/** 
	 * Adds the specified element to this set, if it is not already present.
	 * post: x is added to the set if it is not already present
	 * @param  x the element to be added
	 * @return true if the specified element was added
	 */
	public boolean add(E x) {
        if(maxElement == null || maxElement.compareTo(x) < 0) {
            maxElement = x;
        }
		return super.add(x);
	}
	
	/** 
	 * Removes the specified element from this set if it is present. 
	 * post: 	x is removed if it was present
	 * @param 	x the element to remove - if present
	 * @return true if the set contained the specified element
	 */
	public boolean remove(E x) {
        boolean retVal = super.remove(x);
        if(maxElement.compareTo(x) == 0) {
            //System.out.println(x.toString() + " could be " + maxElement.toString() + "\nChecking for new maxElement");
            maxElement = null;
            for(E item : this) {
                if(maxElement == null || maxElement.compareTo(item) < 0) {
                    maxElement = item;
                }
            }
        }
		return retVal;
	}

}