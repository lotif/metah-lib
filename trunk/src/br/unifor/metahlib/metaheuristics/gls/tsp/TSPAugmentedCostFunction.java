package br.unifor.metahlib.metaheuristics.gls.tsp;

import br.unifor.metahlib.functions.tsp.TSPFunction;
import br.unifor.metahlib.functions.tsp.TSPProblemDefinition;
import br.unifor.metahlib.metaheuristics.gls.AugmentedCostFunction;

public class TSPAugmentedCostFunction extends AugmentedCostFunction {

	public TSPAugmentedCostFunction(TSPFunction function, double lambda,
			double[] p, TSPPenalizedFeatures penalizedFeatures) {
		super(function, lambda, p, penalizedFeatures);
		
		this.function = new TSPFunction(((TSPProblemDefinition)function.getTspProblem().clone()), function.getNeighbourhoodStructure());
		
	}

	@Override
	protected double augmentedEval(double... x) {
		return function.eval(x);
	}

	@Override
	public void updatePVector(int indexToUpdate){
		p[indexToUpdate]++;
		
		double[][] distanceMatrix = ((TSPFunction)function).getTspProblem().getDistanceMatrix();		
		
		TSPFeature tspFeature = ((TSPPenalizedFeatures)penalizedFeatures).getPenalizedFeatures().get(indexToUpdate);
		int i = tspFeature.getI();
		int j = tspFeature.getJ();
		distanceMatrix[i][j] += lambda*p[indexToUpdate];
		
		((TSPFunction)function).getTspProblem().setDistanceMatrix(distanceMatrix);
	}
	
}
