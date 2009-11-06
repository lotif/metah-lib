package br.unifor.metahlib.problems.tsp;

public class EdgeWeightTypeNotSupported extends Exception {
	
	public EdgeWeightTypeNotSupported(String edgeWeightType){
		super("Edged weight type \"" + edgeWeightType + "\" not supported.");
	}
	
	private static final long serialVersionUID = -8214192469053905959L;
}
