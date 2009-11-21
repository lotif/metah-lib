package br.unifor.metahlib.problems.tsp.ga;

import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;

/**
 * Implements the CX crossover operator.
 */
public class CycleCrossover extends CrossoverOperator {

	private void fillChild(Object[] child, Object[] p1, Object[] p2){
		Object value = p1[0];
		int i = 0;
		while (child[i] == null){
			child[i] = value;
			value = p2[i];
			i = indexOf(value, p1);
		}
		
		for (i = 0; i < child.length; ++i){
			if (child[i] == null){
				child[i] = p2[i];
			}
		}
	}

	@Override
	public Object[][] crossover(Object[] g1, Object[] g2) {
		assert(g1.length == g2.length);
		int len = g1.length;
		
		Object[] c1 = new Object[len];
		Object[] c2 = new Object[len];
		
		fillChild(c1, g1, g2);
		fillChild(c2, g2, g1);
		
		Object[][] children = new Object[][]{c1, c2};

		return children;
	}
}
