package br.unifor.metahlib.base;

/**
 * This class groups the commons characteristics of heuristics that have the concept of a solution
 * moving in the search space.
 */
public abstract class TrajectoryHeuristic extends Heuristic {

	public TrajectoryHeuristic(Problem problem) {
		super(problem);
	}

}
