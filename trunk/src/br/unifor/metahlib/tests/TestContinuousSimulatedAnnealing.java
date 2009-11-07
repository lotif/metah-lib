package br.unifor.metahlib.tests;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.sa.SimulatedAnnealing;
import br.unifor.metahlib.problems.continuous.ContinuousOptimizationProblem;
import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.Perturber;
import br.unifor.metahlib.problems.continuous.Perturber.DimensionSelector;
import br.unifor.metahlib.problems.continuous.functions.*;
import br.unifor.metahlib.problems.continuous.perturbers.*;

public class TestContinuousSimulatedAnnealing {

	public static void main(String[] args) {
		try{
			OptimizableFunction function = new SumPowFunction();
			//OptimizableFunction function = new UnidFunction();
			//OptimizableFunction function = new RanaFunction();

			Perturber perturber = new UniformPertuber(function);
			perturber.setMaxPercentChange(0.20);
			perturber.setDimensionSelector(DimensionSelector.ALEATORY);
			perturber.setPerturbedDimensionsCount(1);
			
			ContinuousOptimizationProblem problem = new ContinuousOptimizationProblem(function, perturber);
			Heuristic h = new SimulatedAnnealing(problem, 1, 0.00001, 0.9, 1000);
			Solution s = h.execute();

			System.out.println("Cost: " + s.getCost() + ". Optimal: " + function.getOptimalResult());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
