package br.unifor.metahlib.problems.tsp.neighborhood;

import java.util.ArrayList;
import java.util.List;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * An implementation of the 3-opt TSP neighborhood structure 
 * This implementation is in accordance with the specification from the 
 * book "The traveling salesman problem and its variations" (Gutin, G. Punnen, A. 2002) p. 315-318.
 * 
 * 
 * @author marcelo lotif
 *
 */
public class ThreeOpt extends NeighborhoodStructure {

	public Problem problem;
	
	public ThreeOpt(Problem problem){
		super();
		this.problem = problem;
	}
	
	/**
	 * This method implements the 3-opt operation.
	 * The removed edges are randomly generated.
	 * 
	 * @param parents the list of parents. For the 3-opt operation, only one parent is needed.
	 * @return the child route.
	 */
	@Override
	public List<double[]> getNeighbours(List<double[]> parents) {
		
		TwoOpt two_opt1 = new TwoOpt();
		TwoOpt two_opt2_1 = new TwoOpt();
		TwoOpt two_opt2_2 = new TwoOpt();
		
		List<double[]> c = two_opt1.getNeighbours(parents);
		
		double[] child1 = two_opt2_1.getNeighbours(c, two_opt1.getEdge1(), -1).get(0);
		double[] child2 = two_opt2_2.getNeighbours(c, -1, two_opt1.getEdge2()).get(0);
		
		List<double[]> resultChild = new ArrayList<double[]>();
		
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
			resultChild.add(child1);
		} else {
			resultChild.add(child2);
		}
		
		return resultChild;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}
}
