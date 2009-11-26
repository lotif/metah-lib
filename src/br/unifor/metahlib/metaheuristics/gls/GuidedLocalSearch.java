package br.unifor.metahlib.metaheuristics.gls;

import java.io.IOException;
import java.util.List;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.problems.tsp.EdgeWeightFormatNotSupported;
import br.unifor.metahlib.problems.tsp.EdgeWeightTypeNotSupported;


/**
 * An implementation of the Guiided Local Search Metaheuristic.
 * This implementation is in accordance with Voudouris PHD 
 * Thesis "Guided Local Search for Combinatorial Optimisation Problems" (1997).
 * 
 * @author marcelo lotif
 *
 */
public class GuidedLocalSearch extends Heuristic {

	/**
	 * The local search method used inside the GLS execution
	 */
	private Heuristic localSearchMethod;
	
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
	 * The initial solution for the GLS
	 */
	private Solution initialSolution;
	
	/**
	 * First GLS contructor, using lambda.
	 * 
	 * @param function the problem this instance will try to solve.
	 * @param localSearchMethod The local search method used inside the GLS execution
	 * @param lambda The lambda parameter for the GLS execution. It is strongly problem-dependent and must
	 * be set depending on the problem one's trying to solve.
	 * @param maxIterations The maximum number of iterations of this GLS execution. 
	 */
	public GuidedLocalSearch(Problem problem, Heuristic localSearchMethod,  double lambda, int maxIterations) {
		super(problem);
		this.localSearchMethod = localSearchMethod;
		this.a = 0;
		this.lambda = lambda;
		this.max_it = maxIterations;
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
	public GuidedLocalSearch(Problem problem, Heuristic localSearchMethod, int maxIterations, double a) {
		super(problem);
		this.localSearchMethod = localSearchMethod;
		this.a = a;
		this.lambda = 0;
		this.max_it = maxIterations;
	}
	
	/**
	 * The execution method
	 */
	public Solution execute(){
		
		Solution s = initialSolution == null ? problem.getInitialSolution() : initialSolution;
		
		Solution best = null;
		Double bestEval = null;
		
		localSearchMethod.setProblem(problem);
		localSearchMethod.getProblem().setInitialSolution(s);
		s = localSearchMethod.execute();
		
		if(lambda == 0){
			lambda = a*(problem.getCostEvaluator().eval(s)/s.getValues().length);
		}
		
		AugmentedCostProblem f_ = null;
		try {
			f_ = AugmentedCostFunctionFactory.getInstance(problem, lambda);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (EdgeWeightTypeNotSupported e1) {
			e1.printStackTrace();
		} catch (EdgeWeightFormatNotSupported e) {
			e.printStackTrace();
		} 
		
		//for(int i = 0; i < max_it; i++){
		do {
			List<SolutionFeature> features = f_.getSolutionFeatures(s);
			
			double[] util = new double[features.size()];
			double maxUtil = -1;
			int maxUtilIndex = -1;
			
			for(int k = 0; k < util.length; k++){
				SolutionFeature feature = features.get(k);
				util[k] = feature.getCost()/(1 + ((AugmentedCostEvaluator)f_.getCostEvaluator()).getP(feature));
				if(util[k] > maxUtil){
					maxUtil = util[k];
					maxUtilIndex = k;
				}
			}
			
			if(maxUtilIndex >= 0){
				((AugmentedCostEvaluator)f_.getCostEvaluator()).updateP(features.get(maxUtilIndex));
			}
			
			double currentEval = problem.getCostEvaluator().eval(s);
			System.out.println("*" + current_it + ": " + currentEval);
			
			if(bestEval == null || currentEval < bestEval){
				bestEval = currentEval;
				best = s.duplicate();
			}
			
			localSearchMethod.setProblem(f_);
			localSearchMethod.getProblem().setInitialSolution(s);
			s = localSearchMethod.execute();
			
		} while (endIteration(s));
		
		return best;
	}

	public double getLambda() {
		return lambda;
	}

	public void setLambda(double lambda) {
		this.lambda = lambda;
	}

	public Heuristic getLocalSearchMethod() {
		return localSearchMethod;
	}

	public void setLocalSearchMethod(Heuristic localSearchMethod) {
		this.localSearchMethod = localSearchMethod;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public Solution getInitialSolution() {
		return initialSolution;
	}

	public void setInitialSolution(Solution initialSolution) {
		this.initialSolution = initialSolution;
	}

}
