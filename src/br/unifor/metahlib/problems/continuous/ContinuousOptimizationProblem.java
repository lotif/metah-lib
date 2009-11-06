package br.unifor.metahlib.problems.continuous;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.problems.continuous.perturbers.UniformPertuber;

public class ContinuousOptimizationProblem extends Problem {
	
	protected OptimizableFunction function;	
	
	public ContinuousOptimizationProblem(OptimizableFunction function){
		this.function = function;
		this.setNeighborhoodStructure(new UniformPertuber(function));
		this.setCostEvaluator(new FunctionCostEvaluator(function));
	}

	@Override
	public Solution newRandomSolution() {
		Object[] values = new Double[function.getDimensionCount()];
		for (int i = 0; i < values.length; ++i){
			double[] range = function.getDimensionRange(i);
			double min = range[0];
			double max = range[1];
	        double delta = max - min;
	        double value = (random.nextDouble() * delta) - min; 
			values[i] = function.rangeValue(i, value);
		}
		
		Solution s = new Solution(this);
		s.setValues(values);
		return s;
	}
}
