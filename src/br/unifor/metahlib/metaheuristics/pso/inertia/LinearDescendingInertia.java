package br.unifor.metahlib.metaheuristics.pso.inertia;

import br.unifor.metahlib.metaheuristics.pso.Inertia;

/**
 * Implements a varying inertia weight with linear descending.
 */
public class LinearDescendingInertia extends Inertia {

	/**
	 * Inertia weight at first iteration.
	 */
	private double maxValue;
	
	/**
	 * Inertia  weight at last iteration.
	 */
    private double minValue;
    
    /**
     * Class constructor.
     * @param maxValue inertia weight at first iteration
     * @param minValue inertia weight at last iteration
     */
    public LinearDescendingInertia(double maxValue, double minValue){
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

	/**
	 * Creates a string representation of inertia calculation.
	 */
    @Override
    public String toString(){
        return "w(t) = " + maxValue + " - t/tmax * (" + maxValue + " - " + minValue + ");";
    }

	/**
	 * Calculates the inertia weight at informed iteration.
	 * @param currentIteration current iteration
	 * @param maxIterations max quantity of iterations
	 * @return inertia weight value into range [0,1]
	 */
    @Override
	public double calculate(int currentIteration, int maxIterations) {
        double value = maxValue - (currentIteration/maxIterations) * (maxValue - minValue);
        return value;
	}
}

