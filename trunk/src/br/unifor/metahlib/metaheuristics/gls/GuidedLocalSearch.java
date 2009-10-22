package br.unifor.metahlib.metaheuristics.gls;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.base.Metaheuristic;

public class GuidedLocalSearch extends Metaheuristic {

	private Function function;
	private Metaheuristic localSearchMethod;
	private double lambda;
	private int maxIterations;
	
	public GuidedLocalSearch(Function function, Metaheuristic localSearchMethod, int maxIterations, double lambda) {
		this.function = function;
		this.localSearchMethod = localSearchMethod;
		this.lambda = lambda;
		this.maxIterations = maxIterations;
	}
	
	public double[] execute(){
		
		double[] s = function.getRandomSolution();
		
		for(int i = 0; i < maxIterations; i++){
			localSearchMethod.setInitialSolution(s);
			s = localSearchMethod.execute();
			
			//TODO to be continued...
		}
		
		return null;
	}
	
	@Override
	public Function getFunction() {
		return function;
	}

	@Override
	public void setFunction(Function function) {
		this.function = function;		
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

}
