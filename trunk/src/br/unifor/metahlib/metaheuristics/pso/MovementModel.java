package br.unifor.metahlib.metaheuristics.pso;

/**
 * Implements a model that determines how a particle moves in the search space. 
 */
public abstract class MovementModel {

	/**
	 * Moves a particle in the search space.
	 * @param p particle
	 */
	public abstract void move(Particle p);
	
	/**
	 * Limits the calculated velocity for a valid value for the informed dimension. 
	 * @param dimension dimension index
	 * @param velocity calculated velocity
	 * @return ranged velocity
	 */
	public abstract double rangeVelocity(int dimension, double velocity);
	
	/**
	 * Creates a random particle velocity vector.
	 * @return velocity vector
	 */
	public abstract double[] newRandomVelocity();
	
}
