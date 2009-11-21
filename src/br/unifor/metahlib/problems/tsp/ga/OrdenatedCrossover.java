package br.unifor.metahlib.problems.tsp.ga;

import java.util.ArrayList;

import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;

/**
 * Implements the OX crossover operator.
 */
public class OrdenatedCrossover extends CrossoverOperator {
	
	private void fillChild(Object[] child, Object[] p1, Object[] p2, int startIdx, int endIdx){
		ArrayList<Object> list = new ArrayList<Object>(child.length);
		
		int i = endIdx;
		int count = 0;
		while (count < child.length){
			i++;
			if (i == child.length){
				i = 0;
			}
			list.add(p2[i]);
			count++;
		}
			
		for (i = startIdx; i <= endIdx; ++i){
			list.remove(list.indexOf(p1[i]));
		}
		
		i = endIdx; 
		for (Object value : list){
			i++;
			if (i == child.length){
				i = 0;
			}
			
			child[i] = value;
		}
	}

	@Override
	public Object[][] crossover(Object[] g1, Object[] g2) {
		assert(g1.length == g2.length);
		int len = g1.length;
		
		Object[] c1 = new Object[len];
		Object[] c2 = new Object[len];
		
		int p1 = random.nextInt(len);
		int p2 = random.nextInt(len);
		for (int i = p1; i <= p2; ++i){
			c1[i] = g1[i];
			c2[i] = g2[i];
		}
		
		fillChild(c1, g1, g2, p1, p2);
		fillChild(c2, g2, g1, p1, p2);
		
		Object[][] children = new Object[][]{c1, c2};

		return children;
	}
}
