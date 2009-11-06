package br.unifor.metahlib.base;

public abstract class CostEvaluator {
	
	private int evalCount = 0;
	
	public double eval(Solution s){
		evalCount++;
		return calculateCost(s);
	}
	
	protected abstract double calculateCost(Solution s);

	public int getEvalCount() {
		return evalCount;
	}
}
