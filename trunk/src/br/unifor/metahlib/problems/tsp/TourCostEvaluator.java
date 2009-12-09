package br.unifor.metahlib.problems.tsp;

import br.unifor.metahlib.base.CostEvaluator;
import br.unifor.metahlib.base.Solution;

/**
 * Cost evaluator for TSP problems.
 */
public class TourCostEvaluator extends CostEvaluator {
	
	/**
	 * TSP instance with cities distances.
	 */
	private TSPInstance instance;
	
	/**
	 * Class constructor.
	 * @param instance instance with cities distances
	 */
	public TourCostEvaluator(TSPInstance instance){
		this.instance = instance;
	}

	/**
	 * Calculate the sum of cities distances of a tour.
	 */
	@Override
	protected double calculateCost(Solution s) {
		Object[] cities = s.getValues();
		int d = 0;
		if (cities.length > 0){
			for (int i = 0; i < cities.length - 1; ++i){
				d+= instance.getDistance((Integer) cities[i], (Integer) cities[i+1]); 
			}
			d+= instance.getDistance((Integer) cities[cities.length - 1], (Integer) cities[0]);
		}
		
		return d;
	}
}
