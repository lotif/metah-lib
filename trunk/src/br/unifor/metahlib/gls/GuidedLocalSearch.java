package br.unifor.metahlib.gls;

import java.util.List;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.base.Metaheuristic;

/**
 * An implementation of the Guiided Local Search Metaheuristic.
 * This implementation is in accordance with Voudouris PHD 
 * Thesis "Guided Local Search for Combinatorial Optimisation Problems" (1997).
 * 
 * @author marcelo lotif
 *
 */
public class GuidedLocalSearch extends Metaheuristic {

	/**
	 * The local search method used inside the GLS execution
	 */
	private Metaheuristic localSearchMethod;
	
	/**
	 * The parameter "a" defined by Voudouris (1997) p. 64-65.
	 * Initially idealized for TSP, this parameter tries to guess lambda based on 
	 * the problem instance. The following values are accepted depending on the 
	 * neighborhood structure chosen:<br/>
	 * 2-OPT: 1/8 <= a <= 1/2<br/>
	 * 3-OPT: 1/10 <= a <= 1/4<br/>
	 * LK-OPT: 1/12 <= a <= 1/6<br/>
	 * <br/>
	 * One can still force the lamba parameter by using the setLambda(double) method before
	 * the beginning of the execution.
	 */ 
	private double a;
	
	/**
	 * The lambda parameter for the GLS execution. It is strongly problem-dependent and must
	 * be set depending on the problem one's trying to solve.
	 */
	private double lambda;
	
	/**
	 * The maximum number of iterations of this GLS execution.
	 */
	private int maxIterations;
	
	/**
	 * First GLS contructor, using lambda.
	 * 
	 * @param function the problem this instance will try to solve.
	 * @param localSearchMethod The local search method used inside the GLS execution
	 * @param lambda The lambda parameter for the GLS execution. It is strongly problem-dependent and must
	 * be set depending on the problem one's trying to solve.
	 * @param maxIterations The maximum number of iterations of this GLS execution. 
	 */
	public GuidedLocalSearch(Function function, Metaheuristic localSearchMethod,  double lambda, int maxIterations) {
		super(function);
		this.localSearchMethod = localSearchMethod;
		this.a = 0;
		this.lambda = lambda;
		this.maxIterations = maxIterations;
	}
	
	/**
	 * Second GLS contructor, using a
	 * 
	 * @param function the problem this instance will try to solve.
	 * @param localSearchMethod The local search method used inside the GLS execution
	 * @param maxIterations The maximum number of iterations of this GLS execution. 
	 * @param a The parameter "a" defined by Voudouris (1997) p. 64-65.
	 * Initially idealized for TSP, this parameter tries to guess lambda based on 
	 * the problem instance. The following values are accepted depending on the 
	 * neighborhood structure chosen:<br/>
	 * 2-OPT: 1/8 <= a <= 1/2<br/>
	 * 3-OPT: 1/10 <= a <= 1/4<br/>
	 * LK-OPT: 1/12 <= a <= 1/6<br/>
	 * <br/>
	 * One can still force the lamba parameter by using the setLambda(double) method before
	 * the beginning of the execution.
	 */
	public GuidedLocalSearch(Function function, Metaheuristic localSearchMethod, int maxIterations, double a) {
		super(function);
		this.localSearchMethod = localSearchMethod;
		this.a = a;
		this.lambda = 0;
		this.maxIterations = maxIterations;
	}
	
	/**
	 * The execution method
	 */
	public double[] execute(){
		
		double[] s = function.getRandomSolution();
		
		double[] best = null;
		Double bestEval = null;
		
		localSearchMethod.setFunction(function);
		localSearchMethod.setInitialSolution(s);
		s = localSearchMethod.execute();
		
		if(lambda == 0){
			lambda = a*(function.eval(s)/function.getNumVariables());
		}
		
		AugmentedCostFunction f_ = AugmentedCostFunctionFactory.getInstance(function, lambda); 
		
		for(int i = 0; i < maxIterations; i++){
			
			List<SolutionFeature> features = f_.getSolutionFeatures(s, function);
			
			double[] util = new double[features.size()];
			double maxUtil = -1;
			int maxUtilIndex = -1;
			
			for(int k = 0; k < util.length; k++){
				SolutionFeature feature = features.get(k);
				util[k] = feature.getCost()/(1 + f_.getP(feature));
				if(util[k] > maxUtil){
					maxUtil = util[k];
					maxUtilIndex = k;
				}
			}
			
			if(maxUtilIndex >= 0){
				f_.updateP(features.get(maxUtilIndex));
			}
			
			double currentEval = function.eval(s);
			System.out.println("*" + i + ": " + currentEval);
			
			if(bestEval == null || currentEval < bestEval){
				bestEval = currentEval;
				best = s.clone();
			}
			
			localSearchMethod.setFunction(f_);
			localSearchMethod.setInitialSolution(s);
			s = localSearchMethod.execute();
		}
		
		return best;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

}
