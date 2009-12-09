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

		for (int i = 1; i < e; i++) {
			int cityI = sol[i - 1];
			double til = 0;
			double dist = 0;
			double[] prob = new double[Jki.size()];

			for (int l = 0; l < Jki.size(); l++) {
				int cityL = Jki.get(l);
				til += tij[cityI - 1][cityL - 1];
				dist += problem.getInstance().getDistance(cityI, cityL);
			}
			double den = Math.pow(til, alpha) * Math.pow(1.0 / dist, beta);

			double total = 0;
			for (int j = 0; j < Jki.size(); j++) {
				int cityJ = Jki.get(j);
				double num = Math.pow(tij[cityI - 1][cityJ - 1], alpha)
						* Math.pow(1.0 / problem.getInstance().getDistance(
								cityI, cityJ), beta);

				double pkij = num / den;
				prob[j] = pkij;
				total += pkij;
			}

			prob[0] = prob[0] / total;
			double last = prob[0];
			for (int j = 1; j < prob.length; j++) {
				prob[j] = prob[j] / total + last;
				last = prob[j];
			}

			double r = rand.nextDouble();
			int pos = 0;
			while (pos < prob.length - 1 && r > prob[pos]) {
				pos++;
			}
			sol[i] = Jki.remove(pos);
		}
		solution.setValues(sol);
	}

}
