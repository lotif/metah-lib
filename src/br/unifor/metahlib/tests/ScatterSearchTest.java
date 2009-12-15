package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.scatter.ScatterSearchTSP;

public class ScatterSearchTest {

	public static void main(String[] args) {

		try {
			File file = new File(System.getProperty("user.dir") + "/d198.tsp");

			TSPProblem problem = new TSPProblem(file);
			problem.setRandomSeed(3);

			Heuristic scatter = new ScatterSearchTSP(problem, 20);
			scatter.setMax_it(50);

			OptimizationResult r = scatter.run();

			System.out.println("Best Solution: " + r.getBestSolution());
			System.out.println("Found on iteration "
					+ r.getBestSolutionIteration());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* 
	 * a280 - 2 - 1452
	 * ch150 - 3 - 499 
	 * d198 - 3 - 42268 (cost) 
	 * gr120 - 1 - 396 (fazer com mais iterações)
	 * gr17 - 2 - 2088 (cost)
	 * gr48 - 2 - 28 ok 
	 * si175 - 1 - 28699 (cost) 24722 
	 * swiss42 - 1 - 1313 (cost) 
	 */

	
	/*
	 * Zerou
	 * ulysses16, att48 e bays29: 
	 * seed 3 
	 * 
	 * berlin52, pr76
	 * seed 2
	 */

}
