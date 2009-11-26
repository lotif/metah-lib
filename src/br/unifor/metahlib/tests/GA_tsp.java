package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;
import br.unifor.metahlib.metaheuristics.ga.GeneticAlgorithm;
import br.unifor.metahlib.metaheuristics.ga.MutationOperator;
import br.unifor.metahlib.metaheuristics.ga.Selector;
import br.unifor.metahlib.metaheuristics.ga.selector.*;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.ga.*;

public class GA_tsp {
	
	public static void main(String[] args) {
		try{
			//File file = new File(System.getProperty("user.dir") + "/ulysses16.tsp");
			//File file = new File(System.getProperty("user.dir") + "/d198.tsp");
			File file = new File(System.getProperty("user.dir") + "/berlin52.tsp");
			
			TSPProblem problem = new TSPProblem(file);
			
			CrossoverOperator crossoverOperator = new OrdenatedCrossover();
			MutationOperator mutationOperator = new ShiftMutationOperator();
			Selector reproductionSelector = new RouletteSelector();
			Selector surviveSelector = new DeterministicSelector();
			
			GeneticAlgorithm ga = new GeneticAlgorithm(problem, crossoverOperator, mutationOperator,
					reproductionSelector, surviveSelector);
			ga.setMax_it(200);
			ga.setPopulationSize(50);
			ga.setMutationProbability(0.10);
			ga.setCrossoverProbability(0.75);
			
			OptimizationResult r = ga.run();
			System.out.println("Resultl: " + r.getBestSolution().toString());

		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
