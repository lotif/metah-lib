package br.unifor.metahlib.metaheuristics.grasp;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.base.SolutionFactory;

public class GRASP extends Heuristic {

	private Heuristic localSearch;
	private SolutionFactory factory;
	
	public GRASP(Problem problem, Heuristic localSearch, int maxIterations, 
			SolutionFactory factory) {
		super(problem);
		this.localSearch = localSearch;
		this.max_it = maxIterations;
		this.factory = factory;
	}

	@Override
	public Solution execute() {
		Solution s = null;
		Solution s_;
		do {
			s_ = factory.newSolution();
			localSearch.getProblem().setInitialSolution(s_);
			s_ = localSearch.execute();
			if (s== null || s_.getCost() < s.getCost()){
				s = s_;
			}
			
			//System.out.println(String.format("it:%d, current: %f, best: %s.", current_it, 
			//		s.getCost(), optimizationResult.getBestSolution().getCost()));
			
		} while (endIteration(s_));
		
		return s;
	}

	public Heuristic getLocalSearch() {
		return localSearch;
	}

	public void setLocalSearch(Heuristic localSearch) {
		this.localSearch = localSearch;
	}

}
