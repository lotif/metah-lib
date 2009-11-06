package br.unifor.metahlib.problems.continuous.perturbers;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.Perturber;

public class GaussianPerturber extends Perturber {

	public GaussianPerturber(OptimizableFunction function) {
		super(function);
	}

	@Override
	protected double perturb(int dimension, double value) {
		double[] range = function.getDimensionRange(dimension);
        double maxDelta = (range[1] - range[0]) * maxPercentChange;
        double delta = (random.nextGaussian() * (maxDelta/2)) + (maxDelta/2);
        if (random.nextBoolean()){
            delta = -1 * delta;
        }

        return value + delta;
	}
}
