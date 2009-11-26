package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.heuristics.hillclimbing.HillClimbing;
import br.unifor.metahlib.metaheuristics.vns.VariableNeighborhoodSearch;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.neighborhood.KOpt;
import br.unifor.metahlib.problems.tsp.neighborhood.TwoOpt;

public class VNS_tsp {
	
	public static void main(String[] args) {
		try{
			File file = new File(System.getProperty("user.dir") + "/a280.tsp");
			
			TwoOpt twoOpt = new TwoOpt();
			TSPProblem problem = new TSPProblem(file);
			
			KOpt threeOpt = new KOpt(problem, 3);
			KOpt fourOpt = new KOpt(problem, 4);
			KOpt fiveOpt = new KOpt(problem, 5);			
			
			HillClimbing h = new HillClimbing(problem, twoOpt, HillClimbing.DEFAULT, 1500, 0, 0);
			
			Heuristic vns = new VariableNeighborhoodSearch(problem, h, 100, 100, fiveOpt, fourOpt, threeOpt, twoOpt);
			OptimizationResult r = vns.run();
			System.out.println("Best Solution: " + r.getBestSolution());
			System.out.println("Found on iteration " + r.getBestSolutionIteration());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
