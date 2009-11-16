package br.unifor.metahlib.metaheuristics.grasp;

import java.util.List;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public abstract class GreedyRandomizedConstructor {

	protected double alpha;
	protected Problem problem;
	
	public GreedyRandomizedConstructor(Problem problem, double alpha){
		this.alpha = alpha;
		this.problem = problem;
	}

	public Solution generateGreedyRandomizedSolution(){
		Object[] values = new Object[problem.getDimension()];
		
		for(int i = 0; i < values.length; i++){
			List<SolutionElement> rcl = buildRestrictedCandidateList(values);
			
			int index = problem.getRandom().nextInt(rcl.size());
			
			values[i] = rcl.get(index).getValue();
			
		}
		
		Solution s = new Solution(problem);
		s.setValues(values);
		return s;
		
	}

	protected abstract List<SolutionElement> buildRestrictedCandidateList(Object[] values);

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
	
}
