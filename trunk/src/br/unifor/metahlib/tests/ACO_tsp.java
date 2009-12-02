package br.unifor.metahlib.tests;

import java.io.File;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.OptimizationResult;
import br.unifor.metahlib.metaheuristics.aco.AntColonyOptimization;
import br.unifor.metahlib.problems.tsp.TSPProblem;

public class ACO_tsp {
	public static void main(String[] args) {
		try {
			File file = new File(System.getProperty("user.dir") + "/d198.tsp");

			TSPProblem problem = new TSPProblem(file);

			// weight of trail intensity
			double alpha = 1;
			// weight of visibility
			double beta = 5;
			// pheromone decay rate
			double p = 0.5;
			// Number of ants
			int n = problem.getDataSet().getCities().size();
			// Constant used for updating pheromone trails
			double q = 100;
			// elitist ants
			double b = 5;
			// initial trail intensity
			Double[][] t0 = new Double[n][n];
			for (int i = 0; i < t0.length; i++) {
				for (int j = 0; j < t0[i].length; j++) {
					t0[i][j] = Math.pow(10, -6);
				}
			}

			Heuristic h = new AntColonyOptimization(problem, alpha, beta, p, n,
					q, b, t0);

			OptimizationResult r = h.run();
			System.out.println("Distance: " + r.getBestSolution().getCost());
			System.out.println("Found on iteration "
					+ r.getBestSolutionIteration());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
