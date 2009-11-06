package br.unifor.metahlib.problems.tsp.neighborhood;

import java.util.ArrayList;
import java.util.List;

import deprecated.Function;

import br.unifor.metahlib.base.NeighborhoodStructure;

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

	public Function function;
	
	public ThreeOpt(Function function){
		super();
		this.function = function;
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
		
		if(function.eval(child1) < function.eval(child2)){
			resultChild.add(child1);
		} else {
			resultChild.add(child2);
		}
		
		return resultChild;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
	}
}
