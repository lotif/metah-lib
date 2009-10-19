package br.unifor.metahlib.base;

public abstract class Metaheuristic {
	
	public Metaheuristic(){	}
	
	protected int lastBestFoundOn = 0;
	
	public abstract Function getFunction();
	public abstract void setFunction(Function function);

//	public abstract void enableGraphic();
	
	public int getLastBestFoundOn() {
		return lastBestFoundOn;
	}

	public String getHeader() {
		return "Unknown Metaheuristic";
	}
}
