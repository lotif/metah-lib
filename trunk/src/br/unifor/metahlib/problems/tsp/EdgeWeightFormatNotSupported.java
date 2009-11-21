package br.unifor.metahlib.problems.tsp;

public class EdgeWeightFormatNotSupported extends Exception {

	/**
	 * Exception constructor.
	 * @param edgeWeightType id of not supported format
	 */
	public EdgeWeightFormatNotSupported(String edgeWeightFormat){
		super("Edged weight format \"" + edgeWeightFormat + "\" not supported.");
	}

	private static final long serialVersionUID = -3746985610571126290L;
}
