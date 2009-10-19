package br.unifor.metahlib.functions.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.base.NeighbourhoodStructure;

public class TSPFunction extends Function {

	private Random r = new Random();
	
	private TSPProblemDefinition tspProblem;
	
	private NeighbourhoodStructure neighbourhoodStructure;
	
	public TSPFunction(double[][] distanceMatrix, NeighbourhoodStructure neighbourhoodStructure) {
		tspProblem = new TSPProblemDefinition(distanceMatrix);
		numVariables = distanceMatrix.length;
		this.neighbourhoodStructure = neighbourhoodStructure;
	}
	
	@Override
	protected double evalImpl(double... x) {
		
		double totalCost = 0;
		
		for(int i = 0; i < x.length; i++){
			if(i < x.length - 1){
				totalCost += tspProblem.getDistance((int)x[i], (int)x[i + 1]);
			} else {
				totalCost += tspProblem.getDistance((int)x[i], (int)x[0]);
			}
		}
		
		return totalCost;
	}

	@Override
	protected double getFeasibleValue() {
		return r.nextInt(numVariables);
	}

	@Override
	public double getOptimalEval() {
		return -1;
	}

	@Override
	public double[] getRandomSolution(){
		double[] x = new double[getNumVariables()];
		
		List<Integer> citiesLeft = new ArrayList<Integer>();
		for(int i = 0; i < citiesLeft.size(); i++){
			citiesLeft.set(i, i);
		}
		
		for(int i = 0; i < x.length; i++){
			int index = r.nextInt(citiesLeft.size());
			x[i] = (double) citiesLeft.get(index);
			
			citiesLeft.remove(index);
		}
		
		return x;
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
	protected double getPerturbation() {
		return 0;
	}
	
	@Override
	@Deprecated
	protected double perturb(double x){
		return -1;
	}
	
	@Override
	public double maxValue() {
		return numVariables;
	}

	@Override
	public double minValue() {
		return 0;
	}

}
