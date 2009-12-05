package br.unifor.metahlib.problems.tsp.ts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.ts.TabuList;

/**
 * An implementation of a Tabu List that prevents changes in recently moved cities 
 * in a tour. 
 */
public class CityTabuList  extends TabuList {
	
	/**
	 * List with the forbidden movements.
	 */
	private ArrayList<String> tabuMovements;
	
	/**
	 * Tabu list size.
	 */
	private int size;  
	
	/**
	 * Class constructor.
	 * @param size tabu list size
	 */
	public CityTabuList(int size){
		this.tabuMovements = new ArrayList<String>();
		this.size = size;
		
	}
	
	/**
	 * Returns the cities that was recently moved in the tour.
	 */
	private ArrayList<Object> getChangedCities(Solution solution){
		Solution origin = solution.getOriginSolution();
		ArrayList<Object> changedCities = new ArrayList<Object>();
		if (origin != null){
			Object[] tour = solution.getValues();
			List<Object> originTour = Arrays.asList(origin.getValues());
			for (int i = 0; i < tour.length; ++i){
				if (!tour[i].equals(originTour.get(i))){
					changedCities.add(tour[i]);
				}
			}
		}
		
		return changedCities;
	}

	@Override
	public boolean isTabu(Solution s) {
		boolean found = false;
		ArrayList<Object> changedCities = getChangedCities(s);
		for (Object c: changedCities){
			found = tabuMovements.indexOf(c.toString()) >= 0;
			if (found){
				break;
			}
		}
		
		return found;
	}

	@Override
	public void markAsTabu(Solution s) {
		ArrayList<Object> changedCities = getChangedCities(s);
		for (Object c: changedCities){
			String id = c.toString();
			if (tabuMovements.indexOf(id) == -1){
				tabuMovements.add(id);
				System.out.println("Added to tabu list: " + id);
			}
		}

		assert(size > 0);
		while (tabuMovements.size() > size){
			String id = tabuMovements.remove(0);
			System.out.println("Removed from tabu list: " + id);
		}
	}

}
