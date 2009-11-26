package br.unifor.metahlib.metaheuristics.vns;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * This is an implementation of the Variable Neighborhood Search (VNS) metaheuristic
 * as described in Mladenovic, N. & Hansen, P. (1997). 
 * 
 * @author marcelolotif
 *
 */
public class VariableNeighborhoodSearch extends Heuristic {

	/**
	 * The array of Neighborhood Structures
	 */
	private NeighborhoodStructure[] neighborhoods;
	
	/**
	 * The initial solution for the metahuristic
	 */
	private Solution initialSolution;
	
	/**
	 * The local search method for the metahuristic
	 */
	private Heuristic localSearchMethod;
	
	/**
	 * The maximum number of iterations allowed to execute without improvement
	 */
	private int maxItWithoutImprovement;
	
	/**
	 * Contructor of the class
	 * 
	 * @param problem the problem which VNS will try to solve
	 * @param localSearchMethod The local search method for the metahuristic 
	 * @param maxItWithoutImprovement The maximum number of iterations allowed to execute 
	 * @param maxIt The maximum number of iterations allowed to execute without improvement
	 * @param neighborhoods The array of Neighborhood Structures
	 */
	public VariableNeighborhoodSearch(Problem problem, Heuristic localSearchMethod, int maxItWithoutImprovement, int maxIt, NeighborhoodStructure... neighborhoods) {
		super(problem);
		this.neighborhoods = neighborhoods;
		this.localSearchMethod = localSearchMethod;
		this.max_it = maxIt;
		this.maxItWithoutImprovement = maxItWithoutImprovement;
	}

	@Override
	public Solution execute() {
		Solution s = initialSolution == null ? problem.getInitialSolution() : initialSolution;
		System.out.println("Initial s: " + problem.getCostEvaluator().eval(s));
		
		int i = 0;
		int totalIt = 0;
		Solution s_;
		
		while(i < maxItWithoutImprovement && totalIt < max_it) {
			System.out.println("#### i = " + i + "####");
			double lastSCost = s.getCost();
			int k = 0;
			do {
				if(totalIt > max_it){
					break;
				}
				s_ = neighborhoods[k].getRandomNeighbor(s); //pick at random
				localSearchMethod.setProblem(problem);
				localSearchMethod.getProblem().setInitialSolution(s_);
				s_ = localSearchMethod.execute();

				System.out.println("s_, n" + k + ": " + s_.getCost());

				if(s_.getCost() < s.getCost()){
					s = s_;
					k = 0;
				} else {
					k++;
				}
				totalIt++;
				
			} while (endIteration(s_) && k < neighborhoods.length);
			
			if(lastSCost > s.getCost()){
				i = 0;
			} else {
				i++;
			}
		}
		
		System.out.println("Final s: " + s.getCost());
		
		return s;
	}

	/**
	 * Returns the array of neighborhood structures
	 * 
	 * @return the array of neighborhood structures
	 */
	public NeighborhoodStructure[] getNeighborhoods() {
		return neighborhoods;
	}

	/**
	 * Sets the array of neighborhood structures for the VNS
	 * 
	 * @param neighborhoods the new array of neighborhood structures for the VNS
	 */
	public void setNeighborhoods(NeighborhoodStructure[] neighborhoods) {
		this.neighborhoods = neighborhoods;
	}

	/**
	 * Returns the local search method for this VNS instance
	 * 
	 * @return the local search method for this VNS instance
	 */
	public Heuristic getLocalSearchMethod() {
		return localSearchMethod;
	}

	/**
	 * Sets the local search method for this VNS instance
	 * 
	 * @param localSearchMethod the new local search method for this VNS instance
	 */
	public void setLocalSearchMethod(Heuristic localSearchMethod) {
		this.localSearchMethod = localSearchMethod;
	}

	/**
	 * Returns the initial solution for this VNS instance
	 * 
	 * @return the initial solution for this VNS instance
	 */
	public Solution getInitialSolution() {
		return initialSolution;
	}

	/**
	 * Sets the initial solution for this VNS instance
	 * 
	 * @param initialSolution the new initial solution for this VNS instance
	 */
	public void setInitialSolution(Solution initialSolution) {
		this.initialSolution = initialSolution;
	}

	public int getMaxItWithoutImprovement() {
		return maxItWithoutImprovement;
	}

	public void setMaxItWithoutImprovement(int maxItWithoutImprovement) {
		this.maxItWithoutImprovement = maxItWithoutImprovement;
	}

}
