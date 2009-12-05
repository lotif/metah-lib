package br.unifor.metahlib.problems.tsp.ts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.ts.TabuList;

/**
 * An implementation of a Tabu List that prevents recently cities swaps. 
 */
public class SwapTabuList extends TabuList {
	
	/**
	 * List with the forbidden movements.
	 */
	private ArrayList<String> tabuMovements;
	
	/**
	 * Tabu list size.
	 */
	private int size;  
	
	public SwapTabuList(int size){
		this.tabuMovements = new ArrayList<String>();
		this.size = size;
		
	}
	
	/**
	 * Creates an id that represents the recently cities swaps of informed solution.
	 * @return an id
	 */
	private String formatMovementsId(Solution solution){
		Solution origin = solution.getOriginSolution();
		if (origin != null){
			String tag = "";
			Object[] tour = solution.getValues();
			List<Object> originTour = Arrays.asList(origin.getValues());
			for (int i = 0; i < tour.length; ++i){
				if (!tour[i].equals(originTour.get(i))){
					int j = originTour.indexOf(tour[i]);
					if (j > i){
						tag+= "," + i + "<->" + j;
					}
				}
			}
			
			return tag;
			
		} else {
			return null;
		}
	}

	@Override
	public boolean isTabu(Solution s) {
		String tag = formatMovementsId(s);
		boolean found = tabuMovements.indexOf(tag) >= 0;
		return found;
	}

	@Override
	public void markAsTabu(Solution s) {
		String tag = formatMovementsId(s);
		System.out.println("Added to tabu list: " + tag);
		tabuMovements.add(tag);
		if (tabuMovements.size() > size){
			String removedTag = tabuMovements.remove(0);
			System.out.println("Removed from tabu list:" + removedTag);
		}
	}

}
