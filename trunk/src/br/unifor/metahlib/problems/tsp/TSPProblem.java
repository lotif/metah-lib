package br.unifor.metahlib.problems.tsp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * Defines an instance of a Traveling Salesman Problem.
 */
public class TSPProblem extends Problem {
	
	/**
	 * TSP instance with cities distances.
	 */
	private TSPInstance instance;
	
	/**
	 * Optimal tour or null if it's unknown.
	 */
	private Solution optimalTour;

	/**
	 * Constructs a new TSPProblem instance.
	 * @param file TSPlib file
	 * @param neighborhoodStructure object responsible to create the neighbors of a solution
	 * @throws IOException
	 * @throws EdgeWeightTypeNotSupported
	 */
	public TSPProblem(File file) throws IOException, EdgeWeightTypeNotSupported, EdgeWeightFormatNotSupported {
		super();
		TSPInstance ds = new TSPInstance(file); 
		setCostEvaluator(new TourCostEvaluator(ds));
		setInstance(ds);
	}

	/**
	 * Creates a new random solution.
	 * @return new random solution.
	 */
	@Override
	public Solution newRandomSolution() {
		Solution s = new Solution(this);
		Object[] tour = new Object[instance.getDimension()];
		
		ArrayList<Integer> citiesLeft = new ArrayList<Integer>();
		for(int i = 1; i <= tour.length; i++){
			citiesLeft.add(i);
		}
		
		for(int i = 0; i < tour.length; i++){
			int idx = random.nextInt(citiesLeft.size());
			tour[i] = citiesLeft.remove(idx);
		}
		s.setValues(tour);
		
		return s;
	}

	/**
	 * Returns the TSP instance with cities distances.
	 * @return a TSPInstance instance
	 */
	public TSPInstance getInstance() {
		return instance;
	}

	/**
	 * Sets the TSP instance with cities distances.
	 * @param instance a TSPInstance instance
	 */
	public void setInstance(TSPInstance instance) {
		this.instance = instance;
		ArrayList<Integer> tour = instance.getOptimalTour();
		if (tour != null){
			optimalTour = new Solution(this);
			optimalTour.setValues(tour.toArray());
			setOptimalSolutionCost(optimalTour.getCost());
		}
	}
	
	/**
	 * Checks if cities indexes are valid. 
	 * @param values solution values
	 * @return ranged values
	 */
	@Override
	public Object[] rangeSolutionValues(Object[] values){
		Object[] result = new Object[values.length];
		for(int i = 0; i < values.length; ++i){
			int idx = (Integer) values[i];
			if (idx >= 1 && idx <= instance.getDimension()){
				result[i] = idx;
			} else {
				throw new IndexOutOfBoundsException("Tour contains invalid index at position "
						+ i + ". Index found: " + idx + ".");
			}
		}
		
		return result;
	}
	
	@Override
	public double[] getSolutionValueRange(int idx){
		return new double[] { 1.0, instance.getDimension() };
	}
	
	@Override
	public int getDimension(){
		return instance.getDimension();
	}
	
	public Solution getOptimalTour(){
		return optimalTour;
	}
}
