package br.unifor.metahlib.problems.tsp.ga;

import br.unifor.metahlib.metaheuristics.ga.MutationOperator;

/**
 * Mutates the chain of genes exchanging one gene value for another.
 */
public class ExchangeMutationOperator extends MutationOperator {

	@Override
	public boolean mutate(Object[] genes, double mutationProbability) {
		boolean changed = false;
		for (int i = 0; i < genes.length; ++i){
			if (random.nextDouble() < mutationProbability){
				int j = random.nextInt(genes.length);
				Object value = genes[j];
				genes[j] = genes[i];
				genes[i] = value;
				
				changed = true;
			}
		}
			
		return changed;
	}
}
