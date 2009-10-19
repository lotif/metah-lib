package br.unifor.metahlib.functions;

import java.util.Random;

import br.unifor.metahlib.base.Function;

public class SphereFunction extends Function {

	public SphereFunction(){
		this.numVariables = 30;
	}
	
	@Override
	protected double evalImpl(double... x) {
		
		double sum = 0;
		
		for(int i = 0; i < x.length; i++){
			sum += Math.pow(x[i], 2);
		}
		
		return sum;
	}

	public void setNumVariables(int numVariables) {
		this.numVariables = 30;
	}
	
	@Override
	public double getOptimalEval() {
		return 0;
	}

	@Override
	public double getPerturbation() {
		Random random = new Random();
		int signal = 1;
		if(random.nextDouble() > 0.5){
			signal = -1;
		}
		return random.nextDouble()*2*signal;
	}

	@Override
	public double getFeasibleValue(){
		Random random = new Random();
		int signal = 1;
		if(random.nextDouble() > 0.5){
			signal = -1;
		}
		return (random.nextInt((int)maxValue()) + random.nextDouble())*signal;
	}
	
	@Override
	public double maxValue() {
		return 100;
	}

	@Override
	public double minValue() {
		return -100;
	}

}
