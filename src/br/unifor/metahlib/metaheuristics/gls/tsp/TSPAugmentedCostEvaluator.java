package br.unifor.metahlib.metaheuristics.gls.tsp;

import java.io.IOException;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.gls.AugmentedCostEvaluator;
import br.unifor.metahlib.metaheuristics.gls.SolutionFeature;
import br.unifor.metahlib.problems.tsp.EdgeWeightTypeNotSupported;
import br.unifor.metahlib.problems.tsp.TSPProblem;

public class TSPAugmentedCostEvaluator extends AugmentedCostEvaluator {

	public TSPAugmentedCostEvaluator(TSPProblem problem, double lambda, int numberOfVariables) throws IOException, EdgeWeightTypeNotSupported{
		super(problem, lambda);
//		problem.setDataSet(new TSPDataSet(problem.getDataSet().getFile()));
		this.penalties = new double[numberOfVariables][numberOfVariables];
	}
	
	@Override
	protected double calculateAugmentedCost(Solution s) {
		return problem.getCostEvaluator().eval(s);
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
		return penalties[tspFeature.getI() - 1][tspFeature.getJ() - 1];
	}
	
	/**
	 * Updates the penalty for a given feature
	 * 
	 * @param penalizedFeature the feature to be penalized
	 */
	@Override
	public void updateP(SolutionFeature penalizedFeature) {
		TSPSolutionFeature tspFeature = (TSPSolutionFeature) penalizedFeature;
		penalties[tspFeature.getI() - 1][tspFeature.getJ() - 1]++;
		
		TSPProblem p = (TSPProblem) problem;
		int[][] distanceMatrix = p.getDataSet().getDistances();
		
		distanceMatrix[tspFeature.getI() - 1][tspFeature.getJ() - 1] = 
			(int)Math.round(
			distanceMatrix[tspFeature.getI() - 1][tspFeature.getJ() - 1] + 
			(lambda*penalties[tspFeature.getI() - 1][tspFeature.getJ() - 1]));
	}

}
