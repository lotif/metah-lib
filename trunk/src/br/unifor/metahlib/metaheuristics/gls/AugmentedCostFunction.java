package br.unifor.metahlib.metaheuristics.gls;

import java.util.List;

import deprecated.Function;


/**
 * The abstract class for the Augmented Cost Function of GLS metaheuristic.
 * Each problem must have an specific implementation of this class.
 * 
 * This class also treats with the penalties of the GLS Metaheuristic.
 * 
 * @author marcelo lotif
 *
 */
public abstract class AugmentedCostFunction extends Function {

	/**
	 * The function that this instance is augmenting
	 */
	protected Function function;
	
	/**
	 * The lambda parameter to calculate the feature's penalties
	 */
	protected double lambda;
	
	/**
	 * Class contructor
	 * 
	 * @param function The function that this instance is augmenting
	 * @param lambda The lambda parameter to calculate the feature's penalties
	 */
	public AugmentedCostFunction(Function function, double lambda) {
		super();
		this.function = function;
		this.lambda = lambda;
	}

	/**
	 * The implementation of the evaluation function.
	 * Adds the penalties to the original cost function.
	 * 
	 * @param x the solution instance
	 * @return the value of the augmented cost function
	 */
	@Override
	protected double evalImpl(double... x) {
		return augmentedEval(x);
	}

	/**
	 * Calculates the augmented cost function.
	 * 
	 * @param x the solution instance
	 * @return the augmented cost value
	 */
	protected abstract double augmentedEval(double... x);
	
	/**
	 * Given a problem solution, returns all the features belonging to this solution
	 * 
	 * @param solution a problem solution
	 * @param function the function to calculate the cost of the features
	 * @return a list of features belonging to this solution
	 */
	public abstract List<SolutionFeature> getSolutionFeatures(double[] solution, Function function);
	
	/**
	 * Returns the penalty value for a given feature
	 * 
	 * @param feature the feature
	 * @return the penalty for this feature
	 */
	public abstract double getP(SolutionFeature feature);
	
	/**
	 * Updates the penalty for a given feature
	 * 
	 * @param penalizedFeature the feature to be penalized
	 */
	public abstract void updateP(SolutionFeature penalizedFeature);
	
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
