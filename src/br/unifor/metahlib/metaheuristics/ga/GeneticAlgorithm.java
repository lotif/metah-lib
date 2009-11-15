package br.unifor.metahlib.metaheuristics.ga;

import br.unifor.metahlib.base.PopulationHeuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * The Genetic Algorithm optimization method.
 */
public class GeneticAlgorithm extends PopulationHeuristic {
	
    /**
     * Probability of an individual be chosen for reproduction.
     */
	private double crossoverProbability = 0.75;
    
    /**
     * Probability of an individual suffers a mutation in a generation
     */
	private double mutationProbability = 0.10;
    
    /**
     * Responsible to select the individuals for reproducing.
     */
	private Selector reproductionSelector;
    
    /**
     * Responsible to select the survived individuals of a generation.
     */
	private Selector surviveSelector;
    
    /**
     * Responsible to merge two individual genes.
     */
	private CrossoverOperator crossoverOperator;
	
    /**
     * Responsible to mutate the "genes" of an individual.
     */
	private MutationOperator mutationOperator;	
	
	public GeneticAlgorithm(Problem problem, CrossoverOperator crossoverOperator, MutationOperator mutationOperator,
			Selector reproductionSelector, Selector surviveSelector) {
		super(problem);
		setCrossoverOperator(crossoverOperator);
		setMutationOperator(mutationOperator);
		setReproductionSelector(reproductionSelector);
		setSurviveSelector(surviveSelector);
	}

    /**
     * Creates a new random population.
     * @return population
     */
	protected Population newPopulation() {
    	Population pop = new Population();
        for (int i = 0; i < getPopulationSize(); ++i ){
        	pop.add(new Individual(problem));
        }
        return pop;
    }
	
    /**
     * Reproduces the individuals of population. The new individuals will be added to
     * informed population.
     * @param population
     * @return new individuals
     */
	protected Population reproduce(Population population){
        Population newPopulation = new Population();
        Individual[] selection = getReproductionSelector().select(population.getArray(),
        		population.getSize());

        for (int i = 0; i < selection.length; i+=2){
        	Individual[] children = selection[i].reproduce(selection[i+1],
            		getCrossoverProbability(), crossoverOperator);
            
            population.addAll(children);
            newPopulation.addAll(children);
        }

        return newPopulation;
    }
	
    /**
     * Mutates the individuals of population with probability determined by 
     * property "mutationProbability". 
     * @param population
     */
	protected void mutate(Population population){
        for ( int i = 0; i < population.getSize(); ++i ){
        	population.get(i).mutate(getMutationProbability(), getMutationOperator());
        }
    }
	
    /**
     * Selects the individuals that will form the next generation.
     * @param population current generation
     * @return next generation
     */
	protected Population selectForNextGeneration(Population population){
        Individual[] selection = getSurviveSelector().select(population.getArray(), getPopulationSize());
        Population nextGeneration = new Population();
        for (int i = 0; i < selection.length; ++i ){
        	nextGeneration.add(selection[i].duplicate());
        }

        return nextGeneration;
    }
	
	@Override
	public Solution execute() {
        Population p = newPopulation();
        Population children;

        Individual individual = p.getBestIndividual();
        Individual bestIndividual = individual;

        //r.evolucaoMelhor.add(individuo.getValor());
        //r.evolucaoMedia.add(p.getMediaValor());
        //r.xInicial = melhorIndividuo.getGenes();

        int it = 0;
        while (it < max_it){
            children = reproduce(p);
            mutate(children);
            p = selectForNextGeneration(p);

            individual = p.getBestIndividual();
            if ( individual.getFitness() > bestIndividual.getFitness() ){
                //r.qtdIteracoesAteMelhorSolucao = t + 1;
                //r.qtdAvaliacoesAteMelhorSolucao = a + tamanhoPopulacao;
            	lastBestFoundOn = it;
            	bestIndividual = individual.duplicate();
            }

            //r.evolucaoMelhor.add(individuo.getValor());
            //r.evolucaoMedia.add(p.getMediaValor());

            it++;
        }

        //r.x = melhorIndividuo.getGenes();
        //r.eval_x = avaliarX( melhorIndividuo.getGenes());
        //r.qtdIteracoes = t;
        
        return bestIndividual.getSolution();
	}
	
	@Override
	public String toString(){
        return String.format( "% (IndividualSize=%d,PopulationSize=%d,pc=%.2f," +
        		"pm=%.2f,maxGenerations=%d,crossoverOperator=%s,mutationOperator=%s," +
        		"reproductionSelector=%s,surviveSelector=%s)",
        		this.getClass().getName(),
        		problem.getDimension(), getPopulationSize(), getCrossoverProbability(), 
        		getMutationProbability(), max_it, getCrossoverOperator().toString(),
        		getMutationOperator().toString(), getReproductionSelector().toString(),
        		getSurviveSelector().toString());
	}
	
    /**
     * Sets the object responsible to merge two individual genes.
     */
	public void setCrossoverOperator(CrossoverOperator crossoverOperator) {
		this.crossoverOperator = crossoverOperator;
		this.crossoverOperator.setRandom(problem.getRandom());
	}

    /**
     * Returns the object responsible to merge two individual genes.
     */
	public CrossoverOperator getCrossoverOperator() {
		return crossoverOperator;
	}

	public void setCrossoverProbability(double crossoverProbability) {
		this.crossoverProbability = crossoverProbability;
	}

	public double getCrossoverProbability() {
		return crossoverProbability;
	}

	public void setMutationProbability(double mutationProbability) {
		this.mutationProbability = mutationProbability;
	}

	public double getMutationProbability() {
		return mutationProbability;
	}

	public void setReproductionSelector(Selector reproductionSelector) {
		this.reproductionSelector = reproductionSelector;
		this.reproductionSelector.setRandom(problem.getRandom());
	}

	public Selector getReproductionSelector() {
		return reproductionSelector;
	}

	public void setSurviveSelector(Selector surviveSelector) {
		this.surviveSelector = surviveSelector;
		this.surviveSelector.setRandom(problem.getRandom());
	}

	public Selector getSurviveSelector() {
		return surviveSelector;
	}

	public void setMutationOperator(MutationOperator mutationOperator) {
		this.mutationOperator = mutationOperator;
		this.mutationOperator.setRandom(problem.getRandom());
	}

	public MutationOperator getMutationOperator() {
		return mutationOperator;
	}
}
