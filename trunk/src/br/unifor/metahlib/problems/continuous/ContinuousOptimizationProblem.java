package br.unifor.metahlib.problems.continuous;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * Defines an instance of a continuous optimization problem.
 */
public class ContinuousOptimizationProblem extends Problem {
	
	/**
	 * Function to be optimized.
	 */
	protected OptimizableFunction function;	
	
	/**
	 * Constructs a new instance.
	 * @param function function to be optimized
	 * @param pertuber object responsible to perturb the solutions
	 */
	public ContinuousOptimizationProblem(OptimizableFunction function, Perturber perturber){
		this.function = function;
		this.setNeighborhoodStructure(perturber);
		
		FunctionCostEvaluator evaluator = new FunctionCostEvaluator(function); 
		this.setCostEvaluator(evaluator);
		
		Double optimalResult = function.getOptimalResult(); 
		if (optimalResult != null){
			setOptimalSolutionCost(evaluator.calculateCost(optimalResult));
		}
	}

	/**
	 * Creates a new random solution.
	 * @return new random solution.
	 */
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
	
	/**
	 * Limits the solution values to the allowed ranges. 
	 * @param values solution values
	 * @return ranged values
	 */
	@Override
	public Object[] rangeSolutionValues(Object[] values){
		Object[] result = new Object[values.length];
		for(int i = 0; i < values.length; ++i){
			result[i] = function.rangeValue(i, (Double) values[i]);
		}
		
		return result;
	}
	
	@Override
	public double[] getSolutionValueRange(int idx){
		return function.getDimensionRange(idx);
	}
	
	@Override
	public int getDimension(){
		return function.getDimensionCount();
	}
}
