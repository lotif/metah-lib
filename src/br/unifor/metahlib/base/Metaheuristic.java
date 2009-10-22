package br.unifor.metahlib.base;

public abstract class Metaheuristic {
	
	public Metaheuristic(){	}
	
	protected int lastBestFoundOn = 0;
	protected double[] initialSolution = null;
	
	public abstract Function getFunction();
	public abstract void setFunction(Function function);
	public abstract double[] execute();
	
//	public abstract void enableGraphic();
	
	public int getLastBestFoundOn() {
		return lastBestFoundOn;
	}

	public String getHeader() {
		return "Unknown Metaheuristic";
	}
	
	public double[] getInitialSolution() {
		return initialSolution;
	}
	
	public void setInitialSolution(double[] initialSolution) {
		this.initialSolution = initialSolution;
	}
	
}
