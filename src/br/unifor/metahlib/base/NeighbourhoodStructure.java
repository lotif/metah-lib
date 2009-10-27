package br.unifor.metahlib.base;

import java.util.List;

/**
 * An interface that defines a neighborhood structure.
 * 
 * @author marcelo lotif
 *
 */
public interface NeighbourhoodStructure {

	/**
	 * Given a group of parents, this method should return a group of sons, depending
	 * on the structure used.
	 * 
	 * @param parents the list of parent solutions
	 * @return a list of child solutions
	 */
	public List<double[]> getNeighbours(List<double[]> parents);
	
}
