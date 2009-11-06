package deprecated;

import java.util.Random;



public class SumPowFunction extends Function {

	public SumPowFunction(){
		numVariables = 10;
	}
	
	//Minimo global = 0	
	@Override
	public double evalImpl(double... x) {
		double eq = 0;
		for(int i = 0; i < x.length; i++){
			eq += Math.pow(Math.abs(x[i]), i+1);
		}
		return eq;
	}
	
	@Override
	public double getPerturbation(){
		Random random = new Random();
		int signal = 1;
		if(random.nextDouble() > 0.5){
			signal = -1;
		}
		return (random.nextDouble()*0.1)*signal;
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
		return 1;
	}

	@Override
	public double minValue() {
		return -1;
	}

	@Override
	public double getOptimalEval() {
		return 0;
	}

}
