package br.unifor.metahlib.base;

/**
 * A possible solution for a problem. 
 */
public class Solution implements Cloneable {
	
	/**
	 * Solution cost.  
	 */
	private Double cost;
	
	/**
	 * Problem which this solution is applicable. 
	 */
	private Problem problem;
	
	/**
	 * Values of solution. The meaning of the values is given by the problem.
	 */
	private Object[] values;
	
	/**
	 * Constructs a new Solution for the problem informed.
	 * @param p
	 */
	public Solution(Problem p){
		problem = p;
	}

	/**
	 * Returns the cost of solution.
	 * @return cost of solution
	 */
	public double getCost() {
		if (cost == null){
			cost = problem.getCostEvaluator().eval(this);
		}
		return cost;
	}
	
	/**
	 * Sets the solution values. The meaning of the values is given by the problem. 
	 * @param values solution values
	 */
	public void setValues(Object[] values){
		this.values = problem.rangeSolutionValues(values);
		this.cost = null;
	}
	
	/**
	 * Returns the solution values. The meaning of the values is given by the problem.
	 * @return solution values
	 */
	public Object[] getValues(){
		return values;
	}
	
    /**
     * Creates a solution clone.
     */
	@Override
    public Object clone() throws CloneNotSupportedException {
        Solution clone = duplicate();
        return clone;
    }
	
    /**
     * Creates a solution clone. Same that clone(), but without throws CloneNotSupportedException.
     */
	public Solution duplicate() {
        Solution clone = new Solution(problem);
        clone.cost = cost;
        clone.values = values.clone();
        return clone;
    }
	    
	/**
	 * Creates a string representation of solution.
	 */
	@Override
	public String toString(){
		double cost = getCost();
		Double optimal = problem.getOptimalSolutionCost();
		String s = "Cost: " + cost;
		if (optimal != null){
			s+= String.format(" OptimalCost: %f Error: %f)", optimal, optimal - cost);
		}
		s+= " [";		
		if (values.length > 0){
			s+= values[0];
			for (int i = 1; i < values.length; ++i){
				s+= ", " + values[i];
			}
		}
		s+= "]";
		return s;
	}
}
