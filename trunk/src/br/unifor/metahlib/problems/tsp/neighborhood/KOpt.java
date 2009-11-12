package br.unifor.metahlib.problems.tsp.neighborhood;

import java.util.List;

import br.unifor.metahlib.base.NeighborhoodStructure;
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
public class KOpt extends NeighborhoodStructure {

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
		this.k = k;
	}
	
	/**
	 * This method implements the k-opt operation.
	 * The removed edges are randomly generated.
	 * 
	 * @param parents the list of parents. For the k-opt operation, only one parent is needed.
	 * @return the child route.
	 */
	@Override
	public List<double[]> getNeighbours(List<double[]> parents) {
		
		TwoOpt two_opt1 = new TwoOpt();
		List<double[]> c = two_opt1.getNeighbours(parents);
		
		int opt = 2;
		
		do{
		
			TwoOpt two_opt2_1 = new TwoOpt();
			TwoOpt two_opt2_2 = new TwoOpt();
			
			double[] child1 = two_opt2_1.getNeighbours(c, two_opt1.getEdge1(), -1).get(0);
			double[] child2 = two_opt2_2.getNeighbours(c, -1, two_opt1.getEdge2()).get(0);
			
			Solution c1 = new Solution(problem);
			Solution c2 = new Solution(problem);
			
			Object[] ch1 = new Object[child1.length];
			Object[] ch2 = new Object[child1.length];
			
			for(int i = 0; i < ch1.length; i++){
				ch1[i] = (int) Double.parseDouble(child1[i] + "");
			}
			for(int i = 0; i < ch2.length; i++){
				ch2[i] = (int) Double.parseDouble(child2[i] + "");
			}
			
			c1.setValues(ch1);
			c2.setValues(ch2);
			
			if(problem.getCostEvaluator().eval(c1) < problem.getCostEvaluator().eval(c2)){
				c.set(0, child1);
			} else {
				c.add(0, child2);//
			}
			
			opt++;
		
		} while (opt < k);
		
		return c;
	}
	
	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

}
