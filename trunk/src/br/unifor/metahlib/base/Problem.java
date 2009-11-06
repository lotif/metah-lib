package br.unifor.metahlib.base;

import java.util.Random;

public abstract class Problem {
	
	/**
	 * Cost of optimal solution. Null when optimal solution is unknown. 
	 */
	private Double optimalSolutionCost;
	
	/**
	 * Initial solution. If wasn't informed, an initial randomized solution will be created.
	 */
	private Solution initialSolution;
	
	/**
	 * Responsible to evaluate the cost of a solution.
	 */
	private CostEvaluator costEvaluator;
	
	/**
	 * Responsible to create the neighbors of a solution.
	 */
	private NeighborhoodStructure neighborhoodStructure;
	
	/**
	 * Unique random generator. Allows reproducible executions when a seed is informed through 
	 * the setRandomSeed method.
	 */
	protected Random random;

	/**
	 * Creates a new instance of Problem. 
	 */
	public Problem(){
		this.random = new Random();
	}

	/**
	 * Informs the cost of optimal solution.
	 */
	public void setOptimalSolutionCost(Double cost) {
		optimalSolutionCost = cost;
	}

	/**
	 * Returns the cost of optimal solution. Null when optimal solution is unknown.
	 */
	public Double getOptimalSolutionCost() {
		return optimalSolutionCost;
	}

	/**
	 * Returns the object responsible to evaluate the cost of a solution.
	 */
	public CostEvaluator getCostEvaluator() {
		return costEvaluator;
	}
	
	/**
	 * Set the object responsible to evaluate the cost of a solution.
	 */
	public void setCostEvaluator(CostEvaluator costEvaluator) {
		this.costEvaluator = costEvaluator;
	}

	/**
	 * Returns the object responsible to create the neighbors of a solution.
	 */
	public NeighborhoodStructure getNeighborhoodStructure() {
		return neighborhoodStructure;
	}
	
	/**
	 * Sets the object responsible to create the neighbors of a solution.
	 */
	public void setNeighborhoodStructure(NeighborhoodStructure neighborhoodStructure) {
		this.neighborhoodStructure = neighborhoodStructure;
	}

	/**
	 * Returns the random number generator of the problem.
	 */
	public Random getRandom() {
		return random;
	}
	
	/**
     * Sets the seed of this random number generator.  
	 * @param seed the initial seed
	 */
	public void setRandomSeed(long seed){
		random.setSeed(seed);
	}
	
	/**
	 * Creates a new random solution.
	 * @return new random solution.
	 */
	public abstract Solution newRandomSolution();

	/**
	 * Informs the initial solution of the problem.
	 * @param initialSolution initial solution
	 */
	public void setInitialSolution(Solution initialSolution) {
		this.initialSolution = initialSolution;
	}

	/**
	 * Gets the initial solution. If an initial solution hasn't informed before, a new
	 * random solution will be created.
	 * @return the initial solution
	 */
	public Solution getInitialSolution() {
		if (initialSolution == null){
			initialSolution = newRandomSolution();
		}
		return initialSolution;
	}
}
