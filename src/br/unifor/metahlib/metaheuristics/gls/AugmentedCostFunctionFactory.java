package br.unifor.metahlib.metaheuristics.gls;

import java.io.IOException;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPAugmentedCostEvaluator;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPAugmentedCostProblem;
import br.unifor.metahlib.problems.tsp.EdgeWeightTypeNotSupported;
import br.unifor.metahlib.problems.tsp.TSPProblem;

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
	 * @throws EdgeWeightTypeNotSupported 
	 * @throws IOException 
	 */
	public static AugmentedCostProblem getInstance(Problem problem, double lambda) throws IOException, EdgeWeightTypeNotSupported{
		if(problem instanceof TSPProblem){
			TSPProblem p = new TSPProblem(((TSPProblem) problem).getDataSet().getFile());
			TSPAugmentedCostEvaluator evaluator = new TSPAugmentedCostEvaluator(p, lambda, problem.getInitialSolution().getValues().length);
			TSPAugmentedCostProblem t = new TSPAugmentedCostProblem(p, evaluator);
			return t;
		} else {
			return null;
		}
	}
	
}
