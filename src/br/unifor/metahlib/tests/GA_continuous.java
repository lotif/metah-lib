package br.unifor.metahlib.tests;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;
import br.unifor.metahlib.metaheuristics.ga.GeneticAlgorithm;
import br.unifor.metahlib.metaheuristics.ga.MutationOperator;
import br.unifor.metahlib.metaheuristics.ga.Selector;
import br.unifor.metahlib.metaheuristics.ga.selector.RouletteSelector;
import br.unifor.metahlib.problems.continuous.ContinuousOptimizationProblem;
import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.Perturber;
import br.unifor.metahlib.problems.continuous.Perturber.DimensionSelector;
import br.unifor.metahlib.problems.continuous.functions.*;
import br.unifor.metahlib.problems.continuous.ga.PerturbMutationOperator;
import br.unifor.metahlib.problems.continuous.ga.TwoPointsCrossover;
import br.unifor.metahlib.problems.continuous.perturbers.*;

public class GA_continuous {
	public static void main(String[] args) {
		try{
			//OptimizableFunction function = new SumPowFunction();
			//OptimizableFunction function = new UnidFunction();
			OptimizableFunction function = new RanaFunction();

			Perturber perturber = new UniformPertuber(function);
			perturber.setMaxPercentChange(0.20);
			perturber.setDimensionSelector(DimensionSelector.ALEATORY);
			perturber.setPerturbedDimensionsCount(1);
			
			ContinuousOptimizationProblem problem = new ContinuousOptimizationProblem(function);
			
			CrossoverOperator crossoverOperator = new TwoPointsCrossover();
			MutationOperator mutationOperator = new PerturbMutationOperator(perturber);
			Selector reproductionSelector = new RouletteSelector();
			Selector surviveSelector = new RouletteSelector();
			
			GeneticAlgorithm ga = new GeneticAlgorithm(problem, crossoverOperator, mutationOperator,
					reproductionSelector, surviveSelector);
			ga.setMax_it(200);
			ga.setPopulationSize(50);
			ga.setMutationProbability(0.10);
			ga.setCrossoverProbability(0.75);
			
			Solution s = ga.execute();

			System.out.println("Result: " + function.execute(s.getValues()) + ". OptimalResult: " + function.getOptimalResult());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
