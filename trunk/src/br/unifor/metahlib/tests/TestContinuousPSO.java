package br.unifor.metahlib.tests;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.pso.Inertia;
import br.unifor.metahlib.metaheuristics.pso.MovementModel;
import br.unifor.metahlib.metaheuristics.pso.NeighborhoodTopology;
import br.unifor.metahlib.metaheuristics.pso.PSO;
import br.unifor.metahlib.metaheuristics.pso.inertia.ConstantInertia;
import br.unifor.metahlib.metaheuristics.pso.movement.ContinuousMovementModel;
import br.unifor.metahlib.metaheuristics.pso.neighborhood.GlobalBest;
import br.unifor.metahlib.problems.continuous.ContinuousOptimizationProblem;
import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.Perturber;
import br.unifor.metahlib.problems.continuous.Perturber.DimensionSelector;
import br.unifor.metahlib.problems.continuous.functions.*;
import br.unifor.metahlib.problems.continuous.perturbers.*;

public class TestContinuousPSO {

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
			
			MovementModel movementModel = new ContinuousMovementModel(problem);
			
			NeighborhoodTopology topology = new GlobalBest();
			
			Inertia inertia = new ConstantInertia(0.25);
			
			Heuristic h = new PSO(problem, topology, inertia, movementModel);
			Solution s = h.execute();

			System.out.println("Result: " + function.execute(s.getValues()) + ". OptimalResult: " + function.getOptimalResult());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
