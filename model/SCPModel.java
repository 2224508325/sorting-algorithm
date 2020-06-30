package model;

import java.util.*;

import util.ElementSet;

// Holds all SCP model data
public class SCPModel {

    // TODO: add any instance variables needed to make this class work as expected
    // TODO: add any methods needed to complelte the expected functionality
	
	private Collection<ElementSet> _elementsSet;

    public SCPModel() {
        // TODO: initialize instance variables correctly. 
    	_elementsSet = new TreeSet<ElementSet>();
    }
    
    public SCPModel(SCPModel m) {
    	this._elementsSet = m._elementsSet;
    }
    public Collection<ElementSet> getElementsSet() {
    	return _elementsSet;
    }
    public void addSetToCover(int id, double cost, Collection<Integer> elements) {
        // TODO: complete this method;
    	ElementSet e = new ElementSet(id,cost,elements);
    	_elementsSet.add(e);
    }

    public Iterable<ElementSet> getElementSetIterable() {
        // TODO: complete this method
        
        return  _elementsSet;  // return null is not the correct behaviour; you need to fix this. 
    }
    
    public int setsNum() {
    	int count = 0;
    	for(int i = 0; i < _elementsSet.size(); i++) {
    		count++;
    	}
    	return count;
    }
    
    public int elementsnum() {
    	TreeSet<Integer> m = new TreeSet<Integer>();
    	for(ElementSet i:_elementsSet) {
    		for(Integer j:i.getElementIterable()) {
    			if(!m.contains(j)){
    				m.add(j);
    			}
    		}
    	}
    	return m.size();
    }
    
    public TreeSet<Integer> totalList(){
    	TreeSet<Integer> m = new TreeSet<Integer>();
    	for(ElementSet i:_elementsSet) {
    		for(Integer j:i.getElementIterable()) {
    			if(!m.contains(j)){
    				m.add(j);
    			}
    		}
    	}
    	return m;
    }
    
    public void remove(ElementSet e) {
    	for(ElementSet i : _elementsSet) {
    		if (i.equals(e))
    			_elementsSet.remove(i);
    	}
    }
    			
    
    
    // print out model details
    @Override
    public String toString() {
        // TODO: complete this to string method
   //   Set 1: weight 3.0, elements { 1, 3, 5, 7, 9 }
    	StringBuilder sb = new StringBuilder();
    	Iterator i = _elementsSet.iterator();
    	sb.append("Weighted SCP model:" + "\n");
    	sb.append("---------------------" + "\n");
    	sb.append("Number of elements (n): " + totalList().size() + "\n");
    	sb.append("Number of sets (m): " + setsNum() + "\n");
    	sb.append("\n");
    	sb.append("Set details:" + "\n");
    	sb.append("----------------------------------------------------------" + "\n");
    	
    	
    	
    	while(i.hasNext())
    		sb.append(i.next() + "\n");
    	return sb.toString();
   
        // returning empty string is not the correct behavior; you neeed to fix this. 

    }
}
