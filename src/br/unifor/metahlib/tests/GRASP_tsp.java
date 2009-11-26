package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.heuristics.hillclimbing.HillClimbing;
import br.unifor.metahlib.metaheuristics.grasp.GRASP;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.grasp.TSPGreedyRandomizedContructor;
import br.unifor.metahlib.problems.tsp.neighborhood.TwoOpt;

public class GRASP_tsp {
	public static void main(String[] args) {
		try{
			File file = new File(System.getProperty("user.dir") + "/gr48.tsp");
			
			TwoOpt twoOpt = new TwoOpt();
			TSPProblem problem = new TSPProblem(file);		
			
			HillClimbing h = new HillClimbing(problem, twoOpt, HillClimbing.DEFAULT, 1500, 0, 0);
			
			Heuristic grasp = new GRASP(problem, h, 2000, new TSPGreedyRandomizedContructor(problem, 0.5));

			OptimizationResult r = grasp.run();
			System.out.println("Distance: " + r.getBestSolution().getCost());
			System.out.println("Found on iteration " + r.getBestSolutionIteration());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
