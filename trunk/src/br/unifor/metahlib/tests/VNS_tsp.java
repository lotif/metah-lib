package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Solution;
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
			
			Heuristic vns = new VariableNeighborhoodSearch(problem, h, 20, fiveOpt, fourOpt, threeOpt, twoOpt);
			Solution s = vns.execute();
			System.out.println("Distance: " + s.getCost());
			System.out.println("Found on iteration " + vns.getLastBestFoundOn());
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
