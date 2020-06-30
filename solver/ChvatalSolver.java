package solver;

import java.util.TreeSet;

import util.ElementSet;

public class ChvatalSolver extends GreedySolver {
// TODO: you need add any instance variable and method (including overriding methods)
	private ElementSet covered_element;
	private TreeSet<Double> sortedRatio;
	private ElementSet selected_set;
	
	public ChvatalSolver() {
		this._name = "Chvatal";
		covered_element = new ElementSet();
		sortedRatio = new TreeSet<Double>();
		//copy = getModel();
		
		//copy= new SCPModel(_model);

	}
	
	public void reset() {
		covered_element = new ElementSet();
		this._elementsNotCovered = new TreeSet<Integer>(_model.totalList());
		_solnSets = new TreeSet<ElementSet>();
	}
	
	public ElementSet nextBestSet(){
		double least_ratio = 0.0;
		selected_set = null;
		sortedRatio = new TreeSet<Double>();
		
		//Iterator it = this._model.getElementSetIterable().iterator();
		for(ElementSet i : _model.getElementSetIterable()) {
			if(!_solnSets.contains(i)) {
				sortedRatio.add(i.getCost()/(i.compareTo2(covered_element)));
			}
		}
		
		least_ratio = sortedRatio.first();
		for(ElementSet j : _model.getElementSetIterable()) {
			if(!_solnSets.contains(j)) {
			if(j.getCost()/j.compareTo2(covered_element) == least_ratio) {
				selected_set = new ElementSet(j);
				this._objFn += j.getCost();
				for(Integer k : j.getElementIterable()) {
					covered_element.getElements().add(k);
					this._elementsNotCovered.remove(k);
					
				}
			}
				
				//this._model.getElementsSet().remove(j);
				//_model.remove(j);
				//return selected_set;
				_solnSets.add(selected_set);
				return selected_set;
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
