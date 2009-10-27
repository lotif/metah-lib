package br.unifor.metahlib.metaheuristics.gls;

import br.unifor.metahlib.base.Function;

public class AugmentedCostFunction extends Function {

	private Function function;
	private double lambda;
	private double[] p;
	private PenalizedFeatures penalizedFeatures;
	
	public AugmentedCostFunction(Function function, double lambda, double[] p,
			PenalizedFeatures penalizedFeatures) {
		super();
		this.function = function;
		this.lambda = lambda;
		this.p = p;
		this.penalizedFeatures = penalizedFeatures;
	}

	@Override
	protected double evalImpl(double... x) {
		double eval = function.eval(x);
		
		for(int i = 0; i < penalizedFeatures.getTotalFeatures(); i++){
			if(penalizedFeatures.hasTheFeature(x, i) != 0){
				eval = eval + lambda*p[i];
			}
		}
		
		return eval;
	}

	@Override
	public double getFeasibleValue() {
		return function.getFeasibleValue();
	}

	@Override
	public double getOptimalEval() {
		return function.getOptimalEval();
	}

	@Override
	public double getPerturbation() {
		return function.getPerturbation();
	}

	@Override
	public double maxValue() {
		return function.maxValue();
	}

	@Override
	public double minValue() {
		return function.minValue();
	}
	
	public boolean isOptimal(double eval){
		return function.isOptimal(eval);
	}
	public boolean isQuasiOptimal(double eval){
		return function.isQuasiOptimal(eval);
	}
	
	public double[] perturb(double... x){
		return function.perturb(x);
	}
	
	public double perturb(double x){
		return function.perturb(x);
	}
	
	public double[] getRandomSolution(){
		return function.getRandomSolution();
	}
	
}
