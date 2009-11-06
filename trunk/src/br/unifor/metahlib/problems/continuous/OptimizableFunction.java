package br.unifor.metahlib.problems.continuous;

public abstract class OptimizableFunction {
	
	static public enum OptimizationType { MINIMIZATION, MAXIMIZATION };

	private int dimensionCount;
	protected double[][] ranges;
	protected int decimalPrecision = 6;
	protected Double optimalResult = null;

	protected OptimizationType optimizationType = OptimizationType.MINIMIZATION;
	
	public OptimizableFunction(int dimensionCount){
		ranges = new double[dimensionCount][2];
		this.dimensionCount = dimensionCount;
	}
	
	protected void setAllRanges(double min, double max){
		for (int i = 0; i < ranges.length; ++i){
			ranges[i][0] = min;
			ranges[i][1] = max;
		}
	}
	
	public double rangeValue(int dimension, double value){
		double[] range = ranges[dimension];
		double min = range[0];
		double max = range[1];
		return Math.max(min, Math.min(max, value));
	}
	
	public abstract double execute(Object[] values);
	
	public int getDimensionCount(){
		return dimensionCount;
	}
	
	public int getDecimalPrecision(){
		return decimalPrecision;
	}
	
	public double[] getDimensionRange(int dimension){
		return ranges[dimension];
	}
	
	public OptimizationType getOptimizationType() {
		return optimizationType;
	}
	
	public Double getOptimalResult() {
		return optimalResult;
	}
}
