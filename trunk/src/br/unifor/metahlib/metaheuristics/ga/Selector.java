package br.unifor.metahlib.metaheuristics.ga;

public abstract class Selector {
	
	/**
	 * Selects the informed quantity of individuals using a strategy.
	 * @param individuals
	 * @param quantity
	 * @return selected individuals
	 */
	public abstract Individual[] select(Individual[] individuals, int quantity);

	@Override
	public String toString(){
		return this.getClass().getName();		
	}
}
