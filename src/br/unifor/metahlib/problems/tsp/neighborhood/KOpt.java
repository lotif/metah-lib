package br.unifor.metahlib.problems.tsp.neighborhood;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * An implementation of the k-opt TSP neighborhood structure 
 * This implementation is in accordance with the specification of the 3-opt operation from the 
 * book "The traveling salesman problem and its variations" (Gutin, G. Punnen, A. 2002) p. 315-318.
 * 
 * In simple lines, this class does a K-Opt operation by making successive k*(2-Opt) operations. 
 * The first 2-opt removes 2 random edges and reconnect the path by relinking the cities with the only
 * 2 possible edges that can be made. From the second 2-opt on, this operation will remove one edge created
 * by the latter operation and another random edge. 
 * 
 * @author marcelo lotif
 *
 */
public class KOpt extends TwoOpt {

	/**
	 * The number of edges to be removed
	 */
	private int k;
	
	/**
	 * The problem which this operation is applied 
	 */
	private Problem problem;

	/**
	 * Constructor of the class
	 * 
	 * @param problem The problem which this operation is applied
	 * @param k The number of edges to be removed
	 */
	public KOpt(Problem problem, int k){
		super();
		this.problem = problem;
		this.setRandom(problem.getRandom());
		this.k = k;
	}
	
	/**
	 * This method implements the k-opt operation.
	 * The removed edges are randomly generated.
	 * 
	 * @param parents the list of parents. For the k-opt operation, only one parent is needed.
	 * @return the child route.
	 */
	public Object[] kOpt(Object[] values) {
		
		TwoOptResult resultOpt1 = twoOpt(values, -1, -1);
		Object[] c = resultOpt1.neighbor;
		
		int opt = 2;
		
		do {
			TwoOptResult child1 = twoOpt(c, resultOpt1.removedEdges[0], -1);
			TwoOptResult child2 = twoOpt(c, -1, resultOpt1.removedEdges[1]);
			
			Solution c1 = new Solution(problem);
			c1.setValues(child1.neighbor);
			
			Solution c2 = new Solution(problem);
			c2.setValues(child2.neighbor);
			
			if (c1.getCost() < c2.getCost()){
				c = child1.neighbor;
			} else {
				c = child2.neighbor;
			}
			
			opt++;
		
		} while (opt < k);
		
		return c;
	}
	
	@Override
	public Solution getRandomNeighbor(Solution solution) {
		Solution s = solution.duplicate();
		s.setValues(kOpt(s.getValues()));
		return s;
	}
}
