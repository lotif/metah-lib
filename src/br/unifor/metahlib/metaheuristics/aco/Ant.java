package br.unifor.metahlib.metaheuristics.aco;

import java.util.ArrayList;
import java.util.Random;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.problems.tsp.TSPProblem;

/**
 * Class responsible for encapsulate the ant colony optimization method for
 * building a tour
 */
public class Ant implements Comparable<Ant> {

	/**
	 * Problem to be optimized.
	 */
	TSPProblem problem;

	/**
	 * Solution represented by this ant.
	 */
	Solution solution;

	/**
	 * number of cities
	 */
	private int e;
	/**
	 * Randomly selected city
	 */
	private int city;
	/**
	 * weight of trail intensity
	 */
	private double alpha;
	/**
	 * weight of visibility
	 */
	private double beta;

	/**
	 * @param problem
	 * @param beta
	 * @param alpha
	 */
	public Ant(TSPProblem problem, int e, int city, double alpha, double beta) {
		this.problem = problem;
		this.e = e;
		this.city = city;
		this.alpha = alpha;
		this.beta = beta;
		solution = new Solution(problem);
	}

	@Override
	public int compareTo(Ant o) {
		if (getTourCost() == o.getTourCost()) {
			return 0;
		} else {
			return getTourCost() > o.getTourCost() ? 1 : -1;
		}
	}

	/**
	 * @return the problem
	 */
	public Problem getProblem() {
		return problem;
	}

	/**
	 * @param problem
	 *            the problem to set
	 */
	public void setProblem(TSPProblem problem) {
		this.problem = problem;
	}

	/**
	 * @return the solution
	 */
	public Solution getSolution() {
		return solution;
	}

	/**
	 * @param solution
	 *            the solution to set
	 */
	public void setSolution(Solution solution) {
		this.solution = solution;
	}

	private double getTourCost() {
		return -1 * solution.getCost();
	}

	public void buildNewTour(Double[][] tij) {
		Random rand = new Random();
		ArrayList<Integer> Jki = new ArrayList<Integer>();
		for (int i = 0; i < e; i++) {
			Jki.add(new Integer(i + 1));
		}
		Integer[] sol = new Integer[e];
		sol[0] = Jki.remove(city - 1);
		int cityJ = Jki.remove(rand.nextInt(Jki.size()));
		sol[1] = cityJ;

		double til = tij[sol[0] - 1][sol[1] - 1];
		double dist = problem.getDataSet().getDistance(sol[0], sol[1]);

		for (int i = 2; i < e; i++) {
			double prob;
			int cityI = sol[i - 1];
			int pos = -1;
			while (pos == -1) {
				for (int j = 0; j < Jki.size(); j++) {
					cityJ = Jki.get(j);
					double num = Math.pow(tij[cityI - 1][cityJ - 1], alpha)
							* Math.pow(1.0 / problem.getDataSet().getDistance(
									cityI, cityJ), beta);
					double den = Math.pow(til, alpha)
							* Math.pow(1.0 / dist, beta);
					double pkij = num / den;
					System.out.println("pkij: " + pkij);
					prob = rand.nextDouble();
					if (pkij >= prob) {
						pos = j;
						dist += problem.getDataSet().getDistance(cityI, cityJ);
						til += tij[cityI - 1][cityJ - 1];
						break;
					}
				}
			}
			sol[i] = Jki.remove(pos);
		}
		solution.setValues(sol);
	}

}
