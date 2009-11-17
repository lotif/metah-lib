package br.unifor.metahlib.base;

/**
 * This class groups the commons characteristics of heuristics that have the concept of a solution
 * moving in the search space.
 */
public abstract class TrajectoryHeuristic extends Heuristic {
	
	/**
	 * Responsible to create the neighbors of a solution.
	 */
	protected NeighborhoodStructure neighborhoodStructure;

	public TrajectoryHeuristic(Problem problem, NeighborhoodStructure neighborhoodStructure) {
		super(problem);
		setNeighborhoodStructure(neighborhoodStructure);
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
		this.neighborhoodStructure.setRandom(random);
	}
}
