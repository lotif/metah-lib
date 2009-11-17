package br.unifor.metahlib.base;

import java.util.Random;

/**
 * A class that defines a neighborhood structure.
 * @author marcelo lotif
 */
public abstract class NeighborhoodStructure {
	
	/**
	 * Random number generator. Problem class automatically sets this property on setNeighborhoodStructure() method. 
	 */
	protected Random random;

	/**
	 * Create a new random neighbor solution.
	 * @param solution base of neighborhood
	 * @return a random solution into neighborhood of informed solution 
	 */
	public abstract Solution getRandomNeighbor(Solution solution);
	
	/**
	 * Returns the random number generator.
	 * @return random number generator
	 */
	public Random getRandom() {
		return random;
	}

	/**
	 * Sets the random number generator.
	 * @param random number generator
	 */
	public void setRandom(Random random) {
		this.random = random;
	}
}
