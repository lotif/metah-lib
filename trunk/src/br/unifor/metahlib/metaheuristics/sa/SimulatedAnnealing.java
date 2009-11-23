package br.unifor.metahlib.metaheuristics.sa;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.base.TrajectoryHeuristic;


/**
 * The simulated annealing optimization method
 * 
 * @author marcelo lotif
 *
 */
public class SimulatedAnnealing extends TrajectoryHeuristic {
	
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
	 * The initial solution for the SimulatedAnnealing
	 */
	private Solution initialSolution;
	
	/**
	 * Constructor of the class
	 * @param function the function to be optimized
	 * @param tmax the maximum temperature of the system
	 * @param tmin the minimum temperature of the system
	 * @param b the decreasing step
	 * @param k the maximum number of iterations for each temperature reached
	 */
	public SimulatedAnnealing(Problem problem, NeighborhoodStructure neighborhoodStructure,
			double tmax, double tmin, double b, int k){
		super(problem, neighborhoodStructure);
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
	@Override
	public Solution execute() {
		double temperature = maxTemperature;
		int currentIteration = 0;

		Solution s = initialSolution == null ? problem.getInitialSolution() : initialSolution;
		
		int totalIt = 1;
		while(temperature > minTemperature && totalIt < max_it) {
			for(int i = 0; i < maxIterations; i++){
				if(totalIt > max_it){
					break;
				}				
				currentIteration++;
				
				System.out.print("i: " + totalIt + " ");
				Solution _s = neighborhoodStructure.getRandomNeighbor(s);
				if(_s.getCost() < s.getCost()){
					s = _s;
					lastBestFoundOn = totalIt;
					System.out.print("improved to: " + s);
					
				} else {
					double rand = problem.getRandom().nextDouble();
					double exp = Math.exp((s.getCost() - _s.getCost())/temperature);
					if(rand < exp){
						s = _s;
						System.out.print("worsened to: " + s);
					}
				}
				
				System.out.println();
				totalIt++;
			}
			
			temperature *= decreaseStep;
		}
		
		return s;
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

	public double getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(double minTemperature) {
		this.minTemperature = minTemperature;
	}

	public Solution getInitialSolution() {
		return initialSolution;
	}

	public void setInitialSolution(Solution initialSolution) {
		this.initialSolution = initialSolution;
	}
	
}
