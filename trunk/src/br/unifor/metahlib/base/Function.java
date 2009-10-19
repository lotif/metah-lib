package br.unifor.metahlib.base;

import java.text.DecimalFormat;

public abstract class Function {
	
	private int count = 0;
	protected int numVariables;
	
	public double eval(double... x){
		count++;		
		return evalImpl(x);
	}
	
	protected abstract double evalImpl(double... x);
	
	public abstract double getOptimalEval();
	
	public boolean isOptimal(double eval){
		return eval == getOptimalEval();
	}
	public boolean isQuasiOptimal(double eval){
		DecimalFormat d = new DecimalFormat("#######.####");
		return d.format(eval).equals(d.format(getOptimalEval()));
	}
	
	public double[] perturb(double... x){
		double[] var = x.clone();
		for(int i = 0; i < var.length; i++){
			var[i] = perturb(var[i]);
		}
		return var;
	}
	
	protected double perturb(double x){
		double var = x + getPerturbation();
		if(var < minValue()){
			var = minValue();
		}
		if(var > maxValue()){
			var = maxValue();
		}
		
		return var;
	}
	
	protected abstract double getPerturbation();
	
	public abstract double minValue();
	public abstract double maxValue();
	
	public double[] getRandomSolution(){
		double[] x = new double[getNumVariables()];
		
		for(int i = 0; i < x.length; i++){
			x[i] = getFeasibleValue();
		}
		return x;
	}
	
	protected abstract double getFeasibleValue();

	public int getCount() {
		return count;
	}
	
	public void resetCount(){
		count = 0;
	}

	public int getNumVariables() {
		return numVariables;
	}

	public void setNumVariables(int numVariables) {
		this.numVariables = numVariables;
	}
	
}
