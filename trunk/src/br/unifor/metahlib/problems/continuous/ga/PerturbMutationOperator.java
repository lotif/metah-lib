package br.unifor.metahlib.problems.continuous.ga;

import br.unifor.metahlib.metaheuristics.ga.MutationOperator;
import br.unifor.metahlib.problems.continuous.Perturber;

/**
 * Mutation operator that uses a Perturber instance to modify the genes.
 */
public class PerturbMutationOperator extends MutationOperator {
	
	private Perturber perturber;

	/**
	 * Class constructor.
	 * @param perturber
	 */
	public PerturbMutationOperator(Perturber perturber){
		super();
		this.perturber = perturber;
    }

	@Override
	public boolean mutate(Object[] genes, double mutationProbability) {
		boolean changed = false;
		double value;
        for (int i = 0; i < genes.length; ++i){
            if (random.nextDouble() < mutationProbability){
            	value = perturber.perturb(i, (Double) genes[i]);
                genes[i] = value;
                changed = true;
            }
        }
        
        return changed;
	}
}
