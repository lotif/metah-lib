package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.heuristics.hillclimbing.HillClimbing;
import br.unifor.metahlib.metaheuristics.gls.GuidedLocalSearch;
import br.unifor.metahlib.problems.tsp.GreedyConstructiveSolutionGenerator;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.neighborhood.*;

public class GLS_tsp {

	public static void main(String[] args) {
		try{
			File file = new File(System.getProperty("user.dir") + "/berlin52.tsp");
			
//			NeighborhoodStructure neighborhoodStructure = new TwoOpt(); 
//			TSPProblem problem = new TSPProblem(file, neighborhoodStructure);
			
			TSPProblem problem = new TSPProblem(file);
			KOpt neighborhoodStructure = new KOpt(problem, 3);
			HillClimbing h = new HillClimbing(problem, neighborhoodStructure, HillClimbing.DEFAULT, 1500, 0, 0);
			
			/* 
			 * Possible values for the GLS parameter "a", according with Vordouris 1997:	
			 * 2-OPT: 1/8 <= a <= 1/2
			 * 3-OPT: 1/10 <= a <= 1/4
			 * LK-OPT: 1/12 <= a <= 1/6	
			 */
			Heuristic gls = new GuidedLocalSearch(problem, h, 1000, 1.0/10.0);
			
			GreedyConstructiveSolutionGenerator generator = new GreedyConstructiveSolutionGenerator(problem);
			((GuidedLocalSearch)gls).setInitialSolution(generator.generateNewGreedySolution());
			
			OptimizationResult r = gls.run();
			System.out.println("Distance: " + r.getBestSolution().getCost());
			System.out.println("Found on iteration " + r.getBestSolutionIteration());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
