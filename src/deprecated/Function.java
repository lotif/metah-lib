package deprecated;

import java.text.DecimalFormat;

/**
 * Abstract class which groups common characteristics of continuous and discrete functions.
 * 
 * TODO: This class was first designed to continuous mathematic functions, so it needs some improvements
 * to work better with discrete functions, like the TSP.
 * 
 * @author marcelo lotif
 *
 */
public abstract class Function {
	
	/**
	 * A counter to control the number of executed evaluations
	 */
	private int count = 0;
	
	/**
	 * The number of variables (dimensionality) of the function
	 */
	protected int numVariables;
	
	/**
	 * The evaluation function
	 * 
	 * @param x the solution to be evaluated
	 * @return the evaluation result
	 */
	public double eval(double... x){
		count++;		
		return evalImpl(x);
	}
	
	/**
	 * The abstract evaluation function, to be implemented by
	 * the child class.
	 * 
	 * @param x the solution to be evaluated
	 * @return the evaluation result
	 */
	protected abstract double evalImpl(double... x);
	
	/**
	 * Returns the optimal evaluation (if known) of the function.
	 * 
	 * @return the optimal evaluation for this function
	 */
	public abstract double getOptimalEval();
	
	/**
	 * Check if a given evaluation is the best for this function
	 * 
	 * @param eval the evaluation to be tested
	 * @return true if it is the best evaluation, false otherwise
	 */
	public boolean isOptimal(double eval){
		return eval == getOptimalEval();
	}
	
	/**
	 * Check if a given evaluation is near the best evaluation for this function.
	 * There is a fixed precision of 4 digits. For a better precision, you must
	 * override this method in your implementation. 
	 * 
	 * @param eval the evaluation to be testes
	 * @return true if it is near the best evaluation, false otherwise
	 */
	public boolean isQuasiOptimal(double eval){
		DecimalFormat d = new DecimalFormat("#######.####");
		return d.format(eval).equals(d.format(getOptimalEval()));
	}
	
	/**
	 * This method perturbs a solution and brings it to a slightly near place of
	 * the search space.
	 * 
	 * @param x the solution to be perturbed
	 * @return the new solution
	 */
	public double[] perturb(double... x){
		double[] var = x.clone();
		for(int i = 0; i < var.length; i++){
			var[i] = perturb(var[i]);
		}
		return var;
	}
	
	/**
	 * Drags a single component of the solution to a slightly near place
	 * of the search space.
	 * If the perturbation generates a value out of the function bounds,
	 * this method brings the value to the limits of the function (minimum or maximum value).
	 * 
	 * @param x the value to be perturbed
	 * @return the new value
	 */
	public double perturb(double x){
		double var = x + getPerturbation();
		if(var < minValue()){
			var = minValue();
		}
		if(var > maxValue()){
			var = maxValue();
		}
		
		return var;
	}
	
	/**
	 * This abstract method defines the amount of perturbation that each
	 * component of a solution should suffer.
	 * 
	 * @return the perturbation for a component of the solution
	 */
	public abstract double getPerturbation();
	
	/**
	 * @return The maximum value that a component of the solution must assume
	 */
	public abstract double minValue();
	
	/**
	 * @return The minimum value that a component of the solution must assume
	 */
	public abstract double maxValue();
	
	/**
	 * Generates a random feasible solution of the function
	 * 
	 * @return a random feasible solution
	 */
	public double[] getRandomSolution(){
		double[] x = new double[getNumVariables()];
		
		for(int i = 0; i < x.length; i++){
			x[i] = getFeasibleValue();
		}
		return x;
	}
	
	/**
	 * @return a random feasible value for the function.
	 */
	public abstract double getFeasibleValue();

	public int getCount() {
		return count;
	}
	
	public void resetCount(){
		count = 0;
	}

	public int getNumVariables() {
		return numVariables;
	}

	public void setNumVariables(int numVariables) {
		this.numVariables = numVariables;
	}
	
}
