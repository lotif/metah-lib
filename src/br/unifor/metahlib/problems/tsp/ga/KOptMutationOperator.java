package br.unifor.metahlib.problems.tsp.ga;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.metaheuristics.ga.MutationOperator;
import br.unifor.metahlib.problems.tsp.neighborhood.KOpt;

public class KOptMutationOperator extends MutationOperator {
	
	KOpt kOpt;
	
	public KOptMutationOperator(Problem problem, int k){
		kOpt = new KOpt(problem, k);
	}

	@Override
	public boolean mutate(Object[] genes, double mutationProbability) {
		if (random.nextDouble() < mutationProbability){
			Object[] result = kOpt.getRandomNeighbor(genes);
			for (int i = 0; i < result.length; ++i){
				genes[i] = result[i];
			}
			return true;
			
		} else {
			return false;
		}
	}

}
