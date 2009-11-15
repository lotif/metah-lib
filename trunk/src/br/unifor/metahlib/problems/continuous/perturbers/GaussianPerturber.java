package br.unifor.metahlib.problems.continuous.perturbers;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.Perturber;

/**
 * Class that changes the values of a solution using Gaussian distribution.
 */
public class GaussianPerturber extends Perturber {

	/**
	 * Class constructor.
	 * @param function function to be optimized
	 */
	public GaussianPerturber(OptimizableFunction function) {
		super(function);
	}

	/**
	 * Perturb the value of a dimension. 
	 * @param dimension dimension of value
	 * @param value value to be perturbed
	 * @return perturbed value
	 */
	@Override
	public double perturb(int dimension, double value) {
		double[] range = function.getDimensionRange(dimension);
        double maxDelta = (range[1] - range[0]) * maxPercentChange;
        double delta = (random.nextGaussian() * (maxDelta/2)) + (maxDelta/2);
        if (random.nextBoolean()){
            delta = -1 * delta;
        }

        return value + delta;
	}
}
