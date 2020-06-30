package util;

import java.lang.Comparable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ElementSet implements Comparable {

    // TODO: you need to add more instance variables to make this work
    // TODO: you also need to add additional public method according to the test class
	private int _id;
	private double _cost;
	private Collection<Integer> _elements;
	
	public Collection<Integer> getElements() {
		return _elements;
	}
	
	public ElementSet() {
		_id = 0;
		_cost = 0;
		_elements = new TreeSet<Integer>();
	}
	
	
	public ElementSet(ElementSet e) {
		_id = e._id;
		_cost = e._cost;
		_elements = new TreeSet<Integer>(e._elements);
	}

    public ElementSet(int id, double cost, Collection<Integer> elements) {
        //TODO: complete this constructor and set instance variable to correct values
    	_id = id;
    	_cost = cost;
    	_elements = new TreeSet<Integer>(elements);
    	
    }

    
    public int getId() {
        //TODO: complete getId method to return the id of the element set
        // RETURN -1 is not correct. you need to fix this.
        return _id;
    }

    
    public double getCost() {
        //TODO: complete getCost method to return the cost of this ElementSet
        // RETURN -1 is not correct. you need to fix this.
        return _cost;
    }


    // TODO: Count the element covered by the set. 
    public int countElementsCovered(Set<Integer> elements_to_cover) {
        // TODO: Count the element covered by the set. 
        // Return -1 is not correct. you need to fix this.
    	int count = 0;
    	for (int i : elements_to_cover) {
    		if (_elements.contains(i))
    				count++;
    	}
    		
        return count;
    }

    
    public Iterable<Integer> getElementIterable() {
        //TODO: 
        // With the following method, we can write
        //   for (Integer i : getElementIteratable()) {
        //      do something with i;
        //   }
        // so that we can iterate through the Integer elements in
        // this class without having any further ability to modify
        // the underlying instance.

        // Return null is not correct; you need to fix this. 
    	
        return new TreeSet<Integer>(_elements);
    }

    @Override
    public String toString() {
        //TODO: override the toString methods
        // return empty string is not correct, you need to fix this. 
    	StringBuilder sb = new StringBuilder();
    	sb.append("Set ID:   " + _id + "   ");
    	sb.append("Cost:   " + _cost + "   ");
    	sb.append("Element IDs: ");
    	sb.append("[");
    	for(Integer i : _elements)
    		sb.append(i + ", ");
    	sb.setLength(sb.length() - 2);
    	sb.append("]");
        return sb.toString();
    }

    @Override
    public int compareTo(Object o) {
        //TODO: override the compareTo methods
        // Return -1 is not correct. you need to fix this. 
    	int var = 0;
    	if (o instanceof ElementSet) {
    		ElementSet m = (ElementSet)o;
    		var = this._id - m._id;
    	}
        return var;
    }
    
    public int compareTo2(Object o) {
    	int diff = 0;
    	if(o instanceof ElementSet) {
    		ElementSet b = (ElementSet)o;
    		for(Integer i : _elements ) {
    			if  (!b._elements.contains(i))
    				diff++;
    		}
    	}
    	return diff;
    }

    @Override
    public boolean equals(Object o) {
    	if (o instanceof ElementSet) {
    		ElementSet m = (ElementSet)o;
    		if(m._elements.size() != (this._elements.size())) {
    			return false;
    		}
    		Iterator i = this._elements.iterator();
    		for(Integer j : m._elements) {
    			if( !i.next().equals(j)) {
    				return false;
    			}
    		}			
    	}
    	return true;
        //TODO: override the equal methods
        // Return false is not correct. you need to fix this. 
        
    }
}
