package br.unifor.metahlib.problems.tsp.neighborhood;

import java.util.ArrayList;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Solution;

/**
 * An implementation of a simple neighborhood structure that swap cities in a tour. 
 */
public class SwapCities extends NeighborhoodStructure {

	@Override
	public Solution getRandomNeighbor(Solution solution) {
		Solution s = solution.duplicate();
		Object[] tour = s.getValues();
		
		int i = random.nextInt(tour.length);
		int j = random.nextInt(tour.length);
		
		Object aux = tour[i];
		tour[i] = tour[j];
		tour[j] = aux;
		
		s.setValues(tour);
		
		return s;
	}

	@Override
	public ArrayList<Solution> getAllNeighbors(Solution solution) {
		ArrayList<Solution> list = new ArrayList<Solution>();
		int len = solution.getValues().length;
		for (int i = 0; i < len; ++i){
			for (int j = i+1; j < len; ++j){
				Solution s = solution.duplicate();
				Object[] tour = s.getValues();
				Object aux = tour[i];
				tour[i] = tour[j];
				tour[j] = aux;
				s.setValues(tour);
				list.add(s);
			}
		}
		
		return list;
	}
}
