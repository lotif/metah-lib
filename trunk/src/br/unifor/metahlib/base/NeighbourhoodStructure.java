package br.unifor.metahlib.base;

import java.util.List;

public interface NeighbourhoodStructure {

	public List<double[]> getNeighbours(List<double[]> parents);
	
}
