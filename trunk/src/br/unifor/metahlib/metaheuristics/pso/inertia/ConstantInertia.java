package br.unifor.metahlib.metaheuristics.pso.inertia;

import br.unifor.metahlib.metaheuristics.pso.Inertia;

/**
 * Implements a constant inertia weight for a PSO problem.
 */
public class ConstantInertia extends Inertia {
    
	/**
	 * Inertia weight.
	 */
	private double value;

    /**
     * Class constructor.
     * @param value constant inertia weight
     */
	public ConstantInertia(double value){
        this.value = value;
    }
	
	/**
	 * Creates a string representation of inertia calculation.
	 */
	@Override
    public String toString(){
        return "w=" + value;
    }

	/**
	 * Returns a constant inertia weight.
	 * @param currentIteration current iteration
	 * @param maxIterations max quantity of iterations
	 * @return inertia weight value into range [0,1]
	 */
    @Override
	public double calculate(int currentIteration, int maxIterations) {
		return value;
	}
}
