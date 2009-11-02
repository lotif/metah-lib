package br.unifor.metahlib.metaheuristics.gls;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.tsp.TSPFunction;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPAugmentedCostFunction;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPPenalizedFeatures;

public class AugmentedCostFunctionFactory {

	public static AugmentedCostFunction getInstance(Function function, double lambda, double[] p, PenalizedFeatures penalizedFeatures){
		if(function instanceof TSPFunction){
			return new TSPAugmentedCostFunction((TSPFunction)function, lambda, p, (TSPPenalizedFeatures) penalizedFeatures);
		} else {
			return null;
		}
	}
	
}
