package br.unifor.metahlib.metaheuristics.pso.neighborhood;

import br.unifor.metahlib.metaheuristics.pso.NeighborhoodTopology;
import br.unifor.metahlib.metaheuristics.pso.Particle;

/**
 * Implements a star topology, known as gbest.
 */
public class GlobalBest extends NeighborhoodTopology{

	/**
	 * Binds each particle to all other particles.
	 * @param particles all particles of a swarm
	 */
	@Override
	public void bind(Particle[] particles) {
        Particle p, n;
        Particle[] neighbors = new Particle[particles.length - 1];
        int idx;
        for ( int i = 0; i < particles.length; ++i ){
            p = particles[i];
            idx = 0;
            for ( int j = 0; j < particles.length; ++j ){
                n = particles[j];
                if ( p != n ){
                	neighbors[idx] = n;
                    idx++;
                }
            }
            assert( idx == neighbors.length);

            p.setNeighbors(neighbors.clone());
        }
	}

}
