package br.unifor.metahlib.problems.tsp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public class TSPProblem extends Problem {
	
	private TSPDataSet dataSet;

	public TSPProblem(File file, NeighborhoodStructure neighborhoodStructure) throws IOException, EdgeWeightTypeNotSupported {
		super();
		dataSet = new TSPDataSet(file);
		setCostEvaluator(new TourCostEvaluator(dataSet));
		setNeighborhoodStructure(neighborhoodStructure);
	}

	@Override
	public Solution newRandomSolution() {
		Solution s = new Solution(this);
		Object[] tour = new Object[dataSet.getDimension()];
		
		ArrayList<Integer> citiesLeft = new ArrayList<Integer>();
		for(int i = 1; i <= tour.length; i++){
			citiesLeft.add(i);
		}
		
		for(int i = 0; i < tour.length; i++){
			int idx = random.nextInt(citiesLeft.size());
			tour[i] = citiesLeft.remove(idx);
		}
		s.setValues(tour);
		
		return s;
	}
}
