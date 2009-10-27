package br.unifor.metahlib.base;

/**
 * This class groups the commons characteristics of a metaheuristic.
 * 
 * @author marcelo lotif
 *
 */
public abstract class Metaheuristic {
	
	/**
	 * Stores the iteration where the best solution for a function was found.
	 * Should be set inside the "execute" function of the child class. 
	 */
	protected int lastBestFoundOn = 0;
	
	/**
	 * The initial solution for the optimization. If null, it should start
	 * based on a random solution.
	 */
	protected double[] initialSolution = null;
	
	/**
	 * Retrieves the function to be optimized by the metaheuristic.
	 * 
	 * @return the function to be optimized
	 */
	public abstract Function getFunction();
	
	/**
	 * Set the function to be optimized by the metraheuristic
	 * 
	 * @param function the function to be optimized
	 */
	public abstract void setFunction(Function function);
	
	/**
	 * The method which initiates the optimization.
	 * 
	 * @return the result of the optimization
	 */
	public abstract double[] execute();
	
	public int getLastBestFoundOn() {
		return lastBestFoundOn;
	}
	
	public double[] getInitialSolution() {
		return initialSolution;
	}
	
	public void setInitialSolution(double[] initialSolution) {
		this.initialSolution = initialSolution;
	}
	
}
