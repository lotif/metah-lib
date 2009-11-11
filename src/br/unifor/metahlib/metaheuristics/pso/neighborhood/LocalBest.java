package br.unifor.metahlib.metaheuristics.pso.neighborhood;

import br.unifor.metahlib.metaheuristics.pso.NeighborhoodTopology;
import br.unifor.metahlib.metaheuristics.pso.Particle;

/**
 * Implements a ring topology, known as lbest.
 */
public class LocalBest extends NeighborhoodTopology {

	/**
	 * Binds each particle to two other particles.
	 * @param particles all particles of a swarm
	 */
	@Override
	public void bind(Particle[] particles) {
        Particle p;
        Particle n1;
        Particle n2;
        int len = particles.length;
        for (int i = 0; i < len; ++i){
            p = particles[i];
            n1 = i > 0 ? particles[i-1] : particles[len-1];
            n2 = i+1 < len ? particles[i+1] : particles[0];
            
            p.setNeighbors(new Particle[] { n1, n2 });
        }
	}
	
}
