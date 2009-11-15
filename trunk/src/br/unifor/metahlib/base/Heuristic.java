package br.unifor.metahlib.base;

/**
 * This class groups the commons characteristics of heuristics.
 */
public abstract class Heuristic {

	/**
	 * Stores the iteration where the best solution was found.
	 * Should be set inside the "execute" method of the child class. 
	 */
	protected int lastBestFoundOn = 0;
	
	/**
	 * Max quantity of iterations.
	 */
	protected int max_it = 100;
	
	/**
	 * The problem to be optimized by this heuristic.
	 */
	protected Problem problem;
	
	/**
	 * Construct a new heuristic for optimize the informed problem.
	 * @param problem the problem to be optimized
	 */
	public Heuristic(Problem problem){
		this.problem = problem;
	}
	
	/**
	 * The method which initiates the optimization.
	 * @return best solution found during execution
	 */
	public abstract Solution execute();
	
	/**
	 * Retrieves the problem to be optimized by the heuristic.
	 * @return the problem to be optimized
	 */
	public Problem getProblem(){
		return problem;
	}
	
	/**
	 * Set the problem to be optimized by the heuristic.
	 * @param problem the problem to be optimized
	 */
	public void setProblem(Problem problem){
		this.problem = problem;
	}
	
	/**
	 * Iteration where the best solution was found.
	 */
	public int getLastBestFoundOn() {
		return lastBestFoundOn;
	}
	
	/**
	 * Returns the max quantity of iterations.
	 */
	public int getMax_it(){
		return max_it;
	}

	/**
	 * Sets the max quantity of iterations.
	 */
	public void setMax_it(int value){
		max_it = value;
	}
}
