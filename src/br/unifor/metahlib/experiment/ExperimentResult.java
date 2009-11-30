package br.unifor.metahlib.experiment;

import java.util.ArrayList;

import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.base.Solution;

public class ExperimentResult implements Comparable<ExperimentResult> {
	
	protected String name;
	
	protected ArrayList<Double> evolution_mean;
	
	protected ArrayList<Double> evolution_stddev;
	
	protected ArrayList<Double> bestEvolution_mean;
	
	protected ArrayList<Double> bestEvolution_stddev;
	
	protected Solution bestSolution;
	
	protected int bestSolutionIteration;
	
	protected long runTime_mean;
	
	protected long runTime_stddev;
	
	protected double bestSolutionCost_mean;
	
	protected double bestSolutionCost_stddev;
	
	protected int bestSolutionIteration_mean;
	
	protected int bestSolutionIteration_stddev;
	
	protected interface Function{
		double execute(double[] values);
	}
	
	protected static class Mean implements Function{
		public double execute(double[] values){
	        int len = values.length;
	        if (len > 0){
	            double sum = 0;
	            for (double v: values){
	                sum+= v;
	            }

	            return sum / len;

	        } else {
	            return 0;
	        }
	    }
	}
	
	protected static class StdDev implements Function{
		
		private Mean mean = new Mean();
		
		public double execute(double[] values){
	        int len = values.length;
	        if (len > 0){
	            double m = mean.execute(values);
	            double sum = 0;
	            for (double v: values){
	                sum+= Math.pow(m - v, 2);
	            }

	            return Math.sqrt(sum / len);

	        } else {
	            return 0;
	        }
	    }
	}
	
	protected ArrayList<Double> calculateSequences(Function func, ArrayList<ArrayList<Double>> sequences){
		ArrayList<Double> result = new ArrayList<Double>();
		double[] values = new double[sequences.size()];

		int minSize = Integer.MAX_VALUE;
		for (int i = 0; i < sequences.size(); ++i){
			minSize = Math.min(minSize, sequences.get(i).size());
		}
		
		for (int i = 0; i < minSize; ++i){
			for (int j = 0; j < sequences.size(); ++j){
				values[j] = sequences.get(j).get(i);
			}
			
			result.add(func.execute(values));
		}
		
		return result;
	}
    
    public ExperimentResult(String name, ArrayList<OptimizationResult> results){
    	this.name = name;
    	int len = results.size();
    	OptimizationResult optResult;
    	Solution s;
    	double[] runTimeValues = new double[len];
    	double[] bestSolutionCosts = new double[len];
    	double[] bestSolutionIterations = new double[len];
    	ArrayList<ArrayList<Double>> evolutions = new ArrayList<ArrayList<Double>>();
    	ArrayList<ArrayList<Double>> bestEvolutions = new ArrayList<ArrayList<Double>>();
    	for (int i = 0; i < len; ++i){
    		optResult = results.get(i);
    		runTimeValues[i] = optResult.getRunTime();
    		bestSolutionCosts[i] = optResult.getBestSolution().getCost();
    		bestSolutionIterations[i] = optResult.getBestSolutionIteration();
    		s = optResult.getBestSolution();
    		if (bestSolution == null || s.getCost() < bestSolution.getCost()){
    			bestSolution = s;
    		}
    		
    		evolutions.add(optResult.getEvolution());
    		bestEvolutions.add(optResult.getBestEvolution());
    	}
    	
    	Mean mean = new Mean();
    	StdDev stddev = new StdDev(); 
    	
    	runTime_mean = (long) mean.execute(runTimeValues);
    	runTime_stddev = (long) stddev.execute(runTimeValues);
    	
    	bestSolutionCost_mean = mean.execute(bestSolutionCosts);
    	bestSolutionCost_stddev = stddev.execute(bestSolutionCosts);
    	
    	bestSolutionIteration_mean = (int) mean.execute(bestSolutionIterations);
    	bestSolutionIteration_stddev = (int) stddev.execute(bestSolutionIterations); 
    	
    	evolution_mean = calculateSequences(mean, evolutions);
    	evolution_stddev = calculateSequences(stddev, evolutions);
    	bestEvolution_mean = calculateSequences(mean, bestEvolutions);
    	bestEvolution_stddev = calculateSequences(stddev, bestEvolutions);
    }

	public String getName() {
		return name;
	}

	public ArrayList<Double> getEvolution_mean() {
		return evolution_mean;
	}

	public ArrayList<Double> getEvolution_stddev() {
		return evolution_stddev;
	}

	public ArrayList<Double> getBestEvolution_mean() {
		return bestEvolution_mean;
	}

	public ArrayList<Double> getBestEvolution_stddev() {
		return bestEvolution_stddev;
	}

	public Solution getBestSolution() {
		return bestSolution;
	}

	public int getBestSolutionIteration() {
		return bestSolutionIteration;
	}

	public long getRunTime_mean() {
		return runTime_mean;
	}

	public long getRunTime_stddev() {
		return runTime_stddev;
	}
	
	public double getBestSolutionCost_mean() {
		return bestSolutionCost_mean;
	}

	public double getBestSolutionCost_stddev() {
		return bestSolutionCost_stddev;
	}
	
	public int getBestSolutionIteration_mean() {
		return bestSolutionIteration_mean;
	}

	public int getBestSolutionIteration_stddev() {
		return bestSolutionIteration_stddev;
	}
	
	@Override
	public String toString(){
		String s = "Name: " + name + "\n"; 
		s+= String.format("Best solution cost. Mean: %f. StdDev: %f. Best: %f.\n", 
				bestSolutionCost_mean, bestSolutionCost_stddev, bestSolution.getCost());
		s+= String.format("Found at iteration. Mean: %d. StdDev: %d.\n", 
				bestSolutionIteration_mean, bestSolutionIteration_stddev);
		s+= String.format("RunTime (ms): Mean: %d. StdDev: %d", 
				runTime_mean / 1000000, runTime_stddev / 1000000);
		
		return s;
	}
	
	@Override
	public int compareTo(ExperimentResult o) {
		if (this.bestSolutionCost_mean < o.bestSolutionCost_mean){
			return -1;
		} else {
			if (this.bestSolutionCost_mean == o.bestSolutionCost_mean){
				if (this.bestSolutionCost_stddev < o.bestSolutionCost_stddev){
					return -1;
				} else {
					if (this.bestSolutionCost_stddev == o.bestSolutionCost_stddev){
						if (this.runTime_mean < o.runTime_mean){
							return -1;
						} else {
							if (this.runTime_mean == o.runTime_mean){
								return 0;
							}
						}
					}
				}
			}
		}
		return 1;
	}
}
