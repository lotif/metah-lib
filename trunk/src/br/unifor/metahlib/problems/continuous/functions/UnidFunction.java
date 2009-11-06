package br.unifor.metahlib.problems.continuous.functions;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;

public class UnidFunction extends OptimizableFunction {

	public UnidFunction(){
		super(1/*dimensions*/);
		setAllRanges(0, 1);
		optimalResult = 1.0;
		optimizationType = OptimizationType.MAXIMIZATION;
	}
	
	@Override
	public double execute(Object[] values) {
        assert(values.length == 1);
        double value = (Double) values[0];

        double exp = -2 * Math.pow( ( (value - 0.1) / 0.9 ), 2 );
        double pot = Math.pow( 2, exp);

        double base = Math.sin( 5 * Math.PI * value);
        double sin = Math.pow( base, 6);

        return pot * sin;
	}
}
