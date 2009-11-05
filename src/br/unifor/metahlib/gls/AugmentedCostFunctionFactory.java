package br.unifor.metahlib.gls;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.tsp.TSPFunction;
import br.unifor.metahlib.gls.tsp.TSPAugmentedCostFunction;

/**
 * A factory class for instances of the AugmentedCostFunction abstract class 
 * 
 * @author marcelo lotif
 *
 */
public class AugmentedCostFunctionFactory {

	/**
	 * Returns an instance of the AugmentedCostFunction class based on the given function class
	 * 
	 * @param function the function class
	 * @param lambda the lambda of the GLS instance
	 * @return an instance of the AugmentedCostFunction class based on the given function
	 */
	public static AugmentedCostFunction getInstance(Function function, double lambda){
		if(function instanceof TSPFunction){
			return new TSPAugmentedCostFunction((TSPFunction)function, lambda);
		} else {
			return null;
		}
	}
	
}
