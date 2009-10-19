package br.unifor.metahlib.metaheuristics.sa;

import java.util.Random;

import br.unifor.metahlib.base.Function;
import br.unifor.metahlib.base.Metaheuristic;

public class SimulatedAnnealing extends Metaheuristic {

	private Function function;
	private int maxIterations;
	private double maxTemperature;
	private double minTemperature;
	private double decreaseStep;

//	private SAGraphicFrame frame;

	public SimulatedAnnealing(Function function, double tmax, double tmin, double b, int k){
		this.function = function;
		this.maxIterations = k;
		this.maxTemperature = tmax;
		this.minTemperature = tmin;
		this.decreaseStep = b;
//		this.frame = null;
	}

	public double[] execute() {
		Random r = new Random();
		double[] x = function.getRandomSolution();
		
		double temperature = maxTemperature;
		
		int currentIteration = 0;
		double eval = eval(x);
//		updateGraphic(realEval(eval), realEval(eval), temperature, currentIteration);
		
		int totalIt = 1;
		while(temperature > minTemperature){
			for(int i = 0; i < maxIterations; i++){
				currentIteration++;
				
				double[] _x = function.perturb(x);
				double _eval = eval(_x);

				if(_eval < eval){
					x = _x;
					eval = _eval;
					lastBestFoundOn = totalIt;
//					updateGraphic(realEval(eval), realEval(eval), temperature, currentIteration);
				} else {
					double rand = r.nextDouble();
					double exp = Math.exp((eval - _eval)/temperature);
					if(rand < exp){
						x = _x;
						eval = _eval;
//						updateGraphic(realEval(eval), realEval(eval), temperature, currentIteration);
					} else {
//						updateGraphic(realEval(_eval), realEval(eval), temperature, currentIteration);
					}
				}
				totalIt++;
			}
			temperature *= decreaseStep;
		}
		
		return x;
	}
	
	
	
	private double eval(double[] x) {
		return function.eval(x);
	}

//	private double realEval(double eval) {
//		return -eval;
//	}

//	private void updateGraphic(double thisEval, double selectedEval, double temperature, int currentIteration){
//		if(function instanceof MinimizationFunction){
//			thisEval = ((MinimizationFunction) function).realEval(thisEval);
//			selectedEval = ((MinimizationFunction) function).realEval(selectedEval);
//		}
//		if(frame != null){
//			if(frame.getBestEverEval() == null){
//				frame.setFunction(function);
//				frame.setParameters(maxIterations);
//				frame.init(selectedEval, selectedEval, temperature/maxTemperature);
//			}
//			frame.setGraphic(frame.createGraphic(thisEval, selectedEval, temperature), currentIteration);
//		}
//	}
	
	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}
	
	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;		
	}
	
	public double getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(double maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public double getDecreaseStep() {
		return decreaseStep;
	}

	public void setDecreaseStep(double decreaseStep) {
		this.decreaseStep = decreaseStep;
	}

	@Override
//	public void enableGraphic() {
//		frame = new SAGraphicFrame();
//	}
	
	public String getHeader(){
		return "Simulated Annealing, tmax=" + maxTemperature + ", tmin=" + minTemperature + ", b=" + decreaseStep + ", k=" + maxIterations;
	}
	
}
