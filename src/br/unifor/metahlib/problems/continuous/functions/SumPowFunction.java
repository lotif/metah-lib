package br.unifor.metahlib.problems.continuous.functions;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;

public class SumPowFunction extends OptimizableFunction {

	public SumPowFunction(){
		super(10/*dimensions*/);
		setAllRanges(-1, 1);
		optimalResult = 0.0;
		optimizationType = OptimizationType.MINIMIZATION;
	}
	
	@Override
	public double execute(Object[] values) {
		double sum = 0;
		for(int i = 0; i < values.length; i++){
			sum+= Math.pow(Math.abs((Double)values[i]), i+1);
		}
		return sum;
	}
}
