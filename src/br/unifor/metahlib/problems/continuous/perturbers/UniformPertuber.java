package br.unifor.metahlib.problems.continuous.perturbers;

import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.Perturber;

/**
 * Class that changes the values of a solution using uniform distribution.
 */
public class UniformPertuber extends Perturber {

	/**
	 * Class constructor.
	 * @param function function to be optimized
	 */
	public UniformPertuber(OptimizableFunction function) {
		super(function);
	}

	/**
	 * Perturb the value of a dimension. 
	 * @param dimension dimension of value
	 * @param value value to be perturbed
	 * @return perturbed value
	 */
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
