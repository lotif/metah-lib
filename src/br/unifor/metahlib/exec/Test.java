package br.unifor.metahlib.exec;

import java.io.File;
import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.tsp.TSPFunction;
import br.unifor.metahlib.functions.tsp.TSPLibReader;
import br.unifor.metahlib.functions.tsp.TSPProblemDefinition;
import br.unifor.metahlib.functions.tsp.structures.ThreeOpt;
import br.unifor.metahlib.functions.tsp.structures.TwoOpt;
import br.unifor.metahlib.metaheuristics.HillClimbing;
import br.unifor.metahlib.metaheuristics.sa.SimulatedAnnealing;

public class Test {

	public static void main(String[] args) {
		try{
//			TSPProblemDefinition tsp = new A280(new File(System.getProperty("user.dir") + "/a280.tsp"));
			TSPProblemDefinition tsp = new TSPLibReader(new File(System.getProperty("user.dir") + "/berlin52.tsp"));
			
//			Function f = new TSPFunction(tsp, new TwoOpt());
			Function f = new TSPFunction(tsp, null);
			ThreeOpt t = new ThreeOpt(f);
			((TSPFunction)f).setNeighbourhoodStructure(t);
			
//			SimulatedAnnealing h = new SimulatedAnnealing(f, 1, 0.00001, 0.9, 1000);
			HillClimbing h = new HillClimbing(f, HillClimbing.DEFAULT, 10000, 0, 0);
			
			double[] minx = h.execute();
			System.out.println(f.eval(minx));
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
