package br.unifor.metahlib.problems.continuous.functions;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;

public class RanaFunction extends OptimizableFunction {

	public RanaFunction() {
		super(2/*dimensions*/);
		setAllRanges(-512, 512);
		optimalResult = -511.7329;
		optimizationType = OptimizationType.MINIMIZATION;
	}

	@Override
	public double execute(Object[] values) {
        assert( values.length == 2);
        Double x1 = ((Double) values[0]);
        Double x2 = ((Double) values[1]);

        double res = 0;
        res+= x1 *
              Math.sin( Math.sqrt( Math.abs(x2 + 1 - x1))) *
              Math.cos( Math.sqrt( Math.abs(x2 + 1 + x1)));

        res+= (x2 + 1) *
              Math.cos( Math.sqrt( Math.abs(x2 + 1 - x1))) *
              Math.sin( Math.sqrt( Math.abs(x2 + 1 + x1)));

        return res;
	}

}
