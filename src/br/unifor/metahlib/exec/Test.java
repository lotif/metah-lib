package br.unifor.metahlib.exec;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.SumPowFunction;
import br.unifor.metahlib.metaheuristics.sa.SimulatedAnnealing;

public class Test {

	public static void main(String[] args) {
		
		Function f = new SumPowFunction();
		SimulatedAnnealing sa = new SimulatedAnnealing(f, 10, 0.0001, 0.8, 10);
		
		double[] minx = sa.execute();
		System.out.println(f.eval(minx));
		
		
	}
	
}
