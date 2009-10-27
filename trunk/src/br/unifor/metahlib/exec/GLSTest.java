package br.unifor.metahlib.exec;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.functions.tsp.A280;
import br.unifor.metahlib.functions.tsp.TSPFunction;
import br.unifor.metahlib.functions.tsp.TSPProblemDefinition;
import br.unifor.metahlib.functions.tsp.TwoOpt;
import br.unifor.metahlib.metaheuristics.HillClimbing;
import br.unifor.metahlib.metaheuristics.gls.GuidedLocalSearch;
import br.unifor.metahlib.metaheuristics.gls.PenalizedFeatures;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPFeature;
import br.unifor.metahlib.metaheuristics.gls.tsp.TSPPenalizedFeatures;

public class GLSTest {

	public static void main(String[] args) {
		try{
			TSPProblemDefinition tsp = new A280(new File(System.getProperty("user.dir") + "/a280.tsp"));
			
			Function f = new TSPFunction(tsp, new TwoOpt());
//			SimulatedAnnealing h = new SimulatedAnnealing(f, 1, 0.00001, 0.9, 1000);
			HillClimbing h = new HillClimbing(f, HillClimbing.DEFAULT, 1000, 0, 0);
			
			List<TSPFeature> l = new ArrayList<TSPFeature>();
			Random r = new Random();
			for(int i = 0; i < tsp.getNumberOfCities() * 2; i++){
				int x = r.nextInt(tsp.getNumberOfCities()) + 1;
				int y = r.nextInt(tsp.getNumberOfCities()) + 1;
				l.add(new TSPFeature(x, y, tsp.getDistance(x, y)));
			}
			
			GuidedLocalSearch gls = new GuidedLocalSearch(f, h, 100, 100, new TSPPenalizedFeatures(l));
			
			double[] minx = gls.execute();
			System.out.println(f.eval(minx));
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
}
