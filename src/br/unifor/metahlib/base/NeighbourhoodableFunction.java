package br.unifor.metahlib.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines if a function has a neighborhood structure to be defined.
 * This class is used if the generation of new solutions cannot be made
 * the way the Function superclass does.
 * 
 * @author marcelo lotif
 *
 */
public abstract class NeighbourhoodableFunction extends Function {

	/**
	 * The neighborhood structure of the function.
	 */
	protected NeighbourhoodStructure neighbourhoodStructure;
	
	/**
	 * Mandatory abstract constructor for the child classes.
	 * 
	 * @param neighbourhoodStructure
	 */
	public NeighbourhoodableFunction(NeighbourhoodStructure neighbourhoodStructure) {
		this.neighbourhoodStructure = neighbourhoodStructure;
	}
	
	/**
	 * Given a group of parents, this method should return a group of sons, depending
	 * on the structure used.
	 * 
	 * @param parents the list of parent solutions
	 * @return a list of child solutions
	 */
	public List<double[]> getNeighbours(List<double[]> parents){
		return neighbourhoodStructure.getNeighbours(parents);
	}
	
	/**
	 * Use getNeighbours instead
	 */
	@Override
	@Deprecated
	public double[] perturb(double... x){
		List<double[]> parent = new ArrayList<double[]>();
		parent.add(x);
		return neighbourhoodStructure.getNeighbours(parent).get(0);
	}
	
	@Override
	@Deprecated
	public double getPerturbation() {
		return 0;
	}
	
	@Override
	@Deprecated
	public double perturb(double x){
		return -1;
	}

}
