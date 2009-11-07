package br.unifor.metahlib.problems.continuous.functions;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;

public class SaloFunction extends OptimizableFunction {

	/**
	 * Class constructor.
	 */
	public SaloFunction(){
		super(10/*dimensions*/);
		setAllRanges(-100, 100);
		optimalResult = 0.0;
		optimizationType = OptimizationType.MINIMIZATION;
	}
	
	/**
	 * Executes the function.
	 */
	@Override
	public double eval(Object[] values) {
        double sum = 0;
        for ( int i = 0; i < values.length; ++i ){
            sum+= Math.pow( (Double) values[i], 2);
        }
        double sqrtSum = Math.sqrt(sum);

        double result = -1 * Math.cos(2 * Math.PI * sqrtSum);
        result+= 0.1 * sqrtSum;
        result+= 1;

        return result;
	}
}
