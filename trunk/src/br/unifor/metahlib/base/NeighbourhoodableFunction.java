package br.unifor.metahlib.base;

import java.util.ArrayList;
import java.util.List;

public abstract class NeighbourhoodableFunction extends Function {

	protected NeighbourhoodStructure neighbourhoodStructure;
	
	public NeighbourhoodableFunction(NeighbourhoodStructure neighbourhoodStructure) {
		this.neighbourhoodStructure = neighbourhoodStructure;
	}
	
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
