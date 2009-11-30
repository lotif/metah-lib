package br.unifor.metahlib.base;

import java.util.ArrayList;

public class OptimizationResult {
	
	private Solution bestSolution;
	
	/**
	 * Stores the iteration where the best solution was found.
	 */
	private int bestSolutionIteration;

	private long startTime;
	
	private long runTime;
	
	private ArrayList<Double> evolution;
	
	private ArrayList<Double> bestEvolution;
	
	public void start(){
		evolution = new ArrayList<Double>();
		bestEvolution = new ArrayList<Double>();
		startTime = System.nanoTime();
	}
	
	public void finish(){
		runTime = System.nanoTime() - startTime;
	}
	
	public void endIteration(Solution s){
		evolution.add(s.getCost());
		if (bestSolution == null || (s.getCost() < bestSolution.getCost())){
			bestSolution = s.duplicate();
			bestSolutionIteration = evolution.size(); 
		}
		bestEvolution.add(bestSolution.getCost());
	}

	public Solution getBestSolution() {
		return bestSolution;
	}
	
	public void setBestSolution(Solution s) {
		bestSolution = s;
	}

	public int getBestSolutionIteration() {
		return bestSolutionIteration;
	}
	
	public void setBestSolutionIteration(int value) {
		bestSolutionIteration = value;
	}

	public long getRunTime() {
		return runTime;
	}

	public ArrayList<Double> getEvolution() {
		return evolution;
	}
	
	public ArrayList<Double> getBestEvolution() {
		return bestEvolution;
	}
	
	@Override
	public String toString(){
		String s = "Best solution:" + bestSolution + "\n";
		s+= "Found on iteration " + bestSolutionIteration;
		s+= "RunTime: " + (runTime / 1000000) + " (ms)";
		return s;
	}
}
