package br.unifor.metahlib.metaheuristics;

import java.util.Random;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.base.Metaheuristic;

public class HillClimbing extends Metaheuristic {

	public static final int DEFAULT = 0;
	public static final int ITERATED_DEFAULT = 1;
	public static final int STOCHASTIC = 2;
	public static final int ITERATED_STOCHASTIC = 3;
	
	private Function function;
	private int maxIterations;
	private int maxIterations2;
	private int type;
	private double T;
	
	public HillClimbing(Function function, int type, int maxIterations,int maxIterations2, double t) {
		this.function = function;
		this.maxIterations = maxIterations;
		this.maxIterations2 = maxIterations2;
		this.type = type;
		T = t;
	}
	
	public double[] execute() {
		switch(type){
			case DEFAULT: return executeDefault(); 
			case ITERATED_DEFAULT: return executeIterated(); 
			case STOCHASTIC: return executeStochastic(); 
			case ITERATED_STOCHASTIC: return executeIterated(); 
		}
		return null;
	}

	private double[] executeDefault(){
		double[] x;
		if(initialSolution == null){
			x = function.getRandomSolution();
		} else {
			x = initialSolution;
		}
		
		double eval = function.eval(x);
		//if(type == DEFAULT) { }
		
		for(int i = 0; i < maxIterations; i++){
			double[] _x = function.perturb(x);
			
			eval = function.eval(x);
			double _eval = function.eval(_x);
			
			if(_eval < eval){
				x = _x;
				if(type == DEFAULT){ lastBestFoundOn = i; }
			}
			//if(type == DEFAULT) { }
		}
		
		return x;
	}
	
	private double[] executeIterated(){
		double[] bestx;
		if(initialSolution == null){
			bestx = function.getRandomSolution();
		} else {
			bestx = initialSolution;
		}
		
		double bestEval = function.eval(bestx);
		for(int i = 0; i < maxIterations2; i++){
	
			double[] thisx; 
			if(type == ITERATED_DEFAULT) {
				thisx = executeDefault();
			} else {
				thisx = executeStochastic();
			}
			
			double thisEval = function.eval(thisx);
			bestEval = function.eval(bestx);
			
			if(thisEval < bestEval){
				bestx = thisx;
				lastBestFoundOn = i + 2;
			}
		}
		
		return bestx;
	}
	
	private double[] executeStochastic(){
		Random r = new Random();
		
		double[] x;
		if(initialSolution == null){
			x = function.getRandomSolution();
		} else {
			x = initialSolution;
		}
		
		double eval = function.eval(x);
		//if(type == STOCHASTIC) { }
		
		for(int i = 0; i < maxIterations; i++){
			double[] _x = function.perturb(x);
			
			eval = function.eval(x);
			double _eval = function.eval(_x);
			
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
	
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
		lastBestFoundOn = 0;
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

	public String getHeader(){
		if(type == DEFAULT){
			return "Default Hill Climbing, " + maxIterations + " iteracoes";
		} else if(type == STOCHASTIC){
			return "Stochastic Hill Climbing, " + maxIterations + " iteracoes, T=" + T;
		} else if(type == ITERATED_DEFAULT){
			return "Iterated Hill Climbing, " + maxIterations + " iteracoes, n_start=" + maxIterations2;
		} else if(type == ITERATED_STOCHASTIC){
			return "Iterated Stochastic Hill Climbing, " + maxIterations + " iteracoes, n_start=" + maxIterations2 + ", T=" + T;
		}else {
			return super.getHeader();
		}
	}
	
}
