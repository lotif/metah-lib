package br.unifor.metahlib.base;

import java.util.ArrayList;
import java.util.List;
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
	 * Given a group of parents, this method should return a group of sons, depending
	 * on the structure used.
	 * @param parents the list of parent solutions
	 * @return a list of child solutions
	 */
	public abstract List<double[]> getNeighbours(List<double[]> parents);
	
	/**
	 * Create a new random neighbor solution.
	 * @param solution base of neighborhood
	 * @return a random solution into neighborhood of informed solution 
	 */
	public Solution getRandomNeighbor(Solution solution){
		// TODO Otimizar solução eliminando o deprecated getNeighbours
		Object[] cities = solution.getValues();
		double[] parent = new double[cities.length];
		for (int i = 0; i < cities.length; ++i){
			parent[i] = (Integer)cities[i];
		}
		
		List<double[]> parents = new ArrayList<double[]>();
		parents.add(parent);

		List<double[]> neighbors = getNeighbours(parents);
		double[] n;
		n = neighbors.get(0);
		cities = new Object[n.length];
		for (int i = 0; i < n.length; ++i){
			cities[i] = new Integer((int) n[i]);
		}
			
		Solution neighbor = solution.duplicate();
		neighbor.setValues(cities);
		
		return neighbor;
	}
	
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
