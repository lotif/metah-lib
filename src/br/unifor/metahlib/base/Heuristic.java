package br.unifor.metahlib.base;

import java.util.Random;

/**
 * This class groups the commons characteristics of heuristics.
 */
public abstract class Heuristic {

	/**
	 * Max quantity of iterations.
	 */
	protected int current_it = 0;
	
	/**
	 * Max quantity of iterations.
	 */
	protected int max_it = 100;
	
	/**
	 * The problem to be optimized by this heuristic.
	 */
	protected Problem problem;
	
	/**
	 * Problem random number generator.
	 */
	protected Random random;
	
	protected OptimizationResult optimizationResult; 	
	
	/**
	 * Construct a new heuristic for optimize the informed problem.
	 * @param problem the problem to be optimized
	 */
	public Heuristic(Problem problem){
		this.problem = problem;
		this.random = problem.getRandom();
	}
	
	/**
	 * Abstract method with the optimization algorithm. It must be implemented by subclasses.
	 * @return best solution found during execution
	 */
	public abstract Solution execute();
	
	/**
	 * The method which starts the optimization.
	 * @return optimization result with the best solution found and execution statistics 
	 */
	public OptimizationResult run(){
		optimizationResult = new OptimizationResult(); 
		current_it = 0;
		optimizationResult.start();
		execute();
		optimizationResult.finish();
		
		return optimizationResult;
	}
	
	/**
	 * Method used in subclasses to notify the completion of an iteration of the algorithm.
	 * @param currentSolution solution found at iteration
	 * @return true if the algorithm should continue to execute
	 */
	public boolean endIteration(Solution currentSolution){
		if (optimizationResult != null){
			optimizationResult.endIteration(currentSolution);
		}
		current_it++;
		return current_it < max_it;
	}
	
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
