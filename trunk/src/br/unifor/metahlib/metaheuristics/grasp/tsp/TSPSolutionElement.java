package br.unifor.metahlib.metaheuristics.grasp.tsp;

import br.unifor.metahlib.metaheuristics.grasp.SolutionElement;

public class TSPSolutionElement extends SolutionElement {

	private int i;
	private int j;
	
	public TSPSolutionElement(double cost, int i, int j) {
		super(cost, j);
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
