package br.unifor.metahlib.gls;

/**
 * This class stablishes the basic parameters to define a GLS solution feature.
 * 
 * @author marcelo lotif
 *
 */
public abstract class SolutionFeature {

	/**
	 * The cost of this feature
	 */
	private double cost;
	
	/**
	 * Contructor of the class
	 * 
	 * @param cost the cost of this feature
	 */
	public SolutionFeature(double cost){
		this.cost = cost;
	}
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
