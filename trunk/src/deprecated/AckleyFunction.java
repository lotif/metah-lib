package deprecated;

import java.util.Random;



public class AckleyFunction extends Function {

	public AckleyFunction(){
		numVariables = 10;
	}
	
	//Minimo global = 0
	@Override
	protected double evalImpl(double... x) {
		
		double sum1 = 0;
		double sum2 = 0;
		
		for(int i = 0; i < x.length; i++){
			sum1 += Math.pow(x[i], 2);
			sum2 += Math.cos(2 * Math.PI * x[i]);
		}
		
		double k1 = -0.2*(Math.sqrt((1/(double)x.length)*sum1));
		double k2 = (1/(double)x.length) * sum2;
		
		double f = -20*Math.exp(k1) - Math.exp(k2) + 20 + Math.exp(1);
		
		return f;
	}

	@Override
	public double getPerturbation(){
		Random random = new Random();
		int signal = 1;
		if(random.nextDouble() > 0.5){
			signal = -1;
		}
		return (random.nextInt(1) + random.nextDouble())*signal;
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
		return 32.768;
	}

	@Override
	public double minValue() {
		return -32.768;
	}

	@Override
	public double getOptimalEval() {
		return 0;
	}

}
