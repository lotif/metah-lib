package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.metaheuristics.scatter.ScatterSearch;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.scatter.ScatterSearchTSP;

public class ScatterSearchTest {
	
	public static void main(String[] args) {
		
		try{
			File file = new File(System.getProperty("user.dir") + "/a280.tsp");
			
			TSPProblem problem = new TSPProblem(file);
			
			Heuristic scatter = new ScatterSearchTSP(problem, 20);
			
			OptimizationResult r = scatter.run();
			
			System.out.println("Best Solution: " + r.getBestSolution());
			System.out.println("Found on iteration " + r.getBestSolutionIteration());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
