package br.unifor.metahlib.problems.continuous.functions;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;

public class AckleyFunction extends OptimizableFunction {
	
	/**
	 * Class constructor.
	 */
	public AckleyFunction(){
		super(10/*dimensions*/);
		setAllRanges(-32.768, 32.768);
		optimalResult = 0.0;
		optimizationType = OptimizationType.MINIMIZATION;
	}

	/**
	 * Executes the function.
	 */
	@Override
	public double eval(Object[] values) {
		double sum1 = 0;
		double sum2 = 0;
		
		for(int i = 0; i < values.length; i++){
			sum1 += Math.pow((Double) values[i], 2);
			sum2 += Math.cos(2 * Math.PI * (Double) values[i]);
		}
		
		double k1 = -0.2*(Math.sqrt( (1.0/values.length) * sum1));
		double k2 = (1.0 / values.length) * sum2;
		
		double f = -20 * Math.exp(k1) - Math.exp(k2) + 20 + Math.exp(1);
		
		return f;
	}
}
