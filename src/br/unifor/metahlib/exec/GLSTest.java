package br.unifor.metahlib.exec;

import java.io.File;

import deprecated.Function;
import deprecated.TSPFunction;
import deprecated.TSPLibReader;
import deprecated.TSPProblemDefinition;

import br.unifor.metahlib.gls.GuidedLocalSearch;
import br.unifor.metahlib.heuristics.hillclimbing.HillClimbing;
import br.unifor.metahlib.problems.tsp.neighborhood.ThreeOpt;
import br.unifor.metahlib.problems.tsp.neighborhood.TwoOpt;

public class GLSTest {

	public static void main(String[] args) {
		try{
			TSPProblemDefinition tsp = new TSPLibReader(new File(System.getProperty("user.dir") + "/a280.tsp"));
			
			Function f = new TSPFunction(tsp, new TwoOpt());
//			Function f = new TSPFunction(tsp, null);
//			ThreeOpt t = new ThreeOpt(f);
//			((TSPFunction)f).setNeighbourhoodStructure(t);
			
//			SimulatedAnnealing h = new SimulatedAnnealing(f, 1, 0.00001, 0.9, 1000);
			HillClimbing h = new HillClimbing(f, HillClimbing.DEFAULT, 1500, 0, 0);
			
			/*
			 * Possible values for the GLS parameter "a", according with Vordouris 1997:
			 * 2-OPT: 1/8 <= a <= 1/2
			 * 3-OPT: 1/10 <= a <= 1/4
			 * LK-OPT: 1/12 <= a <= 1/6
			 */
			GuidedLocalSearch gls = new GuidedLocalSearch(f, h, 2000, 1.0/6.0);
			
			double[] minx = gls.execute();
			System.out.println("Melhor avaliacao: "+ f.eval(minx));
			
			System.out.print("Rota da melhor Avaliacao: [");
			for(int i = 0; i < minx.length; i++){
				System.out.print((minx[i] + 1) + ",");
			}
			System.out.print("]");
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
