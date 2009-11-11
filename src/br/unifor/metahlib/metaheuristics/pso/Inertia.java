package br.unifor.metahlib.metaheuristics.pso;

/**
 * Calculates the inertia of particles.
 */
public abstract class Inertia {
	
	/**
	 * Calculates the inertia weight at informed iteration.
	 * @param currentIteration current iteration
	 * @param maxIterations max quantity of iterations
	 * @return inertia weight value into range [0,1]
	 */
	public abstract double calculate(int currentIteration, int maxIterations);
}
