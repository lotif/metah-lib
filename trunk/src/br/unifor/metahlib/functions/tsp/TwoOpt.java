package br.unifor.metahlib.functions.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.NeighbourhoodStructure;

/**
 * An implementation of the 2-opt TSP neighborhood structure 
 * 
 * @author marcelo lotif
 *
 */
public class TwoOpt implements NeighbourhoodStructure {
	
	@Override
	public List<double[]> getNeighbours(List<double[]> parents) {
		
		Random r = new Random();
		
		double[] p = parents.get(0);
		
		int k1, k2;
		do {
			k1 = r.nextInt(p.length);
			k2 = r.nextInt(p.length);
		
			if(k1 > k2){
				int aux = k1;
				k1 = k2;
				k2 = aux;
			}
		} while (k1 == k2);
		
		double[] child = new double[p.length];
		
		int i = 0;
		while(i < k1){
			child[i] = p[i];
			i++;
		}
		
		int j = k2;
		while( i < k2 + 1){
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

//MÉTODO ANTIGO - ERRADO
//	@Override
//	public List<double[]> getNeighbours(List<double[]> parents) {
//		
//		Random r = new Random();
//		
//		double[] p = parents.get(0);
//		
//		double[] parent = new double[p.length];
//		for(int i = 0; i < p.length; i++){
//			parent[i] = p[i];
//		}
//		
//		int k1 = r.nextInt(parent.length);
//		int k2 = r.nextInt(parent.length);
//		
//		double aux = parent[k1];
//		parent[k1] = parent[k2];
//		parent[k2] = aux;
//		
//		List<double[]> l = new ArrayList<double[]>();
//		l.add(parent);
//		
//		return l;
//	}
	
}
