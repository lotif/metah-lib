package br.unifor.metahlib.problems.tsp.ga;

import br.unifor.metahlib.metaheuristics.ga.CrossoverOperator;

public class PartialMappedCrossover extends CrossoverOperator {
	
	/**
	 * Returns the index of the value in values array if exists. Otherwise, returns -1.
	 */
	protected int indexOf(Object value, Object[] values){
		int idx = -1;
		for(int i = 0; i < values.length; ++i){
			if (values[i] != null && values[i].equals(value)){
				idx = i;
				break;
			}
		}
		
		return idx;
	}
	
	private void fillChild(Object[] child, Object[] p1, Object[] p2){
		Object value;
		for (int i = 0; i < child.length; ++i){
			if (child[i] == null){
				value = p1[i];
				while (indexOf(value, child) >= 0){
					value = p2[indexOf(value, p1)];
				}
				child[i] = value;
			}
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
			c1[i] = g2[i];
			c2[i] = g1[i];
		}
		
		fillChild(c1, g1, g2);
		fillChild(c2, g2, g1);
		
		Object[][] children = new Object[][]{c1, c2};

		return children;
	}

}
