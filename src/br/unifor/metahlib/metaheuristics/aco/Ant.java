package br.unifor.metahlib.metaheuristics.aco;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * Class responsible for encapsulate the ant colony optimization method for
 * building a tour
 */
public class Ant implements Comparable<Ant> {

	/**
	 * Problem to be optimized.
	 */
	Problem problem;

	/**
	 * Solution represented by this ant.
	 */
	Solution solution;

	/**
	 * @param problem
	 */
	public Ant(Problem problem) {
		// TODO
		this.problem = problem;
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
	public void setProblem(Problem problem) {
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

	public void buildNewTour() {
		// TODO Auto-generated method stub

	}

}
