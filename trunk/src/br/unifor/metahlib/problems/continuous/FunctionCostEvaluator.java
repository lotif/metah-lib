package br.unifor.metahlib.problems.continuous;

import br.unifor.metahlib.base.CostEvaluator;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.problems.continuous.OptimizableFunction.OptimizationType;

/**
 * Cost evaluator for continuous optimization problems.
 */
public class FunctionCostEvaluator extends CostEvaluator {
	
	/**
	 * Function to be optimized.
	 */
	private OptimizableFunction function;

	/**
	 * Class constructor.
	 * @param function function to be optimized
	 */
	public FunctionCostEvaluator(OptimizableFunction function){
		this.function = function;
	}
	
	/**
	 * Returns the result of function if is a minimization problem or the 
	 * inverted result if otherwise.  
	 */
	@Override
	protected double calculateCost(Solution s) {
		double value = function.execute(s.getValues());
		return calculateCost(value);
	}
	
	/**
	 * Calculates a cost of a function result.
	 * @param value result of the function execution.
	 * @return Cost of result.
	 */
	public double calculateCost(double value){
		if (function.getOptimizationType() == OptimizationType.MAXIMIZATION){
			value = -1 * value;
		}
		return value;
	}

}
