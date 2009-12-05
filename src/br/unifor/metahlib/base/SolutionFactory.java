package br.unifor.metahlib.base;

/**
 * A common interface for solution constructors. 
 */
public interface SolutionFactory {
	
	/**
	 * Constructs a new solution.
	 * @return new solution.
	 */
	public Solution newSolution();
}
