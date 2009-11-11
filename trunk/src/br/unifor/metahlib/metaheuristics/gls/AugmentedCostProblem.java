package br.unifor.metahlib.metaheuristics.gls;

import java.util.List;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;


/**
 * The abstract class for the Augmented Cost Function of GLS metaheuristic.
 * Each problem must have an specific implementation of this class.
 * 
 * This class also treats with the penalties of the GLS Metaheuristic.
 * 
 * @author marcelo lotif
 *
 */
public abstract class AugmentedCostProblem extends Problem {

	/**
	 * The function that this instance is augmenting
	 */
	protected Problem problem;
	
	/**
	 * Class contructor
	 * 
	 * @param function The function that this instance is augmenting
	 * @param lambda The lambda parameter to calculate the feature's penalties
	 */
	public AugmentedCostProblem(Problem problem, AugmentedCostEvaluator augmentedCostEvaluator) {
		super();
		this.problem = problem;
		setCostEvaluator(augmentedCostEvaluator);
	}
	
	/**
	 * Given a problem solution, returns all the features belonging to this solution
	 * 
	 * @param solution a problem solution
	 * @return a list of features belonging to this solution
	 */
	public abstract List<SolutionFeature> getSolutionFeatures(Solution solution);
	
	@Override
	public Solution newRandomSolution() {
		return problem.newRandomSolution();
	}
	
	@Override
	public Object[] rangeSolutionValues(Object[] values){
		return problem.rangeSolutionValues(values);
	}
	
	@Override
	public double[] getSolutionValueRange(int idx){
		return problem.getSolutionValueRange(idx);
	}
	
	@Override
	public int getDimension(){
		return problem.getDimension();
	}
	
}
