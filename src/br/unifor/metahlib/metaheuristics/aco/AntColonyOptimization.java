package br.unifor.metahlib.metaheuristics.aco;

import java.util.ArrayList;
import java.util.Random;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.problems.tsp.TSPProblem;

/**
 * The ant colony optimization method
 */
public class AntColonyOptimization extends Heuristic {

	/**
	 * weight of trail intensity
	 */
	private double alpha;
	/**
	 * weight of visibility
	 */
	private double beta;
	/**
	 * pheromone decay rate
	 */
	private double p;
	/**
	 * Number of ants
	 */
	private int n;
	/**
	 * Constant used for updating pheromone trails
	 */
	private double q;
	/**
	 * initial trail intensity
	 */
	private Double[][] t0;
	/**
	 * number of cities
	 */
	private int e;
	/**
	 * elitist ants
	 */
	double b;

	@Override
	public Solution execute() {
		Random rand = new Random();
		ArrayList<Ant> ants = new ArrayList<Ant>();
		for (int i = 0; i < n; i++) {
			int city = rand.nextInt(n) + 1;
			Ant ant = new Ant((TSPProblem) problem, n, city, alpha, beta);
			ants.add(ant);
		}
		// Let best be the best tour found from the beginning and lbest its
		// length
		Solution best = problem.getInitialSolution();
		double lbest = best.getCost();
		e = best.getValues().length;
		System.out.println("Cost 0: " + lbest);
		// Initialize Tij
		Double[][] tij = t0;
		int t = 1;
		while (t < max_it) {
			// for each ant build a new tour
			// evaluate the tour performed by each ant
			for (int i = 0; i < n; i++) {
				Ant ant = ants.get(i);
				ant.buildNewTour(tij);
			}
			// if a shorter tour is found, update best and lbest
			for (int i = 0; i < ants.size(); i++) {
				Ant ant = ants.get(i);
				if (lbest > ant.getSolution().getCost()) {
					best = ant.getSolution().duplicate();
					lbest = ant.getSolution().getCost();
				}
			}
			System.out.println("Cost " + t + ": " + lbest);
			// update pheromone trails
			// decay rate
			for (int i = 0; i < tij.length; i++) {
				for (int j = 0; j < tij[i].length; j++) {
					tij[i][j] *= (1 - p);
				}
			}
			// pheromone at iteration t
			for (int i = 0; i < n; i++) {
				Ant ant = ants.get(i);
				Object[] tour = ant.getSolution().getValues();
				double Lk = ant.getSolution().getCost();
				double value = q / Lk;
				for (int j = 0; j < tour.length - 1; j++) {
					int cityI = ((Integer) tour[j]).intValue();
					int cityJ = ((Integer) tour[j + 1]).intValue();
					tij[cityI - 1][cityJ - 1] += value;
					tij[cityJ - 1][cityI - 1] += value;
				}
				int cityI = ((Integer) tour[tour.length - 1]).intValue();
				int cityJ = ((Integer) tour[0]).intValue();
				tij[cityI - 1][cityJ - 1] += value;
				tij[cityJ - 1][cityI - 1] += value;
			}
			// elitist ants
			Object[] tour = best.getValues();
			double value = q / lbest;
			for (int j = 0; j < tour.length - 1; j++) {
				int cityI = ((Integer) tour[j]).intValue();
				int cityJ = ((Integer) tour[j + 1]).intValue();
				tij[cityI - 1][cityJ - 1] += (b * value);
				tij[cityJ - 1][cityI - 1] += (b * value);
			}
			int cityI = ((Integer) tour[tour.length - 1]).intValue();
			int cityJ = ((Integer) tour[0]).intValue();
			tij[cityI - 1][cityJ - 1] += (b * value);
			tij[cityJ - 1][cityI - 1] += (b * value);
			t++;
		}
		return best;
	}

	/**
	 * @param problem
	 * @param alpha
	 * @param beta
	 * @param p
	 * @param n
	 * @param q
	 * @param b
	 * @param t0
	 */
	public AntColonyOptimization(TSPProblem problem, double alpha, double beta,
			double p, int n, double q, double b, Double[][] t0) {
		super(problem);
		this.alpha = alpha;
		this.beta = beta;
		this.p = p;
		this.n = n;
		this.q = q;
		this.t0 = t0;
		this.b = b;
	}

	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * @param alpha
	 *            the alpha to set
	 */
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	/**
	 * @return the beta
	 */
	public double getBeta() {
		return beta;
	}

	/**
	 * @param beta
	 *            the beta to set
	 */
	public void setBeta(double beta) {
		this.beta = beta;
	}

	/**
	 * @return the p
	 */
	public double getP() {
		return p;
	}

	/**
	 * @param p
	 *            the p to set
	 */
	public void setP(double p) {
		this.p = p;
	}

	/**
	 * @return the n
	 */
	public int getN() {
		return n;
	}

	/**
	 * @param n
	 *            the n to set
	 */
	public void setN(int n) {
		this.n = n;
	}

	/**
	 * @return the q
	 */
	public double getQ() {
		return q;
	}

	/**
	 * @param q
	 *            the q to set
	 */
	public void setQ(double q) {
		this.q = q;
	}

	/**
	 * @return the t0
	 */
	public Double[][] getT0() {
		return t0;
	}

	/**
	 * @param t0
	 *            the t0 to set
	 */
	public void setT0(Double[][] t0) {
		this.t0 = t0;
	}

	/**
	 * @return the e
	 */
	public int getE() {
		return e;
	}

	/**
	 * @param e
	 *            the e to set
	 */
	public void setE(int e) {
		this.e = e;
	}

	/**
	 * @return the b
	 */
	public double getB() {
		return b;
	}

	/**
	 * @param b
	 *            the b to set
	 */
	public void setB(double b) {
		this.b = b;
	}

}
