package br.unifor.metahlib.problems.continuous.perturbers;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.Perturber;

public class UniformPertuber extends Perturber {

	public UniformPertuber(OptimizableFunction function) {
		super(function);
	}

	@Override
	protected double perturb(int dimension, double value) {
		double[] range = function.getDimensionRange(dimension);
        double maxDelta = (range[1] - range[0]) * maxPercentChange;
        double delta = random.nextDouble() * maxDelta;
        if ( random.nextBoolean() ){
            delta = -1 * delta;
        }

        return value + delta;
	}

}
