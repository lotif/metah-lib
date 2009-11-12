package br.unifor.metahlib.problems.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.Solution;

/**
 * Class that generates a new TSP solution by a greedy constructive mehtod
 * 
 * @author marcelolotif
 *
 */
public class GreedyConstructiveSolutionGenerator {

	/**
	 * The TSP problem instance
	 */
	private TSPProblem problem;
	
	/**
	 * Constructor of the class
	 * 
	 * @param problem The TSP problem instance
	 */
	public GreedyConstructiveSolutionGenerator(TSPProblem problem){
		this.problem = problem;
	}
	
	/**
	 * Generates a new solution for a TSP problem instance by a greedy constructive mehtod
	 * 
	 * @return a new valid solution
	 */
	public Solution generateNewGreedySolution(){
		return generateNewGreedySolution(-1);
	}
	
	/**
	 * Generates a new solution for a TSP problem instance by a greedy constructive mehtod 
	 * based in a given starting point (city)
	 * 
	 * @param startingPoint the starting point of the new solution
	 * @return a new valid solution
	 */
	public Solution generateNewGreedySolution(int startingPoint){
		if(startingPoint == -1){
			startingPoint = new Random().nextInt(problem.getDimension()) + 1;
		}
		
		Solution s = new Solution(problem);
		Object[] values = new Object[problem.getDimension()];
		
		List<Integer> cities = new ArrayList<Integer>();
		for(int i = 0; i < problem.getDimension(); i++){
			if((i + 1) != startingPoint){
				cities.add(i + 1);
			}
		}
		
		values[0] = startingPoint;
		for(int i = 1; i < values.length; i++){
			double minCost = -1;
			int index = -1;
			
			for(int j = 0; j < cities.size(); j++){
				double distance = problem.getDataSet().getDistance(startingPoint, cities.get(j));
				
				if(distance < minCost || minCost == -1){
					minCost = distance;
					index = j;
				}
			}
			
			values[i] = cities.get(index);
			startingPoint = cities.get(index);
			
			cities.remove(index);
		}
		
		s.setValues(values);
		return s;
	}
	
}
