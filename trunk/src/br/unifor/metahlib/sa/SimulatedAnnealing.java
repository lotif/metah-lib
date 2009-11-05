package br.unifor.metahlib.sa;

import java.util.Random;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.base.Metaheuristic;

/**
 * The simulated annealing optimization method
 * 
 * @author marcelo lotif
 *
 */
public class SimulatedAnnealing extends Metaheuristic {
	
	/**
	 * Maximum number of iterations for each temperature reached
	 */
	private int maxIterations;
	/**
	 * The maximum temperature of the system
	 */
	private double maxTemperature;
	/**
	 * The minimum temperature of the system
	 */
	private double minTemperature;
	/**
	 * The temperature decreasing step
	 */
	private double decreaseStep;

	/**
	 * Constructor of the class
	 * 
	 * @param function the function to be optimized
	 * @param tmax the maximum temperature of the system
	 * @param tmin the minimum temperature of the system
	 * @param b the decreasing step
	 * @param k the maximum number of iterations for each temperature reached
	 */
	public SimulatedAnnealing(Function function, double tmax, double tmin, double b, int k){
		super(function);
		this.maxIterations = k;
		this.maxTemperature = tmax;
		this.minTemperature = tmin;
		this.decreaseStep = b;
	}

	/**
	 * Executes the simulated annealing optimization
	 * 
	 * @return the best solution found
	 */
	public double[] execute() {
		Random r = new Random();
		
		double[] x;
		if(initialSolution == null){
			x = function.getRandomSolution();
		} else {
			x = initialSolution;
		}
		
		double temperature = maxTemperature;
		
		int currentIteration = 0;
		double eval = eval(x);
		
		int totalIt = 1;
		while(temperature > minTemperature){
			for(int i = 0; i < maxIterations; i++){
				currentIteration++;
				
				double[] _x = function.perturb(x);
				double _eval = eval(_x);

				if(_eval < eval){
					x = _x;
					eval = _eval;
					lastBestFoundOn = totalIt;
				} else {
					double rand = r.nextDouble();
					double exp = Math.exp((eval - _eval)/temperature);
					if(rand < exp){
						x = _x;
						eval = _eval;
					}
				}
				totalIt++;
			}
			temperature *= decreaseStep;
		}
		
		return x;
	}
	
	private double eval(double[] x) {
		return function.eval(x);
	}
	
	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}
	
	public double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public double getDecreaseStep() {
		return decreaseStep;
	}

	public void setDecreaseStep(double decreaseStep) {
		this.decreaseStep = decreaseStep;
	}
	
}
