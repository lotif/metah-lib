package br.unifor.metahlib.metaheuristics.aco;

import java.util.ArrayList;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

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
	private Double[] t0;
	/**
	 * number of cities
	 */
	private int e;

	@Override
	public Solution execute() {
		ArrayList<Ant> ants = new ArrayList<Ant>();
		for (int i = 0; i < n; i++) {
			Ant ant = new Ant(problem);
			ants.add(ant);
		}
		// Let best be the best tour found from the beginning and lbest its
		// length
		Solution best = problem.getInitialSolution();
		double lbest = best.getCost();
		e = best.getValues().length;
		// Initialize Tij
		Double[] tij = t0;
		int t = 1;
		while (t < max_it) {
			// for each ant build a new tour
			// evaluate the tour performed by each ant
			for (int i = 0; i < n; i++) {
				Ant ant = ants.get(i);
				ant.buildNewTour();
			}
			// if a shorter tour is found, update best and lbest
			for (int i = 0; i < ants.size(); i++) {
				Ant ant = ants.get(i);
				if (lbest > ant.getSolution().getCost()) {
					best = ant.getSolution().duplicate();
					lbest = ant.getSolution().getCost();
				}
			}
			// update pheromone trails
			// TODO Auto-generated method stub
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
	 * @param t0
	 */
	public AntColonyOptimization(Problem problem, double alpha, double beta,
			double p, int n, double q, Double[] t0) {
		super(problem);
		this.alpha = alpha;
		this.beta = beta;
		this.p = p;
		this.n = n;
		this.q = q;
		this.t0 = t0;
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
	public Double[] getT0() {
		return t0;
	}

	/**
	 * @param t0
	 *            the t0 to set
	 */
	public void setT0(Double[] t0) {
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
}
