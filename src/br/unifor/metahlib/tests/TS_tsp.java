package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.base.SolutionFactory;
import br.unifor.metahlib.metaheuristics.ts.TabuList;
import br.unifor.metahlib.metaheuristics.ts.TabuSearch;
import br.unifor.metahlib.problems.tsp.GreedyConstructiveSolutionGenerator;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.neighborhood.*;
import br.unifor.metahlib.problems.tsp.ts.*;

public class TS_tsp {
	public static void main(String[] args) {
		try{
			System.out.println("Start");
			//File file = new File(System.getProperty("user.dir") + "/ulysses16.tsp");
			File file = new File(System.getProperty("user.dir") + "/berlin52.tsp");
			
			NeighborhoodStructure neighborhoodStructure = new SwapCities(); 
			TSPProblem problem = new TSPProblem(file);
			SolutionFactory solutionFactory = new GreedyConstructiveSolutionGenerator(problem);
			
			//TabuList tabuList = new SwapTabuList(30 /*size*/);
			TabuList tabuList = new CityTabuList(15 /*size*/);
			
			Heuristic h = new TabuSearch(problem, neighborhoodStructure, solutionFactory, 
					tabuList, 100);

			OptimizationResult r = h.run();
			System.out.println(r);;
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
