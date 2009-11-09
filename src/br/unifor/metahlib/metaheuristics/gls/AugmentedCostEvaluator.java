package br.unifor.metahlib.metaheuristics.gls;

import br.unifor.metahlib.base.CostEvaluator;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public abstract class AugmentedCostEvaluator extends CostEvaluator {

	/**
	 * A matrix of penalties
	 */
	protected double[][] penalties;
	
	/**
	 * The lambda parameter to calculate the feature's penalties
	 */
	protected double lambda;
	
	protected Problem problem;

	public AugmentedCostEvaluator(Problem problem, double lambda){
		this.problem = problem;
		this.lambda = lambda;
	}
	
	/**
	 * Method responsible for calculate the cost of a solution. Must be implemented 
	 * in a descending class.
	 * @param s solution to evaluate
	 * @return cost of solution
	 */
	protected double calculateCost(Solution s){
		return calculateAugmentedCost(s);
	}

	/**
	 * Calculates the augmented cost function.
	 * 
	 * @param s the solution instance
	 * @return the augmented cost value
	 */
	protected abstract double calculateAugmentedCost(Solution s);

	/**
	 * Returns the penalty value for a given feature
	 * 
	 * @param feature the feature
	 * @return the penalty for this feature
	 */
	public abstract double getP(SolutionFeature feature);
	
	/**
	 * Updates the penalty for a given feature
	 * 
	 * @param penalizedFeature the feature to be penalized
	 */
	public abstract void updateP(SolutionFeature penalizedFeature);
	
}
