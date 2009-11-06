package br.unifor.metahlib.base;

/**
 * Class used to calculate the cost of the solutions. Lower-cost solutions are always 
 * best solutions. Therefore, the calculated cost for maximization problems must be inverted.
 */
public abstract class CostEvaluator {
	
	/**
	 * Count of calculated solutions.
	 */
	private int evalCount = 0;
	
	/**
	 * Calculates the cost of a solution.
	 * @param s solution to evaluate
	 * @return cost of a solution
	 */
	public double eval(Solution s) {
		evalCount++;
		return calculateCost(s);
	}

	/**
	 * Method responsible for calculate the cost of a solution. Must be implemented 
	 * in a descending class.
	 * @param s solution to evaluate
	 * @return cost of solution
	 */
	protected abstract double calculateCost(Solution s);

	/**
	 * Count of calculated solutions.
	 */
	public int getEvalCount() {
		return evalCount;
	}
}
