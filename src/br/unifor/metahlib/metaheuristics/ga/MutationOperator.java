package br.unifor.metahlib.metaheuristics.ga;

import java.util.Random;

/**
 * Class responsible for apply a randomized change in a chain of genes.
 */
public abstract class MutationOperator {
	
	/**
	 * Problem random number generator.
	 */
	protected Random random;
	
	/**
	 * Mutate the genes. 
	 * @param genes genes that will be changed
	 * @param mutationProbability probability that mutation occurs
	 * @return true if a mutation occurred
	 */
    public abstract boolean mutate(Object[] genes, double mutationProbability);
    
	/**
	 * Sets the random number generator.
	 * @param random random number generator
	 */
	protected void setRandom(Random random) {
		this.random = random;
	}

	/**
	 * Returns the random number generator.
	 * @return random number generator
	 */
	protected Random getRandom() {
		return random;
	}
}
