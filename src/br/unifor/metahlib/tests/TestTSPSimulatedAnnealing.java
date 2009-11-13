package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.sa.SimulatedAnnealing;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.neighborhood.TwoOpt;

public class TestTSPSimulatedAnnealing {

	public static void main(String[] args) {
		try{
			//File file = new File(System.getProperty("user.dir") + "/ulysses16.tsp");
			File file = new File(System.getProperty("user.dir") + "/d198.tsp");
			Object[] optTour = new Object[] { 1, 14, 13, 12, 7, 6, 15, 5, 11, 9, 10, 16, 3, 2, 4, 8 };
			
			//File file = new File(System.getProperty("user.dir") + "/berlin52.tsp");
			//Object[] optTour = new Object[] {1, 49, 32, 45, 19, 41, 8, 9, 10, 43, 33, 51, 11, 52, 14, 13, 47, 26, 27, 28, 12, 25, 4, 6, 15, 5, 24, 48, 38, 37, 40, 39, 36, 35, 34, 44, 46, 16, 29, 50, 20, 23, 30, 2, 7, 42, 21, 17, 3, 18, 31, 22 };
			
			NeighborhoodStructure neighborhoodStructure = new TwoOpt(); 
			TSPProblem problem = new TSPProblem(file, neighborhoodStructure);
			Heuristic h = new SimulatedAnnealing(problem, 1, 0.00001, 0.9, 1000);
			Solution s = h.execute();
			System.out.println("Distance: " + s.getCost());

			Solution optimal = new Solution(problem);
			optimal.setValues(optTour);
			System.out.println("Optimal: " + optimal);
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
