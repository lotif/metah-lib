package br.unifor.metahlib.metaheuristics.grasp.tsp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.metaheuristics.grasp.GreedyRandomizedConstructor;
import br.unifor.metahlib.metaheuristics.grasp.SolutionElement;
import br.unifor.metahlib.problems.tsp.TSPProblem;

public class TSPGreedyRandomizedContructor extends GreedyRandomizedConstructor {

	public TSPGreedyRandomizedContructor(Problem problem, double alpha) {
		super(problem, alpha);
	}

	@Override
	protected List<SolutionElement> buildRestrictedCandidateList(Object[] values) {
		
		for(int k = 0; k < values.length; k++){
			if(values[k] != null){
				values[k] = ((Integer)values[k]) - 1;
			} else { 
				break;
			}
		}
		
		List<SolutionElement> elements = new ArrayList<SolutionElement>();
		int[][] distances = ((TSPProblem)problem).getDataSet().getDistances();
		
		List<Integer> invalidValues = new ArrayList<Integer>();
		for(int i = 0; i < values.length; i++){
			if(values[i] != null){
				invalidValues.add((Integer)values[i]);
			} else {
				break;
			}
		}
		
		int current;
		if(invalidValues.size() == 0){
			current = problem.getRandom().nextInt(problem.getDimension());
		} else {
			current = invalidValues.get(invalidValues.size() - 1);
		}
		
		if(invalidValues.size() == problem.getDimension() - 1){
			invalidValues.remove(((Object)current));
		}
		
		Collections.sort(invalidValues);
		
		Integer cmax = null, cmin = null;
		int kj = 0;
		int i = current;
		
		for(int j = 0; j < distances[i].length; j++){
			if(invalidValues.size() == 0 || kj >= invalidValues.size() || invalidValues.get(kj) != j){
				if(i != j){
					if(cmin == null || distances[i][j] < cmin){
						cmin = distances[i][j];
					}
					if(cmax == null || distances[i][j] > cmax){
						cmax = distances[i][j];
					}
				}
			} else {
				kj++;
			}
		}
		
		kj = 0;
		double threshold = cmin	+ alpha*(cmax - cmin);
		for(int j = 0; j < distances[i].length; j++){
			if(invalidValues.size() == 0 || kj >= invalidValues.size() || invalidValues.get(kj) != j){
				if(i != j){
					if(distances[i][j] <= threshold){
						elements.add(new TSPSolutionElement(distances[i][j], i, j));
					}
				}
			} else {
				kj++;
			}
		}
		
		for(int k = 0; k < values.length; k++){
			if(values[k] != null){
				values[k] = ((Integer)values[k]) + 1;
			} else { 
				break;
			}
		}
		
		for(SolutionElement t : elements){
			t.setValue(((Integer)t.getValue()) + 1);
		}
		
//		if(elements.size() == 0){
//			System.out.println("OPA!");
//		}
		
		return elements;
	}
	
}
