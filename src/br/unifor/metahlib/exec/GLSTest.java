package br.unifor.metahlib.exec;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.tsp.A280;
import br.unifor.metahlib.functions.tsp.Berlin52;
import br.unifor.metahlib.functions.tsp.TSPFunction;
import br.unifor.metahlib.functions.tsp.TSPProblemDefinition;
import br.unifor.metahlib.functions.tsp.structures.ThreeOpt;
import br.unifor.metahlib.functions.tsp.structures.TwoOpt;
import br.unifor.metahlib.metaheuristics.HillClimbing;
import br.unifor.metahlib.metaheuristics.gls.GuidedLocalSearch;
import br.unifor.metahlib.metaheuristics.gls.PenalizedFeatures;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPFeature;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPPenalizedFeatures;

public class GLSTest {

	public static void main(String[] args) {
		try{
			TSPProblemDefinition tsp = new A280(new File(System.getProperty("user.dir") + "/a280.tsp"));
//			TSPProblemDefinition tsp = new Berlin52(new File(System.getProperty("user.dir") + "/berlin52.tsp"));
			
			Function f = new TSPFunction(tsp, new TwoOpt());
//			Function f = new TSPFunction(tsp, null);
//			ThreeOpt t = new ThreeOpt(f);
//			((TSPFunction)f).setNeighbourhoodStructure(t);
			
//			SimulatedAnnealing h = new SimulatedAnnealing(f, 1, 0.00001, 0.9, 1000);
			HillClimbing h = new HillClimbing(f, HillClimbing.DEFAULT, 1500, 0, 0);
			
			List<TSPFeature> l = new ArrayList<TSPFeature>();
			Random r = new Random();
			for(int i = 0; i < tsp.getNumberOfCities() * 100; i++){
				int x = r.nextInt(tsp.getNumberOfCities());
				int y = r.nextInt(tsp.getNumberOfCities());
				l.add(new TSPFeature(x, y, tsp.getDistance(x, y)));
			}
			
			GuidedLocalSearch gls = new GuidedLocalSearch(f, h, 200, 10, new TSPPenalizedFeatures(l));
			
			double[] minx = gls.execute();
			System.out.println("Melhor avaliação: "+ f.eval(minx));
			
			System.out.print("Rota da melhor Avaliação: [");
			for(int i = 0; i < minx.length; i++){
				System.out.print((minx[i] + 1) + ",");
			}
			System.out.print("]");
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
