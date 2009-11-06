package br.unifor.metahlib.tsp.neighborhood;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.NeighborhoodStructure;

/**
 * An implementation of the 2-opt TSP neighborhood structure 
 * 
 * @author marcelo lotif
 *
 */
public class TwoOpt extends NeighborhoodStructure {
	
	/**
	 * Edge removed #1
	 */
	private int edge1;
	
	/**
	 * Edge removed #2
	 */
	private int edge2;
	
	/**
	 * This method implements the 2-opt operation.
	 * The removed edges are randomly generated.
	 * 
	 * @param parents the list of parents. For the 2-opt operation, only one parent is needed.
	 * @return the child route.
	 */
	@Override
	public List<double[]> getNeighbours(List<double[]> parents) {
		return getNeighbours(parents, -1, -1);
	}

	/**
	 * This method implements the 2-opt with fixed edge removal.
	 * For random generation of any of the two edges, one must assign it with a negative number.
	 * 
	 * 
	 * @param parents the list of parents. For the 2-opt operation, only one parent is needed.
	 * @param k1 the first edge to be removed. If it is a negative number, a random number will be assigned.
	 * @param k2 the second edge to be removed. If it is a negative number, a random number will be assigned.
	 * @return the child route.
	 */
	public List<double[]> getNeighbours(List<double[]> parents, int k1, int k2) {
		
		Random r = new Random();
		
		double[] p = parents.get(0);
		
		do {
			edge1 = k1 < 0 ? r.nextInt(p.length) : k1;
			edge2 = k2 < 0 ? r.nextInt(p.length) : k2;
	
			if(edge1 > edge2){
				int aux = edge1;
				edge1 = edge2;
				edge2 = aux;
			}
		} while (edge1 == edge2);
		
		double[] child = new double[p.length];
		
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
		
		List<double[]> l = new ArrayList<double[]>();
		l.add(child);
		
		return l;
	}

	public int getEdge1() {
		return edge1;
	}

	public void setEdge1(int edge1) {
		this.edge1 = edge1;
	}

	public int getEdge2() {
		return edge2;
	}

	public void setEdge2(int edge2) {
		this.edge2 = edge2;
	}
}
