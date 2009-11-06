package br.unifor.metahlib.base;

import java.util.ArrayList;
import java.util.List;

/**
 * An interface that defines a neighborhood structure.
 * 
 * @author marcelo lotif
 */
public abstract class NeighborhoodStructure {

	/**
	 * Given a group of parents, this method should return a group of sons, depending
	 * on the structure used.
	 * 
	 * @param parents the list of parent solutions
	 * @return a list of child solutions
	 */
	public abstract List<double[]> getNeighbours(List<double[]> parents);
	
	public Solution[] getNeighbors(Solution solution){
		// TODO Otimizar solução eliminando o deprecated getNeighbours
		Object[] cities = solution.getValues();
		double[] parent = new double[cities.length];
		for (int i = 0; i < cities.length; ++i){
			parent[i] = (Integer)cities[i];
		}
		
		List<double[]> parents = new ArrayList<double[]>();
		parents.add(parent);

		List<double[]> neighbors = getNeighbours(parents);
		Solution s;
		double[] n;
		Solution[] result = new Solution[neighbors.size()];
		for (int i = 0; i < neighbors.size(); ++i){
			n = neighbors.get(i);
			cities = new Object[n.length];
			for (int j = 0; j < n.length; ++j){
				cities[j] = new Integer((int) n[j]);
			}
			
			try {
				s = (Solution) solution.clone();
				s.setValues(cities);
				result[i] = s;
				
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
				assert(false);
			}
		}
		
		return result;

	}
}
