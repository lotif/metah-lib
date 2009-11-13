package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.heuristics.hillclimbing.HillClimbing;
import br.unifor.metahlib.metaheuristics.gls.GuidedLocalSearch;
import br.unifor.metahlib.problems.tsp.GreedyConstructiveSolutionGenerator;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.neighborhood.*;

public class GLSTest {

	public static void main(String[] args) {
		try{
			File file = new File(System.getProperty("user.dir") + "/berlin52.tsp");
//			Object[] optTour = new Object[] {1, 49, 32, 45, 19, 41, 8, 9, 10, 43, 33, 51, 11, 52, 14, 13, 47, 26, 27, 28, 12, 25, 4, 6, 15, 5, 24, 48, 38, 37, 40, 39, 36, 35, 34, 44, 46, 16, 29, 50, 20, 23, 30, 2, 7, 42, 21, 17, 3, 18, 31, 22 };
			
//			NeighborhoodStructure neighborhoodStructure = new TwoOpt(); 
//			TSPProblem problem = new TSPProblem(file, neighborhoodStructure);
			
			KOpt neighborhoodStructure = new KOpt(null, 3);
			TSPProblem problem = new TSPProblem(file, neighborhoodStructure);
			neighborhoodStructure.setProblem(problem);
			
			HillClimbing h = new HillClimbing(problem, HillClimbing.DEFAULT, 1500, 0, 0);
			
			/* 
			 * Possible values for the GLS parameter "a", according with Vordouris 1997:	
			 * 2-OPT: 1/8 <= a <= 1/2
			 * 3-OPT: 1/10 <= a <= 1/4
			 * LK-OPT: 1/12 <= a <= 1/6	
			 */
			Heuristic gls = new GuidedLocalSearch(problem, h, 1000, 1.0/10.0);
			
			GreedyConstructiveSolutionGenerator generator = new GreedyConstructiveSolutionGenerator(problem);
			((GuidedLocalSearch)gls).setInitialSolution(generator.generateNewGreedySolution());
			
			Solution s = gls.execute();
			System.out.println("Distance: " + s.getCost());

//			Solution optimal = new Solution(problem);
//			optimal.setValues(optTour);
//			System.out.println("Optimal: " + optimal);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
