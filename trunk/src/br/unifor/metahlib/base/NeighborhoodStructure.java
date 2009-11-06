package br.unifor.metahlib.base;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that defines a neighborhood structure.
 * @author marcelo lotif
 */
public abstract class NeighborhoodStructure {

	/**
	 * Given a group of parents, this method should return a group of sons, depending
	 * on the structure used.
	 * @param parents the list of parent solutions
	 * @return a list of child solutions
	 */
	public abstract List<double[]> getNeighbours(List<double[]> parents);
	
	/**
	 * Create a new random neighbor solution.
	 * @param solution Base of neighborhood.
	 * @return A random solution into neighborhood of informed solution. 
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
			
		Solution neighbor = null;
		try {
			neighbor = (Solution) solution.clone();
			neighbor.setValues(cities);
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			assert(false);
		}
		
		return neighbor;
	}
}
