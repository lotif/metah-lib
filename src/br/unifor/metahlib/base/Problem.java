package br.unifor.metahlib.base;

import java.util.Random;

public abstract class Problem {
	
	private Solution optimalSolution;
	private Solution initialSolution;
	private CostEvaluator costEvaluator;
	private NeighborhoodStructure neighborhoodStructure;
	protected Random random;

	public Problem(){
		this.random = new Random();
	}

	public void setOptimalSolution(Solution optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

	public Solution getOptimalSolution() {
		return optimalSolution;
	}

	public CostEvaluator getCostEvaluator() {
		return costEvaluator;
	}
	
	public void setCostEvaluator(CostEvaluator costEvaluator) {
		this.costEvaluator = costEvaluator;
	}

	public NeighborhoodStructure getNeighborhoodStructure() {
		return neighborhoodStructure;
	}
	
	public void setNeighborhoodStructure(NeighborhoodStructure neighborhoodStructure) {
		this.neighborhoodStructure = neighborhoodStructure;
	}

	public Random getRandom() {
		return random;
	}
	
	public void setRandomSeed(long seed){
		random.setSeed(seed);
	}
	
	public abstract Solution newRandomSolution();

	public void setInitialSolution(Solution initialSolution) {
		this.initialSolution = initialSolution;
	}

	public Solution getInitialSolution() {
		if (initialSolution == null){
			initialSolution = newRandomSolution();
		}
		return initialSolution;
	}
}
