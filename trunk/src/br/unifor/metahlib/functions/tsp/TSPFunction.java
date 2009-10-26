package br.unifor.metahlib.functions.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.NeighbourhoodStructure;
import br.unifor.metahlib.base.NeighbourhoodableFunction;

public class TSPFunction extends NeighbourhoodableFunction {

	private Random r = new Random();
	
	private TSPProblemDefinition tspProblem;
	
	public TSPFunction(TSPProblemDefinition tspProblem, NeighbourhoodStructure neighbourhoodStructure) {
		super(neighbourhoodStructure);
		this.tspProblem = tspProblem;
		numVariables = tspProblem.getNumberOfCities();
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
	public double getFeasibleValue() {
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
		for(int i = 0; i < x.length; i++){
			citiesLeft.add(i + 1);
		}
		
		for(int i = 0; i < x.length; i++){
			int index = r.nextInt(citiesLeft.size());
			x[i] = (double) citiesLeft.get(index);
			
			citiesLeft.remove(index);
		}
		
		return x;
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
