package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;
import br.unifor.metahlib.metaheuristics.ga.GeneticAlgorithm;
import br.unifor.metahlib.metaheuristics.ga.MutationOperator;
import br.unifor.metahlib.metaheuristics.ga.Selector;
import br.unifor.metahlib.metaheuristics.ga.selector.RouletteSelector;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.ga.PartialMappedCrossover;
import br.unifor.metahlib.problems.tsp.ga.TwoOptMutationOperator;

public class GA_tsp {
	public static void main(String[] args) {
		try{
			//File file = new File(System.getProperty("user.dir") + "/ulysses16.tsp");
			//Object[] optTour = new Object[] { 1, 14, 13, 12, 7, 6, 15, 5, 11, 9, 10, 16, 3, 2, 4, 8 };
			
			//File file = new File(System.getProperty("user.dir") + "/d198.tsp");
			//Object[] optTour = new Object[] {};			
			
			File file = new File(System.getProperty("user.dir") + "/berlin52.tsp");
			Object[] optTour = new Object[] {1, 49, 32, 45, 19, 41, 8, 9, 10, 43, 33, 51, 11, 52, 14, 13, 47, 26, 27, 28, 12, 25, 4, 6, 15, 5, 24, 48, 38, 37, 40, 39, 36, 35, 34, 44, 46, 16, 29, 50, 20, 23, 30, 2, 7, 42, 21, 17, 3, 18, 31, 22 };
			
			TSPProblem problem = new TSPProblem(file);
			
			CrossoverOperator crossoverOperator = new PartialMappedCrossover();
			MutationOperator mutationOperator = new TwoOptMutationOperator();
			Selector reproductionSelector = new RouletteSelector();
			Selector surviveSelector = new RouletteSelector();
			
			GeneticAlgorithm ga = new GeneticAlgorithm(problem, crossoverOperator, mutationOperator,
					reproductionSelector, surviveSelector);
			ga.setMax_it(200);
			ga.setPopulationSize(50);
			ga.setMutationProbability(0.10);
			ga.setCrossoverProbability(0.75);
			
			Solution s = ga.execute();
			System.out.println("Resultl: " + s.toString());

			Solution optimal = new Solution(problem);
			optimal.setValues(optTour);
			System.out.println("Optimal: " + optimal);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
