package br.unifor.metahlib.metaheuristics.gls.tsp;

import java.util.ArrayList;
import java.util.List;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.tsp.TSPFunction;
import br.unifor.metahlib.functions.tsp.TSPProblemDefinition;
import br.unifor.metahlib.metaheuristics.gls.AugmentedCostFunction;
import br.unifor.metahlib.metaheuristics.gls.SolutionFeature;

/**
 * An implementation of the AugmentedCostFunction for the TSPFunction class
 * 
 * @author marcelo lotif
 *
 */
public class TSPAugmentedCostFunction extends AugmentedCostFunction {

	/**
	 * A matrix of penalties
	 */
	private double[][] penalties;
	
	/**
	 * The contructor of the class
	 * 
	 * @param function the TSPFunction instance to be augmented
	 * @param lambda the GLS lambda parameter to calculate the penalties
	 */
	public TSPAugmentedCostFunction(TSPFunction function, double lambda) {
		super(function, lambda);
		
		this.function = new TSPFunction(((TSPProblemDefinition)function.getTspProblem().clone()), function.getNeighbourhoodStructure());
		this.penalties = new double[function.getNumVariables()][function.getNumVariables()];
	}
	
	/**
	 * Calculates the augmented cost function.
	 * 
	 * @param x the solution instance
	 * @return the augmented cost value
	 */
	@Override
	protected double augmentedEval(double... x) {
		return function.eval(x);
	}

	/**
	 * Given a problem solution, returns all the features belonging to this solution
	 * 
	 * @param solution a problem solution
	 * @param function the function to calculate the cost of the features
	 * @return a list of features belonging to this solution
	 */
	@Override
	public List<SolutionFeature> getSolutionFeatures(double[] solution, Function function) {
		List<SolutionFeature> solutionFeatures = new ArrayList<SolutionFeature>();
		
		TSPFunction f = (TSPFunction) function;
		
		for(int i = 0; i < solution.length; i++){			
			if(i != solution.length - 1){
				double cost = f.getTspProblem().getDistance((int)solution[i], (int)solution[i + 1]);
				solutionFeatures.add(new TSPSolutionFeature((int)solution[i], (int)solution[i + 1], cost));
			} else {
				double cost = f.getTspProblem().getDistance((int)solution[i], (int)solution[1]);
				solutionFeatures.add(new TSPSolutionFeature((int)solution[i], (int)solution[1], cost));
			}
		}
		
		return solutionFeatures;
	}
	
	/**
	 * Returns the penalty value for a given feature
	 * 
	 * @param feature the feature
	 * @return the penalty for this feature
	 */
	@Override
	public double getP(SolutionFeature feature) {
		TSPSolutionFeature tspFeature = (TSPSolutionFeature) feature;
		return penalties[tspFeature.getI()][tspFeature.getJ()];
	}
	
	/**
	 * Updates the penalty for a given feature
	 * 
	 * @param penalizedFeature the feature to be penalized
	 */
	@Override
	public void updateP(SolutionFeature penalizedFeature) {
		TSPSolutionFeature tspFeature = (TSPSolutionFeature) penalizedFeature;
		penalties[tspFeature.getI()][tspFeature.getJ()]++;
		
		TSPFunction f = (TSPFunction) function;
		double[][] distanceMatrix = f.getTspProblem().getDistanceMatrix();
		
		distanceMatrix[tspFeature.getI()][tspFeature.getJ()] = 
			distanceMatrix[tspFeature.getI()][tspFeature.getJ()] + 
			(lambda*penalties[tspFeature.getI()][tspFeature.getJ()]);
	}
	
}
