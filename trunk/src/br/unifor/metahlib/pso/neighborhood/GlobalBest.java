package br.unifor.metahlib.pso.neighborhood;

import br.unifor.metahlib.pso.Neighborhood;
import br.unifor.metahlib.pso.Particle;

public class GlobalBest extends Neighborhood{

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
            assert(false); // TODO
            //p.setNeighbors(neighbors.clone());
        }
	}

}
