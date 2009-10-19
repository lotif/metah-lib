package br.unifor.metahlib.functions.tsp;

public class TSPProblemDefinition {

	private double[][] distanceMatrix;
	
	public TSPProblemDefinition(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}
	
	public double getDistance(int i, int j){
		return distanceMatrix[i][j];
	}
	
}
