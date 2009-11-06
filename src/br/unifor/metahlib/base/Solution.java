package br.unifor.metahlib.base;

public class Solution implements Cloneable {
	private Double cost;
	private Problem problem;
	private Object[] values;
	
	public Solution(Problem p){
		problem = p;
	}

	public double getCost() {
		if (cost == null){
			cost = problem.getCostEvaluator().eval(this);
		}
		return cost;
	}
	
	public void setValues(Object[] values){
		this.values = values;
		this.cost = null;
	}
	
	public Object[] getValues(){
		return values;
	}
	
    @Override
    public Object clone() throws CloneNotSupportedException {
        Solution clone = new Solution(problem);
        clone.cost = cost;
        clone.values = values.clone();
        return clone;
    }
    
	public String toString(){
		String s = "Cost: " + getCost() + " [";
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
