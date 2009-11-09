package br.unifor.metahlib.heuristics.hillclimbing;

import java.util.Random;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;


/**
 * An implementation of the hill climbing optimization method 
 * 
 * @author marcelo lotif
 *
 */
public class HillClimbing extends Heuristic {

	/**
	 * Executes the default hill climbing
	 */
	public static final int DEFAULT = 0;
	/**
	 * Executes the iterated hill climbing with the default execution
	 */
	public static final int ITERATED_DEFAULT = 1;
	/**
	 * Executes the stochastic hill climbing
	 */
	public static final int STOCHASTIC = 2;
	/**
	 * Executes the iterated hill climbing with the stochastic execution
	 */
	public static final int ITERATED_STOCHASTIC = 3;
	
	/**
	 * The max number of iterations
	 */
	private int maxIterations;
	/**
	 * The max number of iterations for the iterated hill climbing
	 */
	private int maxIterations2;

	/**
	 * The type of the execution
	 */
	private int type;
	/**
	 * Parameter of the stochastic hill climbing
	 */
	private double T;
	
	public HillClimbing(Problem problem, int type, int maxIterations,int maxIterations2, double t) {
		super(problem);
		this.maxIterations = maxIterations;
		this.maxIterations2 = maxIterations2;
		this.type = type;
		T = t;
	}
	
	/**
	 * Executes the desired hill climbing.
	 * 
	 * @return the best solution found
	 */
	public Solution execute() {
		lastBestFoundOn = 0;
		switch(type){
			case DEFAULT: return executeDefault(); 
			case ITERATED_DEFAULT: return executeIterated(); 
			case STOCHASTIC: return executeStochastic(); 
			case ITERATED_STOCHASTIC: return executeIterated(); 
		}
		return null;
	}

	/**
	 * Executes the default hill climbing
	 * 
	 * @return the best solution found
	 */
	private Solution executeDefault(){
		Solution x = problem.getInitialSolution();
		
		double eval = problem.getCostEvaluator().eval(x);
		NeighborhoodStructure neighborhood = problem.getNeighborhoodStructure();
		//if(type == DEFAULT) { }
		
		for(int i = 0; i < maxIterations; i++){
			Solution _x = neighborhood.getRandomNeighbor(x);
			
			eval = problem.getCostEvaluator().eval(x);
			double _eval = problem.getCostEvaluator().eval(_x);
			
			if(_eval < eval){
				x = _x;
				if(type == DEFAULT){ lastBestFoundOn = i; }
			}
			//if(type == DEFAULT) { }
		}
		
		return x;
	}
	
	/**
	 * Executes the iterated hill climbing
	 * 
	 * @return the best solution found
	 */
	private Solution executeIterated(){
		
		Solution bestx = problem.getInitialSolution();
		
		double bestEval = problem.getCostEvaluator().eval(bestx);
		
		for(int i = 0; i < maxIterations2; i++){
	
			Solution thisx; 
			if(type == ITERATED_DEFAULT) {
				thisx = executeDefault();
			} else {
				thisx = executeStochastic();
			}
			
			double thisEval = problem.getCostEvaluator().eval(thisx);
			bestEval = problem.getCostEvaluator().eval(bestx);
			
			if(thisEval < bestEval){
				bestx = thisx;
				lastBestFoundOn = i + 2;
			}
		}
		
		return bestx;
	}
	
	/**
	 * Executes the stochastic hill climbing
	 * 
	 * @return the best solution found
	 */
	private Solution executeStochastic(){
		Random r = new Random();
		
		Solution x = problem.getInitialSolution();
		
		double eval = problem.getCostEvaluator().eval(x);
		NeighborhoodStructure neighborhood = problem.getNeighborhoodStructure();
		//if(type == STOCHASTIC) { }
		
		for(int i = 0; i < maxIterations; i++){
			Solution _x = neighborhood.getRandomNeighbor(x);
			
			eval = problem.getCostEvaluator().eval(x);
			double _eval = problem.getCostEvaluator().eval(_x);
			
			double rand = r.nextDouble();
//			double minus = Math.abs(_eval) - Math.abs(eval);
//			double minusT = minus/T;
//			double exp = Math.exp(minusT);
//			double plusOne = (1.0 + exp); 
//			double p = 1.0/plusOne;
			
			double exp = (1./(1.+Math.exp((Math.abs(eval)-Math.abs(_eval))/T)));
			
			if(rand < exp){
				x = _x;
				eval = _eval;
				if(type == STOCHASTIC) { lastBestFoundOn = i; }
			}
			//if(type == STOCHASTIC) { }
		}
		
		return x;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public int getMaxIterations2() {
		return maxIterations2;
	}

	public void setMaxIterations2(int maxIterations2) {
		this.maxIterations2 = maxIterations2;
	}
	
	public double getT() {
		return T;
	}

	public void setT(double t) {
		T = t;
	}
	
}
