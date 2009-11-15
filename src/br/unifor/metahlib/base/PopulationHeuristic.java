package br.unifor.metahlib.base;

/**
 * This class groups the commons characteristics of heuristics that have the concept of population 
 * of solutions at each iteration.
 */
public abstract class PopulationHeuristic extends Heuristic {
	
    /**
     * Quantity of individuals.
     */
	protected int populationSize = 50;

	/**
	 * Class constructor.
	 * @param problem problem to be optimized.
	 */
	public PopulationHeuristic(Problem problem) {
		super(problem);
	}

	/**
	 * Sets the quantity of individuals.
	 * @param populationSize
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	/**
	 * Returns the quantity of individuals.
	 * @return
	 */
	public int getPopulationSize() {
		return populationSize;
	}
}
