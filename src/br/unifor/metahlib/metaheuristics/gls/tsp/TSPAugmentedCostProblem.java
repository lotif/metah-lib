package br.unifor.metahlib.metaheuristics.gls.tsp;

import java.util.ArrayList;
import java.util.List;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.gls.AugmentedCostProblem;
import br.unifor.metahlib.metaheuristics.gls.SolutionFeature;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import deprecated.Function;
import deprecated.TSPFunction;

/**
 * An implementation of the AugmentedCostFunction for the TSPFunction class
 * 
 * @author marcelo lotif
 *
 */
public class TSPAugmentedCostProblem extends AugmentedCostProblem {
	
	/**
	 * The contructor of the class
	 * 
	 * @param function the TSPFunction instance to be augmented
	 * @param lambda the GLS lambda parameter to calculate the penalties
	 */
	public TSPAugmentedCostProblem(TSPProblem problem, TSPAugmentedCostEvaluator augmentedCostEvaluator) {
		super(problem, augmentedCostEvaluator);
	}

	/**
	 * Given a problem solution, returns all the features belonging to this solution
	 * 
	 * @param solution a problem solution
	 * @return a list of features belonging to this solution
	 */
	@Override
	public List<SolutionFeature> getSolutionFeatures(Solution solution) {
		List<SolutionFeature> solutionFeatures = new ArrayList<SolutionFeature>();
		
		TSPProblem f = (TSPProblem) problem;
		
		Object[] v = solution.getValues();
		
		Integer[] values = new Integer[v.length];
		for(int i = 0; i < v.length; i++){
			values[i] = Integer.valueOf("" + v[i]);
		}
		
		for(int i = 0; i < values.length; i++){			
			if(i != values.length - 1){
				double cost = f.getDataSet().getDistance(values[i], values[i + 1]);
				solutionFeatures.add(new TSPSolutionFeature(values[i], values[i + 1], cost));
			} else {
				double cost = f.getDataSet().getDistance(values[i], values[1]);
				solutionFeatures.add(new TSPSolutionFeature(values[i], values[1], cost));
			}
		}
		
		return solutionFeatures;
	}
	
}
