package br.unifor.metahlib.metaheuristics.grasp;

public abstract class SolutionElement {

	private double cost;
	private Object value;
	
	public SolutionElement(double cost, Object value){
		this.cost = cost;
		this.value = value;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}	
	
}
