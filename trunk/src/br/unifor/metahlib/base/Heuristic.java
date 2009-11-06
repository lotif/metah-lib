package br.unifor.metahlib.base;

public abstract class Heuristic {

	/**
	 * Stores the iteration where the best solution for a function was found.
	 * Should be set inside the "execute" function of the child class. 
	 */
	protected int lastBestFoundOn = 0;
	
	/**
	 * The problem to be optimized by the heuristic.
	 */
	protected Problem problem;
	
	public Heuristic(Problem problem){
		this.problem = problem;
	}
	
	/**
	 * The method which initiates the optimization.
	 * @return the result of the optimization
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
	 * Set the problem to be optimized by the heuristic
	 * @param problem The problem to be optimized
	 */
	public void setProblem(Problem problem){
		this.problem = problem;
	}
	
	public int getLastBestFoundOn() {
		return lastBestFoundOn;
	}
}
