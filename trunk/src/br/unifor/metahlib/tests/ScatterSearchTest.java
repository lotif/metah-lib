package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.scatter.ScatterSearchTSP;

public class ScatterSearchTest {
	
	public static void main(String[] args) {
		
		try{
			File file = new File(System.getProperty("user.dir") + "/ulysses16.tsp");
			
			TSPProblem problem = new TSPProblem(file);
			problem.setRandomSeed(3);
			
			Heuristic scatter = new ScatterSearchTSP(problem, 20);
			scatter.setMax_it(50);
			
			OptimizationResult r = scatter.run();
			
			System.out.println("Best Solution: " + r.getBestSolution());
			System.out.println("Found on iteration " + r.getBestSolutionIteration());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	/*
	 * att48 - 3 - 79
	 * a280	- 2 - 10155
	 * bays29 - 3 - 14
	 * berlin52 - 2 - 239
	 * ch150 - 3 - 8292
	 * d198 - 3 - 42268 (cost)
	 * gr120 - 1 - 4716
	 * gr17 - 2 - 2088 (cost)
	 * gr48 - 2 - 156
	 * pr76 - 2 - 19052
	 * si175 - 1 - 28699 (cost)
	 * swiss42 - 1 - 1313 (cost)
	 * ulysses16 - 3 - 0
	 */

}
