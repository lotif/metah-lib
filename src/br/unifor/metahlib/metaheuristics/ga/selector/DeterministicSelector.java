package br.unifor.metahlib.metaheuristics.ga.selector;

import java.util.Arrays;

import br.unifor.metahlib.metaheuristics.ga.Individual;
import br.unifor.metahlib.metaheuristics.ga.Selector;

/**
 * Always selects the best individuals of a population.
 */
public class DeterministicSelector extends Selector {

	@Override
	public Individual[] select(Individual[] individuals, int quantity) {
		individuals = individuals.clone();
        Arrays.sort(individuals);
        Individual[] selection = new Individual[quantity];
        for (int i = 0; i < selection.length; ++i){
        	selection[i] = individuals[individuals.length - i - 1];
        }

        return selection;
	}
}
