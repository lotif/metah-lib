package br.unifor.metahlib.problems.continuous.ga;

import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;

/**
 * Two points crossover operator.
 */
public class TwoPointsCrossover extends CrossoverOperator {

	@Override
	public Object[][] crossover(Object[] g1, Object[] g2) {
        assert( g1.length == g2.length );
        int len = g1.length;
        Object[] child1 = new Object[len];
        Object[] child2 = new Object[len];

        int r1;
        int r2;

        switch (len){
            case 1:
                r1 = 0;
                r2 = 1;
                break;

            default:
                r1 = random.nextInt(len-1);
                r2 = random.nextInt(len-r1) + r1;
        }

        for (int i = 0; i < r1; ++i){
        	child1[i] = g1[i];
        	child2[i] = g2[i];
        }

        for (int i = r1; i < r2; ++i){
        	child1[i] = g2[i];
        	child2[i] = g1[i];
        }

        for (int i = r2; i < len; ++i){
        	child1[i] = g1[i];
        	child2[i] = g2[i];
        }

        Object[][] children = new Object[][] {child1, child2};

        return children;
	}
}
