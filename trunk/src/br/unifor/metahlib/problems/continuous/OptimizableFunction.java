package br.unifor.metahlib.problems.continuous;

/**
 * Function capable of will be optimized.
 */
public abstract class OptimizableFunction {
	
	/**
	 * Indicates if the function must be maximized or minimized.
	 */
	static public enum OptimizationType { MINIMIZATION, MAXIMIZATION };

	/**
	 * Quantity of dimensions.
	 */
	private int dimensionCount;
	
	/**
	 * Minimal and maximal values of each dimension.
	 */
	protected double[][] ranges;
	
	/**
	 * Decimal precision required by function.
	 */
	protected int decimalPrecision = 6;
	
	/**
	 * Best result of the function. Null indicates that best result is unknown.
	 */
	protected Double optimalResult = null;

	/**
	 * Indicates if the function must be maximized or minimized.
	 */
	protected OptimizationType optimizationType = OptimizationType.MINIMIZATION;
	
	/**
	 * Class constructor.
	 * @param dimensionCount quantity of dimensions.
	 */
	public OptimizableFunction(int dimensionCount){
		ranges = new double[dimensionCount][2];
		this.dimensionCount = dimensionCount;
	}
	
	/**
	 * Configure the range of all dimensions.
	 * @param min minimal allowed dimension value
	 * @param max maximal allowed dimension value
	 */
	protected void setAllRanges(double min, double max){
		for (int i = 0; i < ranges.length; ++i){
			ranges[i][0] = min;
			ranges[i][1] = max;
		}
	}
	
	/**
	 * Limits the dimension value to the allowed range. 
	 * @param dimension dimension index
	 * @param value value to be ranged
	 * @return ranged value
	 */
	public double rangeValue(int dimension, double value){
		double[] range = ranges[dimension];
		double min = range[0];
		double max = range[1];
		return Math.max(min, Math.min(max, value));
	}
	
	/**
	 * Executes the function.
	 * @param values function arguments
	 * @return function result
	 */
	protected abstract double eval(Object[] values);
	
	public double execute(Object[] values){
		double result = eval(values);
		double pow = Math.pow(10, decimalPrecision);
		return Math.round(result * pow) / pow;
	}
	
	/**
	 * Returns the quantity of dimensions.
	 * @return quantity of dimensions
	 */
	public int getDimensionCount(){
		return dimensionCount;
	}
	
	/**
	 * Returns the decimal precision required by the function.
	 * @return decimal precision
	 */
	public int getDecimalPrecision(){
		return decimalPrecision;
	}
	
	/**
	 * Returns the minimal and maximal values allowed for the informed dimension.
	 * @param dimension dimension index
	 * @return array with two values, minimal e maximal allowed values
	 */
	public double[] getDimensionRange(int dimension){
		return ranges[dimension];
	}
	
	/**
	 * Returns a enumerated value that indicates if the function must be 
	 * maximized or minimized.
	 * @return optimization type
	 */
	public OptimizationType getOptimizationType() {
		return optimizationType;
	}
	
	/**
	 * Returns the best result of the function. Null indicates that best 
	 * result is unknown.
	 * @return best result of function
	 */
	public Double getOptimalResult() {
		return optimalResult;
	}
}
