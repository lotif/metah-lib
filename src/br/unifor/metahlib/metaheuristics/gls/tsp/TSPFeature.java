package br.unifor.metahlib.metaheuristics.gls.tsp;

public class TSPFeature {

	private int i;
	private int j;
	private double cost;
	
	public TSPFeature(int i, int j, double cost) {
		super();
		this.i = i;
		this.j = j;
		this.cost = cost;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
}
