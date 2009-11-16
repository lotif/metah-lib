package br.unifor.metahlib.metaheuristics.grasp;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public class GRASP extends Heuristic {

	private Heuristic localSearch;
	private GreedyRandomizedConstructor grc;
	
	public GRASP(Problem problem, Heuristic localSearch, int maxIterations, GreedyRandomizedConstructor grc) {
		super(problem);
		this.localSearch = localSearch;
		this.max_it = maxIterations;
		this.grc = grc;
	}

	@Override
	public Solution execute() {
		
		Solution s = null;
		
		for(int i = 0; i < max_it; i++){
			Solution s_ = grc.generateGreedyRandomizedSolution();
			localSearch.getProblem().setInitialSolution(s_);
			s_ = localSearch.execute();
			
			if(s == null || s_.getCost() < s.getCost()){
				s = s_;
			}
			
			System.out.println(i + ": " + s.getCost());
		}
		
		return s;
	}

	public Heuristic getLocalSearch() {
		return localSearch;
	}

	public void setLocalSearch(Heuristic localSearch) {
		this.localSearch = localSearch;
	}

}
