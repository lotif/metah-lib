package br.unifor.metahlib.metaheuristics.ga;

import java.util.Random;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public class Individual {
	
	/**
	 * Problem to be optimized.
	 */
	Problem problem;
	
	/**
	 * Problem random number generator.
	 */
	Random random;
	
	/**
	 * 
	 */
	Solution solution;
	
	/**
	 * Reproduces the individuals creating two new individuals.
	 * @param individual second parent of reproduction
	 * @param crossoverProbability probability that crossover occurs
	 * @param operator object that will crossover the genes of individuals
	 * @return two new individuals
	 */
	public Individual[] reproduce(Individual individual, double crossoverProbability, Crossover operator){
		Individual child1 = duplicate();
		Individual child2 = individual.duplicate();

        if (random.nextDouble() < crossoverProbability){
            Object[][] newGenes = operator.crossover(child1.getGenes(), child2.getGenes());
            assert(newGenes.length == 2);
            child1.setGenes(newGenes[0]);
            child2.setGenes(newGenes[1]);
        }

        Individual[] children = new Individual[] { child1, child2 };

        return children;
    }

    /**
     * Mutates the individual. 
     * @param mutationProbability probability that mutation occurs
     * @param operator object that will mutate the individual
     */
	public void mutate(double mutationProbability, Mutation operator){
    	Object[] genes = getGenes();
        if (operator.mutate(genes, mutationProbability)){
            setGenes(genes);
        }
    }

    /**
     * Returns the fitness value of the individual.
     * @return fitness
     */
    public double getFitness(){
        return -1 * solution.getCost();
    }

    /**
     * Returns the genes of individual.
     * @return genes
     */
    public Object[] getGenes(){
        return solution.getValues();
    }
    
    /**
     * Informs the new genetic information of individual.
     * @param genes
     */
    public void setGenes(Object[] genes){
        solution.setValues(genes);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return duplicate();
    }
    
    /**
     * Creates a clone of this individual.
     * @return clone of individual
     */
    public Individual duplicate(){
    	Individual ind = new Individual(problem);
    	ind.solution = solution.duplicate();
    	return ind;
    }

    public Individual(Problem problem){
        this.problem = problem;
        this.random = problem.getRandom();
        this.solution = problem.newRandomSolution();
    }
}
