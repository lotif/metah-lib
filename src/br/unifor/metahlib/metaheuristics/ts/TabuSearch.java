package br.unifor.metahlib.metaheuristics.ts;

import java.util.ArrayList;
import java.util.Collections;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.base.SolutionFactory;
import br.unifor.metahlib.base.TrajectoryHeuristic;

/**
 * An implementation of Tabu Search metaheuristic.
 */ 
public class TabuSearch extends TrajectoryHeuristic {
	
	/**
	 * Initial solution constructor.
	 */
	private SolutionFactory solutionFactory;
	
	/**
	 * Tabu list implementation.
	 */
	private TabuList tabuList;

	/**
	 * Class constructor.
	 * @param problem the problem to be solved
	 * @param neighborhoodStructure neighborhood structure of the problem
	 * @param solutionFactory responsible for the initial solution creation
	 * @param tabuList tabu list implementation
	 * @param max_it max quantity of iterations
	 */
	public TabuSearch(Problem problem, NeighborhoodStructure neighborhoodStructure,
			SolutionFactory solutionFactory, TabuList tabuList, int max_it ) {
		super(problem, neighborhoodStructure);
		this.solutionFactory = solutionFactory;
		this.tabuList = tabuList;
		this.setMax_it(max_it);
	}

	@Override
	public Solution execute() {
		Solution s;
		if (problem.hasInitialSolution()){
			s = problem.getInitialSolution();
		} else {
			s = solutionFactory.newSolution();
		}
		Solution best = s;
		
		do {
			ArrayList<Solution> neighbors = neighborhoodStructure.getAllNeighbors(s);
			ArrayList<Solution> tabuSolutions = tabuList.removeTabuSolutions(neighbors);
			Collections.sort(neighbors);
			s = neighbors.get(0);
			if (s.getCost() < best.getCost()){
				best = s;
				System.out.println("Found best: " + best.getCost());
				
			} else {
				if (tabuSolutions.size() > 0){
					Collections.sort(tabuSolutions);
					Solution bestTabu = tabuSolutions.get(0);
					if (bestTabu.getCost() < best.getCost()){
						best = bestTabu;
						s = best;
						System.out.println("Found best (aspiration criteria): " + best.getCost());
					}
				}
			}
			
			tabuList.markAsTabu(s);
			
		} while (endIteration(s));
		
		return best;
	}

}
