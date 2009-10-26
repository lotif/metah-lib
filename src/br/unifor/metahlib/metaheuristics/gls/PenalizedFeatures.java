package br.unifor.metahlib.metaheuristics.gls;

public interface PenalizedFeatures {

	/*
	 *  Returns the cost of the feature to be penalized. If the solution doesn't have
	 *  the feature, returns 0.
	 */
	public double hasTheFeature(double[] solution, int featureIndex);
	public int getTotalFeatures();
	
}
