package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.metaheuristics.sa.SimulatedAnnealing;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.neighborhood.TwoOpt;

public class SimulatedAnnealing_tsp {

	public static void main(String[] args) {
		try{
			//File file = new File(System.getProperty("user.dir") + "/ulysses16.tsp");
			File file = new File(System.getProperty("user.dir") + "/d198.tsp");
			//File file = new File(System.getProperty("user.dir") + "/berlin52.tsp");
			
			NeighborhoodStructure neighborhoodStructure = new TwoOpt(); 
			TSPProblem problem = new TSPProblem(file);
			Heuristic h = new SimulatedAnnealing(problem, neighborhoodStructure, 1, 0.00001, 0.9, 1000);

			OptimizationResult r = h.run();
			System.out.println("Distance: " + r.getBestSolution().getCost());
			System.out.println("Found on iteration " + r.getBestSolutionIteration());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
