package br.unifor.metahlib.exec;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.SumPowFunction;
import br.unifor.metahlib.metaheuristics.HillClimbing;

public class Test {

	public static void main(String[] args) {
		
		Function f = new SumPowFunction();
//		SimulatedAnnealing sa = new SimulatedAnnealing(f, 10, 0.0001, 0.8, 10);
		HillClimbing hc = new HillClimbing(f, HillClimbing.DEFAULT, 100, 0, 0);
		
		double[] minx = hc.execute();
		System.out.println(f.eval(minx));
		
		
	}
	
}
