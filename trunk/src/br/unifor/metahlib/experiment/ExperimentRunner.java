package br.unifor.metahlib.experiment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class ExperimentRunner {
	
	protected int executionQty;
	
	protected ArrayList<Experiment> experiments;
	
	protected ArrayList<ExperimentResult> results;
	
	public ExperimentRunner(int executionQty){
		this.executionQty = executionQty;
		this.experiments = new ArrayList<Experiment>();
		this.results = new ArrayList<ExperimentResult>();
		
	}

	public void addExperiment(Experiment experiment){
		experiments.add(experiment);
	}
	
	public ExperimentResult getBestExperimentResult(){
		Collections.sort(results);
		return results.get(0);
	}
	
	public ExperimentResult run(){
		results.clear();
		for (Experiment ex: experiments){
			ExperimentResult r = ex.run();
			results.add(r);
		}
		
		return getBestExperimentResult();
	}

	public String report(){
		Collections.sort(results);
		String s = "Ranking:\n\n";
		for (int i = 0; i < results.size(); ++i){
			ExperimentResult r = results.get(i);			
			s+= (i+1) + ") " + r.toString() + "\n\n";
		}
		
		return s;
	}
	
	private void writeSequenceToCSVFile(String name, ArrayList<Double> sequence, 
			FileWriter writer) throws IOException{
	    
		writer.write(name);
		for (Double d: sequence){
	        writer.write(String.format(";%.4f", d));
	    }
		writer.write("\r\n");
	}
	
	
	public void export(String prefixFilename) throws IOException{
		FileWriter result = new FileWriter(prefixFilename + ".csv");
		FileWriter evolutionMean = new FileWriter(prefixFilename + "_evolution_mean.csv");
		FileWriter evolutionStdDev = new FileWriter(prefixFilename + "_evolution_stddev.csv");
		FileWriter bestEvolutionMean = new FileWriter(prefixFilename + "_best_evolution_mean.csv");
		FileWriter bestEvolutionStdDev = new FileWriter(prefixFilename + "_best_evolution_stddev.csv");
		
		result.write("Rank;Name;Best(mean);Best(stddev);RunTime(mean);RunTime(stddev);BestIteration(mean);BestIteration(stdev)\r\n");

		Collections.sort(results);
		for (int i = 0; i < results.size(); ++i){
			ExperimentResult r = results.get(i);			
			result.write(String.format("%d;%s;%f;%f;%d;%d;%d;%d\r\n", i+ 1, r.getName(), 
					r.getBestSolutionCost_mean(), r.getBestSolutionCost_stddev(),
					r.getRunTime_mean() / 1000000, r.getRunTime_stddev() / 1000000,
					r.getBestSolutionIteration_mean(), r.getBestSolutionIteration_stddev()));
			
			writeSequenceToCSVFile(r.getName(), r.getEvolution_mean(), evolutionMean);
			writeSequenceToCSVFile(r.getName(), r.getEvolution_stddev(), evolutionStdDev);
			writeSequenceToCSVFile(r.getName(), r.getBestEvolution_mean(), bestEvolutionMean);			
			writeSequenceToCSVFile(r.getName(), r.getBestEvolution_stddev(), bestEvolutionStdDev);			
		}
		
		result.close();
		evolutionMean.close();
		evolutionStdDev.close();
		bestEvolutionMean.close();
		bestEvolutionStdDev.close();
	}
}
