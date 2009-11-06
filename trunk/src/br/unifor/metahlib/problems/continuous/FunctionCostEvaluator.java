package br.unifor.metahlib.problems.continuous;

import br.unifor.metahlib.base.CostEvaluator;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.problems.continuous.OptimizableFunction.OptimizationType;

public class FunctionCostEvaluator extends CostEvaluator {
	
	private OptimizableFunction function;

	public FunctionCostEvaluator(OptimizableFunction function){
		this.function = function;
	}
	
	@Override
	protected double calculateCost(Solution s) {
		double value = function.execute(s.getValues());
		if (function.getOptimizationType() == OptimizationType.MAXIMIZATION){
			value = -1 * value;
		}
		return value;
	}

}
