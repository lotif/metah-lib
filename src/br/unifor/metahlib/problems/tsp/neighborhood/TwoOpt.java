package br.unifor.metahlib.problems.tsp.neighborhood;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Solution;

/**
 * An implementation of the 2-opt TSP neighborhood structure 
 * 
 * @author marcelo lotif
 *
 */
public class TwoOpt extends NeighborhoodStructure {
	
    public static class TwoOptResult {
    	/**
		 * Removed edges.
		 */
		public int[] removedEdges;
		
		/**
		 * Generated neighbor.
		 */
		public Object[] neighbor;
		
		public TwoOptResult(Object[] neighbor, int[] removedEdges){
			this.neighbor = neighbor;
			this.removedEdges = removedEdges;
		}
    }
	
	/**
	 * This method implements the 2-opt with fixed edge removal.
	 * For random generation of any of the two edges, one must assign it with a negative number.
	 * @param parents the list of parents. For the 2-opt operation, only one parent is needed.
	 * @param k1 the first edge to be removed. If it is a negative number, a random number will be assigned.
	 * @param k2 the second edge to be removed. If it is a negative number, a random number will be assigned.
	 * @return the child route.
	 */
    public TwoOptResult twoOpt(Object[] values, int k1, int k2){
		Object[] p = values;
		int edge1;
		int edge2;
		
		do {
			edge1 = k1 < 0 ? random.nextInt(p.length) : k1;
			edge2 = k2 < 0 ? random.nextInt(p.length) : k2;
	
			if(edge1 > edge2){
				int aux = edge1;
				edge1 = edge2;
				edge2 = aux;
			}
		} while (edge1 == edge2);
		
		Object[] child = new Object[p.length];
		int i = 0;
		while(i < edge1){
			child[i] = p[i];
			i++;
		}
		
		int j = edge2;
		while( i < edge2 + 1){
			child[i] = p[j];
			i++;
			j--;
		}
		
		while(i < p.length){
			child[i] = p[i];
			i++;
		}
		
		return new TwoOptResult(child, new int[]{edge1, edge2});
    }
    
	@Override
	public Solution getRandomNeighbor(Solution solution) {
		Solution s = solution.duplicate();
		TwoOptResult twoOptResult = twoOpt(s.getValues(), -1, -1);
		s.setValues(twoOptResult.neighbor);
		return s;
	}
}
