package br.unifor.metahlib.problems.continuous.functions;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;

public class SumPowFunction extends OptimizableFunction {

	/**
	 * Class constructor.
	 */
	public SumPowFunction(){
		super(10/*dimensions*/);
		setAllRanges(-1, 1);
		optimalResult = 0.0;
		optimizationType = OptimizationType.MINIMIZATION;
	}
	
	/**
	 * Executes the function.
	 */
	@Override
	public double eval(Object[] values) {
		double sum = 0;
		for(int i = 0; i < values.length; i++){
			sum+= Math.pow(Math.abs((Double)values[i]), i+1);
		}
		return sum;
	}
}
