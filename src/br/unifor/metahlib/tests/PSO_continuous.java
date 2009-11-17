package br.unifor.metahlib.tests;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.pso.Inertia;
import br.unifor.metahlib.metaheuristics.pso.MovementModel;
import br.unifor.metahlib.metaheuristics.pso.NeighborhoodTopology;
import br.unifor.metahlib.metaheuristics.pso.ParticleSwamOptimization;
import br.unifor.metahlib.metaheuristics.pso.inertia.ConstantInertia;
import br.unifor.metahlib.metaheuristics.pso.movement.ContinuousMovementModel;
import br.unifor.metahlib.metaheuristics.pso.neighborhood.GlobalBest;
import br.unifor.metahlib.problems.continuous.ContinuousOptimizationProblem;
import br.unifor.metahlib.problems.continuous.OptimizableFunction;
import br.unifor.metahlib.problems.continuous.functions.*;

public class PSO_continuous {

	public static void main(String[] args) {
		try{
			OptimizableFunction function = new SumPowFunction();
			//OptimizableFunction function = new UnidFunction();
			//OptimizableFunction function = new RanaFunction();

			ContinuousOptimizationProblem problem = new ContinuousOptimizationProblem(function);
			
			MovementModel movementModel = new ContinuousMovementModel(problem);
			
			NeighborhoodTopology topology = new GlobalBest();
			
			Inertia inertia = new ConstantInertia(0.25);
			
			Heuristic h = new ParticleSwamOptimization(problem, topology, inertia, movementModel);
			Solution s = h.execute();

			System.out.println("Result: " + function.execute(s.getValues()) + ". OptimalResult: " + function.getOptimalResult());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
