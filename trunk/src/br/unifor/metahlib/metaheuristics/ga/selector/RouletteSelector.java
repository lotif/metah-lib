package br.unifor.metahlib.metaheuristics.ga.selector;

import br.unifor.metahlib.metaheuristics.ga.Individual;
import br.unifor.metahlib.metaheuristics.ga.Selector;

/**
 * Selects the individuals using a stochastic fitness proportionate strategy, allowing  
 * some weak individuals survive.
 */
public class RouletteSelector extends Selector {

	/**
	 * Returns the lower fitness of individuals.
	 */
	private double getLowerFitness(Individual[] individuals){
        double lower = Double.MAX_VALUE;
        for (int i = 0; i < individuals.length; ++i ){
        	lower = Math.min(lower, individuals[i].getFitness());
        }
        return lower;
    }
	
	@Override
	public Individual[] select(Individual[] individuals, int quantity) {
        double base = getLowerFitness(individuals);
        if (base <= 0){
            base = (-1 * base) + 1;
        }

        double sum = 0.0;
        for (int i = 0; i < individuals.length; ++i){
            sum+= base + individuals[i].getFitness();
        }

        double[] probabilities = new double[individuals.length];
        for (int i = 0; i < probabilities.length; ++i){
        	probabilities[i] = (base + individuals[i].getFitness()) / sum;
        }

        Individual[] selection = new Individual[quantity];
        for (int i = 0; i < selection.length; ++i){
            double prob = random.nextDouble();
            int j = -1;
            while (prob > 0){
                j++;
                prob-= probabilities[j];
            }
            selection[i] = individuals[j];
        }

        return selection;
	}
}
