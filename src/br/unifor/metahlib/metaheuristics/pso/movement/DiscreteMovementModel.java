package br.unifor.metahlib.metaheuristics.pso.movement;

import br.unifor.metahlib.metaheuristics.pso.MovementModel;
import br.unifor.metahlib.metaheuristics.pso.Particle;

/**
 * Implements a movement model for discrete search space. 
 */
public class DiscreteMovementModel extends MovementModel {

	/**
	 * Moves a particle in the search space.
	 * @param p particle
	 */
	@Override
	public void move(Particle p) {
		// TODO Auto-generated method stub
		assert(false);
	}
	
	/**
	 * Limits the calculated velocity for a valid value for the informed dimension. 
	 * @param dimension dimension index
	 * @param velocity calculated velocity
	 * @return ranged velocity
	 */
	public double rangeVelocity(int dimension, double velocity){
		assert(false);
		return 0.0;
	}
	
	/**
	 * Creates a random particle velocity vector.
	 * @return velocity vector
	 */
	public double[] newRandomVelocity(){
		assert(false);
		return null;
	}
}
