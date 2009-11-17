package br.unifor.metahlib.problems.continuous.ga;

import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;

/**
 * One point crossover operator.
 */
public class OnePointCrossover extends CrossoverOperator {

	@Override
	public Object[][] crossover(Object[] g1, Object[] g2) {
        assert( g1.length == g2.length );
        int len = g1.length;
        Object[] child1 = new Object[len];
        Object[] child2 = new Object[len];

        int r = random.nextInt(len);

        for ( int i = 0; i < r; ++i ){
        	child1[i] = g1[i];
        	child2[i] = g2[i];
        }

        for ( int i = r; i < len; ++i ){
        	child1[i] = g2[i];
        	child2[i] = g1[i];
        }

        Object[][] children = new Object[][] { child1, child2 };

        return children;

	}
}
