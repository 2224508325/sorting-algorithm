package solver;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import model.SCPModel;
import util.ElementSet;

public class GreedyCostSolver extends GreedySolver {
	
// TODO: you need add any instance variable and method (including overriding methods)
	private ElementSet covered_element;
	private TreeSet<Double> sortedCost;
	private ElementSet selected_set;
	//private SCPModel copy;
	
	public void reset() {
		covered_element = new ElementSet();
		this._elementsNotCovered = new TreeSet<Integer>(_model.totalList());
		_solnSets = new TreeSet<ElementSet>();
	}
	
	public GreedyCostSolver() {
		this._name = "Cost";
		covered_element = new ElementSet();
		sortedCost = new TreeSet<Double>();
		//copy = getModel();
		
		//copy= new SCPModel(_model);

	}
	
	public ElementSet nextBestSet() {
		double least_cost = 0.0;
		selected_set = null;
		sortedCost = new TreeSet<Double>();
		
		//Iterator it = this._model.getElementSetIterable().iterator();
		for(ElementSet i : _model.getElementSetIterable()) {
			if(!_solnSets.contains(i)) {
				sortedCost.add(i.getCost());
			}
		}
		
		least_cost = sortedCost.first();
		
		for(ElementSet j : _model.getElementSetIterable()) {
			if(!_solnSets.contains(j)) {
			if(j.getCost() == least_cost && j.compareTo2(covered_element) > 0) {
				selected_set = new ElementSet(j);
				this._objFn += j.getCost();
				for(Integer k : j.getElementIterable()) {
					covered_element.getElements().add(k);
					this._elementsNotCovered.remove(k);
					
				}
				
				//this._model.getElementsSet().remove(j);
				//_model.remove(j);
				//return selected_set;
				_solnSets.add(selected_set);
				return selected_set;
			}
			}
		}
		
	return selected_set;
	}
	public double getCoverage() {
		double a = covered_element.getElements().size();
		double b = _model.totalList().size();
		return a/b;
	}
}