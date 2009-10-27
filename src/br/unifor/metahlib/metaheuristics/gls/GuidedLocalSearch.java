package br.unifor.metahlib.metaheuristics.gls;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.base.Metaheuristic;

public class GuidedLocalSearch extends Metaheuristic {

	private Function function;
	private Metaheuristic localSearchMethod;
	private double lambda;
	private int maxIterations;
	
	private PenalizedFeatures penalizedFeatures;
	private double[] p;
	
	public GuidedLocalSearch(Function function, Metaheuristic localSearchMethod, int maxIterations, double lambda, PenalizedFeatures penalizedFeatures) {
		this.function = function;
		this.localSearchMethod = localSearchMethod;
		this.lambda = lambda;
		this.maxIterations = maxIterations;
		this.penalizedFeatures = penalizedFeatures;
		p = new double[penalizedFeatures.getTotalFeatures()];
	}
	
	public double[] execute(){
		
		double[] s = function.getRandomSolution();
		
		double[] best = null;
		Double bestEval = null;
		
		for(int i = 0; i < maxIterations; i++){
			AugmentedCostFunction f_ = new AugmentedCostFunction(function, lambda, p, penalizedFeatures); 
			
			localSearchMethod.setFunction(f_);
			localSearchMethod.setInitialSolution(s);
			s = localSearchMethod.execute();
			
			double[] util = new double[p.length];
			double maxUtil = -1;
			int maxUtilIndex = -1;
			
			for(int k = 0; k < util.length; k++){
				double c = penalizedFeatures.hasTheFeature(s, k);
				if(c != 0){
					util[k] = c/(1 + p[k]);
					if(util[k] > maxUtil){
						maxUtil = util[k];
						maxUtilIndex = k;
					}
				}
			}
			
			if(maxUtilIndex >= 0){
				p[maxUtilIndex]++;
			}
			
			double currentEval = function.eval(s);
			System.out.println("*" + i + ": " + currentEval);
			
			if(bestEval == null || currentEval < bestEval){
				bestEval = currentEval;
				best = s.clone();
			}
		}
		
		return best;
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
