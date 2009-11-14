package br.unifor.metahlib.metaheuristics.ga;

import java.util.ArrayList;

/**
 * Groups individuals in Genetic Algorithm.
 */
public class Population {
	
	/**
	 * Individuals of this population.
	 */
	private ArrayList<Individual> individuals = new ArrayList<Individual>();
	
    /**
     * Creates a array with the individuals of this population.
     * @return array of Individual
     */
	public Individual[] getArray(){
        Individual[] ar = new Individual[individuals.size()];
        for (int i = 0; i < ar.length; ++i){
            ar[i] = individuals.get(i);
        }

        return ar;
    }

    /**
     * Adds the individual.
     * @param individual
     */
	public void add(Individual individual){
        individuals.add(individual);
	}

    /**
     * Adds all individuals.
     * @param invididuals
     */
	public void addAll(Individual[] individuals){
        for (int i = 0; i < individuals.length; ++i ){
            this.individuals.add(individuals[i]);
        }
    }
	
    /**
     * Returns the population size.
     * @return population size
     */
	public int getSize(){
        return individuals.size();
    }
	

    /**
     * Returns a individual of population.
     * @param idx index of individual
     * @return individual
     */
	public Individual get(int idx){
        return individuals.get(idx);
    }
	
    /**
     * Returns the individual with best fitness.
     * @return individual
     */
	public Individual getBestIndividual(){
		if (individuals.size() > 0){
	        Individual best = individuals.get(0);
	        double bestFitness = best.getFitness();
	        Individual ind;
	        double fitness;
	        for (int i = 1; i < individuals.size(); ++i){
	            ind = individuals.get(i);
	        	fitness = ind.getFitness();
	            if (fitness > bestFitness){
	            	bestFitness = fitness;
	                best = ind;
	            }
	        }
	
	        return best;
	        
		} else {
			return null;
		}
    }

    /**
	public double getSomatorioValor(){
        double soma = 0.0;
        for ( int i = 0; i < individuos.size(); ++i ){
            soma+= get(i).getValor();
        }

        return soma;
    }

    public double getMediaValor(){
        return getSomatorioValor() / getTamanho();
    }

    */
}
