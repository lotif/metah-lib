package br.unifor.metahlib.problems.tsp;

/**
 * Exception that occurs when the TSPLib file has a not supported EdgeWeightType format. 
 */
public class EdgeWeightTypeNotSupported extends Exception {
	
	/**
	 * Exception constructor.
	 * @param edgeWeightType id of not supported format
	 */
	public EdgeWeightTypeNotSupported(String edgeWeightType){
		super("Edged weight type \"" + edgeWeightType + "\" not supported.");
	}
	
	private static final long serialVersionUID = -8214192469053905959L;
}
