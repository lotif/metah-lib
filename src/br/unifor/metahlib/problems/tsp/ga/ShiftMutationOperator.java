package br.unifor.metahlib.problems.tsp.ga;

import br.unifor.metahlib.metaheuristics.ga.MutationOperator;

public class ShiftMutationOperator extends MutationOperator {

	@Override
	public boolean mutate(Object[] genes, double mutationProbability) {
		boolean changed = false;
		for (int i = 0; i < genes.length; ++i){
			if (random.nextDouble() < mutationProbability){
				int other = random.nextInt(genes.length);
				Object value = genes[i];
				if (i > other){
					System.arraycopy(genes, other, genes, other+1, i - other);
				} else {
					System.arraycopy(genes, i+1, genes, i, other - i);
				}
				genes[other] = value;
				
				changed = true;
			}
		}
			
		return changed;
	}

}
