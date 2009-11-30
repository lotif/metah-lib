package br.unifor.metahlib.experiment;

import java.util.ArrayList;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;

public class Experiment {
	
	protected Heuristic heuristic;
	
	protected int executionQty = 10;
	
	protected String name;

	public Experiment(String name, Heuristic heuristic){
		this.name = name;
		this.heuristic = heuristic;
	}
	
	public ExperimentResult run(){
		ArrayList<OptimizationResult> results = new ArrayList<OptimizationResult>(); 
		for (int i = 0; i < executionQty; ++i){
			results.add(heuristic.run());
		}
		
		return new ExperimentResult(name, results);
	}
	
	public int getExecutionQty() {
		return executionQty;
	}

	public void setExecutionQty(int executionQty) {
		this.executionQty = executionQty;
	}
}
