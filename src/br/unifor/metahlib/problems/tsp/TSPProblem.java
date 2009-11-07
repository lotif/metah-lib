package br.unifor.metahlib.problems.tsp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * Defines an instance of a Traveling Salesman Problem.
 */
public class TSPProblem extends Problem {
	
	/**
	 * TSP dataSet with cities distances.
	 */
	private TSPDataSet dataSet;

	/**
	 * Constructs a new TSPProblem instance.
	 * @param file TSPlib file
	 * @param neighborhoodStructure object responsible to create the neighbors of a solution
	 * @throws IOException
	 * @throws EdgeWeightTypeNotSupported
	 */
	public TSPProblem(File file, NeighborhoodStructure neighborhoodStructure) throws IOException, EdgeWeightTypeNotSupported {
		super();
		dataSet = new TSPDataSet(file);
		setCostEvaluator(new TourCostEvaluator(dataSet));
		setNeighborhoodStructure(neighborhoodStructure);
	}

	/**
	 * Creates a new random solution.
	 * @return new random solution.
	 */
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
