package br.unifor.metahlib.metaheuristics.ts;

import java.util.ArrayList;
import br.unifor.metahlib.base.Solution;

/**
 * Implements the concept of tabu list. The children classes must implement an appropriated
 * tabu strategy for the problem class to be optimized.
 */
public abstract class TabuList {

	/**
	 * Remove the tabu solutions of list.
	 * @return The removed items os list.
	 */
	public ArrayList<Solution> removeTabuSolutions(ArrayList<Solution> solutions){
		int i = 0;
		ArrayList<Solution> removed = new ArrayList<Solution>();
		Solution s;
		while (i < solutions.size()){
			s = solutions.get(i);
			if (isTabu(s)){
				removed.add(s);
				solutions.remove(i);
			} else {
				i++;
			}
		}
		
		System.out.println(String.format("Removed %d tabu solutions.", removed.size()));
		
		return removed;
	}
	
	/**
	 * Returns true if the informed solution is a tabu solution.
	 */
	public abstract boolean isTabu(Solution s);
	
	/**
	 * Marks the solution or his features as tabu.
	 */
	public abstract void markAsTabu(Solution s);
}
