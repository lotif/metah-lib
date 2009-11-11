package br.unifor.metahlib.metaheuristics.pso;

/**
 * Implements a topology of particle swarm.
 */
public abstract class NeighborhoodTopology {
	
	/**
	 * Binds the particles to your neighbors.
	 * @param particles all particles of a swarm
	 */
    public abstract void bind(Particle[] particles);
}
