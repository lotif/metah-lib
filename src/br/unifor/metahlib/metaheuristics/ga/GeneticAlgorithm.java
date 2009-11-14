package br.unifor.metahlib.metaheuristics.ga;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public class GeneticAlgorithm extends Heuristic {
	
    /**
     * Quantity of individuals.
     */
	private int populationSize = 50;
    
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
	private Crossover crossoverOperator;
	
	/**
	 * Max quantity of generations.
	 */
	private int max_it;
    
    /**
     * Responsible to mutate the "genes" of an individual.
     */
	public Mutation mutationOperator;	

	public GeneticAlgorithm(Problem problem) {
		super(problem);
	}

	@Override
	public Solution execute() {
		return null;
	}
	
	@Override
	public String toString(){
        return String.format( "% (IndividualSize=%d,PopulationSize=%d,pc=%.2f," +
        		"pm=%.2f,maxGenerations=%d,crossoverOperator=%s,mutationOperator=%s," +
        		"reproductionSelector=%s,surviveSelector=%s)",
        		this.getClass().getName(),
        		problem.getDimension(), populationSize, crossoverProbability, 
        		mutationProbability, max_it, getCrossoverOperator().toString(),
        		mutationOperator.toString(), reproductionSelector.toString(),
        		surviveSelector.toString());
	}
	
    /**
     * Creates a new random population.
     * @return population
     */
	protected Population newPopulation() {
    	Population pop = new Population();
        for (int i = 0; i < populationSize; ++i ){
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
        Individual[] selection = reproductionSelector.select(population.getArray(),
        		population.getSize());

        for (int i = 0; i < selection.length; i+=2){
        	Individual[] children = selection[i].reproduce(selection[i+1],
            		crossoverProbability, crossoverOperator);
            
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
        	population.get(i).mutate(mutationProbability, mutationOperator);
        }
    }

    /**
     * Sets the object responsible to merge two individual genes.
     */
	public void setCrossoverOperator(Crossover crossoverOperator) {
		this.crossoverOperator = crossoverOperator;
		this.crossoverOperator.setRandom(problem.getRandom());
	}

    /**
     * Returns the object responsible to merge two individual genes.
     */
	public Crossover getCrossoverOperator() {
		return crossoverOperator;
	}
	
/*
 * 
 *

    


    protected Populacao selecionar( Populacao populacao ) throws CloneNotSupportedException{
        Individuo[] selecao = seletorNovaGeracao.selecionar(populacao.getArray(), tamanhoPopulacao);

        Populacao novaGeracao = new Populacao();
        for ( int i = 0; i < selecao.length; ++i ){
            novaGeracao.adicionar( (Individuo) (selecao[i].clone()));
        }

        return novaGeracao;
    }

    @Override
    protected void executa(ResultadoBusca r) throws Exception {
        Populacao p = iniciar();
        Populacao filhos;

        Individuo individuo = p.getMelhorIndividuo();
        Individuo melhorIndividuo = individuo;

        r.evolucaoMelhor.add(individuo.getValor());
        r.evolucaoMedia.add(p.getMediaValor());

        r.xInicial = melhorIndividuo.getGenes();

        int t = 0;
        int a = 0;
        while ( t < qtdMaxIteracoes && a < qtdMaxAvaliacoes ){
            filhos = reproduzir(p);
            mutar(filhos);
            p = selecionar(p);

            individuo = p.getMelhorIndividuo();
            if ( individuo.getFitness() > melhorIndividuo.getFitness() ){
                r.qtdIteracoesAteMelhorSolucao = t + 1;
                r.qtdAvaliacoesAteMelhorSolucao = a + tamanhoPopulacao;
                melhorIndividuo = (Individuo) individuo.clone();
                assert( melhorIndividuo.getFitness() == individuo.getFitness());
                assert( melhorIndividuo != individuo );
            }

            r.evolucaoMelhor.add(individuo.getValor());
            r.evolucaoMedia.add(p.getMediaValor());

            t++;
            a+= tamanhoPopulacao;
        }

        r.x = melhorIndividuo.getGenes();
        r.eval_x = avaliarX( melhorIndividuo.getGenes());
        r.qtdIteracoes = t;
        r.qtdAvaliacoes = a;
    }

    public AlgoritmoGenetico( FuncaoObjetivo funcao ) throws Exception {
        super(funcao);
        this.tipo = TipoOtimizacao.MAXIMIZACAO;
        this.qtdMaxIteracoes = 100;
    }
 * 
 */
	
	
}
