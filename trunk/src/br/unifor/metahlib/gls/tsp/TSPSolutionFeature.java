package br.unifor.metahlib.gls.tsp;

import br.unifor.metahlib.gls.SolutionFeature;

/**
 * Implementation of the SolutionFeature abstract class to define a TSP feature
 * A TSP solution feature for the GLS is defined as an edge of the solution.
 * 
 * @author marcelo lotif
 *
 */
public class TSPSolutionFeature extends SolutionFeature {

	/**
	 * The origin city of the edge
	 */
	private int i;
	
	/**
	 * The destination city of the edge
	 */
	private int j;
	
	/**
	 * The class contructor
	 * 
	 * @param i The origin city of the edge
	 * @param j The destination city of the edge
	 * @param cost the cost of this edge
	 */
	public TSPSolutionFeature(int i, int j, double cost) {
		super(cost);
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	
}