package deprecated;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.NeighborhoodStructure;

/**
 * The function that evaluates a solution of the Traveling Salesman Problem
 * 
 * @author marcelo lotif
 *
 */
public class TSPFunction extends NeighbourhoodableFunction {

	/**
	 * Variable to generate random values.
	 */
	private Random r = new Random();
	
	/**
	 * The definition of an instance of the TSP problem.
	 */
	private TSPProblemDefinition tspProblem;
	
	/**
	 * Constructor for the class
	 * 
	 * @param tspProblem the TSP problem instance
	 * @param neighbourhoodStructure a neighborhood structure to generate new 
	 * solutions based on existent ones
	 */
	public TSPFunction(TSPProblemDefinition tspProblem, NeighborhoodStructure neighbourhoodStructure) {
		super(neighbourhoodStructure);
		this.tspProblem = tspProblem;
		numVariables = tspProblem.getNumberOfCities();
	}
	
	/**
	 * Evaluates the function based on the TSP problem definition
	 * 
	 * @param x the solution to be evaluated
	 * @return the evaluation result
	 */
	@Override
	protected double evalImpl(double... x) {
		
		double totalCost = 0;
		for(int i = 0; i < x.length; i++){
			if(i < x.length - 1){
				totalCost += tspProblem.getDistance((int)x[i], (int)x[i + 1]);
			} else {
				totalCost += tspProblem.getDistance((int)x[i], (int)x[0]);
			}
		}
		
		return totalCost;
	}

	@Override
	public double getFeasibleValue() {
		return r.nextInt(numVariables);
	}

	@Override
	public double getOptimalEval() {
		return -1;
	}

	/**
	 * Generates a random feasible solution
	 */
	@Override
	public double[] getRandomSolution(){
		double[] x = new double[getNumVariables()];
		
		List<Integer> citiesLeft = new ArrayList<Integer>();
		for(int i = 0; i < x.length; i++){
			citiesLeft.add(i);
		}
		
		for(int i = 0; i < x.length; i++){
			int index = r.nextInt(citiesLeft.size());
			x[i] = (double) citiesLeft.get(index);
			
			citiesLeft.remove(index);
		}
		
		return x;
	}
	
	@Override
	public double maxValue() {
		return numVariables;
	}

	@Override
	public double minValue() {
		return 1;
	}

	public TSPProblemDefinition getTspProblem() {
		return tspProblem;
	}

	public void setTspProblem(TSPProblemDefinition tspProblem) {
		this.tspProblem = tspProblem;
	}

}
