package solver;

import model.SCPModel;
import util.ElementSet;
import java.util.SortedSet;
import java.util.TreeSet;

/** This is the main method that all solvers inherit from.  It is important
 *  to note how this solver calls nextBestSet() polymorphically!  Subclasses
 *  should *not* override solver(), they need only override nextBestSet().
 * 
 *  We'll assume models are well-defined here and do not specify Exceptions
 *  to throw.
 * 
 * @author ssanner@mie.utoronto.ca
 *
 */
public abstract class GreedySolver {
	
	protected String _name;			  // name of algorithm type
	protected double _alpha;          // minimum required coverage level in range [0,1]
	protected SCPModel _model;        // the SCP model we're currently operating on
	protected double _objFn;          // objective function value (*total cost sum* of all sets used)
	protected double _coverage;       // actual coverage fraction achieved
	protected long _compTime;         // computation time (ms)

	protected TreeSet<ElementSet> _solnSets; // could use array instead
	protected SortedSet<Integer> _elementsNotCovered; // set of the elements currently not covered by solution
	protected boolean _solved;        // whether or not the model has been solved
	
	// Basic setter (only one needed)
	public void setMinCoverage(double alpha) { _alpha = alpha; }
	public void setModel(SCPModel model) { _model = model; }
	
	// Basic getters
	public SCPModel getModel() {return _model;}
	public double getMinCoverage() { return _alpha; }
	public double getObjFn() { return _objFn; }
	public double getCoverage() { return _coverage; }
	public long getCompTime() { return _compTime; }
	public String getName() { return _name; }
	public SortedSet<Integer> getelementsNotCovered() {return _elementsNotCovered; }
	// TODO: Add any helper methods you need

	public void reset() {
		// TODO: you need to compelte this method to reset the class
		//_name = "";
		_objFn = 0.0;
		_coverage = 0.0;
		_compTime = 0;
		_solnSets = new TreeSet<ElementSet>();
		
		//_elementsNotCovered = new TreeSet<Integer>();
		//setModel(_model);
		//setMinCoverage(_alpha);
		
	}
	
	public boolean allSetsSelected() {
		for(ElementSet i : _model.getElementSetIterable()) {
			if(!_solnSets.contains(i)) {
				return false;
			}
		}
		return true;
	}
	/** Run the simple greedy heuristic -- add the next best set until either
	 *  (1) The coverage level is reached, or 
	 *  (2) There is no set that can increase the coverage.
	 */
	public void solve() {
		
		// Reset the solver
		reset();
		
		// TODO: Preliminary initializations
		//setModel(_model);
		//setMinCoverage(_alpha);
		//getelementsNotCovered();
		this._elementsNotCovered = new TreeSet<Integer>(_model.totalList());
		//_coverage = 0.0;
		this._solnSets = new TreeSet<ElementSet>();
		_objFn = 0.0;
		

		// Begin the greedy selection loop
		long start = System.currentTimeMillis();
		System.out.println("Running '" + getName() + "'...");
		System.out.println();
		// TODO: Fill in the main loop, pseudocode given below
		//
		// NOTE: In order to match the solution, pay attention to the following
		//       calculations (where you have to replace ALL-CAPS parts)
		//
		// int num_to_cover = (int)Math.ceil(_alpha * TOTAL_NUMBER_OF_ELEMENTS_IN_SCPMODEL);
		// int num_can_leave_uncovered = TOTAL_NUMBER_OF_ELEMENTS_IN_SCPMODEL - num_to_cover;
		//
		// while (NUM_ELEMENTS_NOT_COVERED > num_can_leave_uncovered 
		//        && ALL_POSSIBLE_SETS_HAVE_NOT_BEEN_SELECTED)
		//
		//      Call nextBestSet() to get the next best ElementSet to add (if there is one)
		// 		Update solution and local members
				
		// Record final set coverage, compTime and print warning if applicable
		int cover = (int)Math.ceil(_alpha*_model.elementsnum());
		int canleave = _model.elementsnum() - cover;
		
		while(_elementsNotCovered.size() > canleave && allSetsSelected() == false ) {
		//for(int i = 0; i< 8 ;i++){
			//System.out.println(_name);
			ElementSet e= nextBestSet();
			if(e == null) {
				break;
			}
			_solnSets.add(e);
			_coverage = getCoverage();
			//_elementsNotCovered.remove(e);
			
			
		}
		for(ElementSet e : _solnSets) {
			System.out.println("- Selected: " + e);
		}
		_coverage = getCoverage();
		//_coverage = (_elementsNotCovered.size())/this._model.totalList().size(); // TODO: Correct this, should be coverage of solution found
		_compTime = System.currentTimeMillis() - start;
		
		if (_coverage < _alpha)
			System.out.format("\nWARNING: Impossible to reach %.2f%% coverage level.\n", 100*_alpha);
		System.out.println("Done.");
	}
	
	/** Returns the next best set to add to the solution according to the heuristic being used.
	 * 
	 *  NOTE 1: This is the **only** method to be implemented in child classes.
	 *  
	 *  NOTE 2: If no set can improve the solution, returns null to allow the greedy algorithm to terminate.
	 *  
	 *  NOTE 3: This references an ElementSet class which is a tuple of (Set ID, Cost, Integer elements to cover)
	 *          which you must define.
	 * 
	 * @return
	 */
	public abstract ElementSet nextBestSet(); // Abstract b/c it must be implemented by subclasses
	
	/** Print the solution
	 * 
	 */
	public void print() {
		System.out.println("\n'" + getName() + "' results:");
		System.out.format("'" + getName() + "'   Time to solve: %dms\n", _compTime);
		System.out.format("'" + getName() + "'   Objective function value: %.2f\n", _objFn);
		System.out.format("'" + getName() + "'   Coverage level: %.2f%% (%.2f%% minimum)\n", 100*_coverage, 100*_alpha);
		System.out.format("'" + getName() + "'   Number of sets selected: %d\n", _solnSets.size());
		System.out.format("'" + getName() + "'   Sets selected: ");
		for (ElementSet s : _solnSets) {
			System.out.print(s.getId() + " ");
		}
		System.out.println("\n");
	}
	
	/** Print the solution performance metrics as a row
	 * 
	 */
	public void printRowMetrics() {
		System.out.format("%-25s%12d%15.4f%17.2f\n", getName(), _compTime, _objFn, 100*_coverage);
	}	
}
