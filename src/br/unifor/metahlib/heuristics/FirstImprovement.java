package br.unifor.metahlib.heuristics;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.base.TrajectoryHeuristic;

/**
 * Implements the first improvement method. The method selects the first
 * solution found better than the actual solution provided.
 * 
 * @author Nathanael de Castro Costa
 */
public class FirstImprovement extends TrajectoryHeuristic {

	/**
	 * Holds the actual solution
	 */
	protected Solution solution;
	
	/**
	 * @param problem
	 *            the problem in use
	 * @param neighborhoodStructure
	 *            the neighborhood structure to use
	 * @param solution
	 *            the solution to be improved
	 */
	public FirstImprovement(Problem problem,
			NeighborhoodStructure neighborhoodStructure, Solution solution, int maxIterations) {

		super(problem, neighborhoodStructure);
		
		this.solution = solution;
		this.max_it = maxIterations;
	}

	@Override
	public Solution execute() {

		double eval = problem.getCostEvaluator().eval(solution);
		
		for(int i = 0; i < max_it; i++){
			Solution _x = neighborhoodStructure.getRandomNeighbor(solution);
			
			eval = problem.getCostEvaluator().eval(solution);
			double _eval = problem.getCostEvaluator().eval(_x);
			
			if(_eval < eval){
				solution = _x;
				break;
			}
			
			endIteration(solution);
		}
		
		return solution;
	}

}
