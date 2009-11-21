package br.unifor.metahlib.metaheuristics.ga;

import java.util.Random;

/**
 * Class responsible to merge two solutions to create a new one.
 */
public abstract class CrossoverOperator {
	
	/**
	 * Problem random number generator.
	 */
	protected Random random;
	
	/**
	 * Crossovers the informed chains of genes creating two new chains.
	 * @param g1 
	 * @param g2
	 * @return new chains of genes
	 */
	public abstract Object[][] crossover(Object[] g1, Object[] g2);
	
	/**
	 * Returns the index of the value in values array if exists. Otherwise, returns -1.
	 */
	protected int indexOf(Object value, Object[] values){
		int idx = -1;
		for(int i = 0; i < values.length; ++i){
			if (values[i] != null && values[i].equals(value)){
				idx = i;
				break;
			}
		}
		
		return idx;
	}
	
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
	
	@Override
	public String toString(){
		return this.getClass().getName();		
	}
}
