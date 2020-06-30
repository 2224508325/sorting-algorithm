package solver;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import util.ElementSet;


public class GreedyCoverageSolver extends GreedySolver {
	
	private ElementSet remain_element;
	private ElementSet selected_set;
	private double old_coverage;
	public GreedyCoverageSolver() {
		this._name = "Coverage";
		remain_element = new ElementSet();
		//this._model = setModel();
	}
	public void reset() {
		remain_element = new ElementSet();
		this._elementsNotCovered = new TreeSet<Integer>(_model.totalList());
		_solnSets = new TreeSet<ElementSet>();
	}
// TODO: you need add any instance variable and method (including overriding methods)
	public ElementSet nextBestSet() {
		//GreedyCoverageSolver copy = new GreedyCoverageSolver();
		//ArrayList<Integer> a = new ArrayList<Integer>();
		
		//for(ElementSet i : _model.getElementSetIterable()) {
		//	int count = 0;
		//	for(Integer j : i.getElementIterable()) {
		//		count++;
		//		a.add(count);
		//		remain_element.add(j);
		//	}
		//}
		//double a = remain_element.getElements().size();
		//double b = this._model.totalList().size();
		//old_coverage = a/b;
		selected_set = new ElementSet();

		int max_diff = 0;
		for(ElementSet i : _model.getElementSetIterable()) {
			if(!_solnSets.contains(i)) {
				if(max_diff < remain_element.compareTo2(i))
					max_diff = remain_element.compareTo2(i);
			}
		}
		//if(getCoverage() != 1.0) {
		
		for(ElementSet j : _model.getElementSetIterable()) {
			if(!_solnSets.contains(j)) {
			if(max_diff == remain_element.compareTo2(j)) {
				selected_set = new ElementSet(j);
				this._objFn += j.getCost();
			for(Integer k : j.getElementIterable()) {
				remain_element.getElements().add(k);
				
				this._elementsNotCovered.remove(k);
				}
			}
		
		}
		}
	//this._solnSets.add(selected_set);
	//if(selected_set.getId() != 0) {
		
		this._solnSets.add(selected_set);
		return selected_set;
	//}
	//return null;
	}
	public double getCoverage() {
		double a = remain_element.getElements().size();
		double b = this._model.totalList().size();
		return a/b;
	}
	public boolean compareCoverage() {
		if(old_coverage != getCoverage()) {
			return false;
		}
	return true;
	}
}
