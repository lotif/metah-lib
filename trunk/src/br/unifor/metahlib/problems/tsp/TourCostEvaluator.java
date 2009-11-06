package br.unifor.metahlib.problems.tsp;

import br.unifor.metahlib.base.CostEvaluator;
import br.unifor.metahlib.base.Solution;

/**
 * Cost evaluator of TSP problems.
 */
public class TourCostEvaluator extends CostEvaluator {
	
	/**
	 * TSP dataSet with cities distances.
	 */
	private TSPDataSet dataSet;
	
	/**
	 * Class constructor.
	 * @param dataSet dataset with cities distances
	 */
	public TourCostEvaluator(TSPDataSet dataSet){
		this.dataSet = dataSet;
	}

	/**
	 * Calculate the sum of cities distances of a tour.
	 */
	@Override
	protected double calculateCost(Solution s) {
		Object[] cities = s.getValues();
		int d = 0;
		for (int i = 0; i < cities.length - 1; ++i){
			d+= dataSet.getDistance((Integer) cities[i], (Integer) cities[i+1]); 
		}
		d+= dataSet.getDistance((Integer) cities[cities.length - 1], (Integer) cities[0]);
		
		return d;
	}
}
