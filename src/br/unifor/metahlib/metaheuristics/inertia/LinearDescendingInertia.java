package br.unifor.metahlib.metaheuristics.inertia;

import br.unifor.metahlib.metaheuristics.pso.Inertia;

public class LinearDescendingInertia extends Inertia {

	private double maxValue;
    private double minValue;
    
    public LinearDescendingInertia(double maxValue, double minValue){
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    @Override
    public String toString(){
        return "w(t) = " + maxValue + " - t/tmax * (" + maxValue + " - " + minValue + ");";
    }

	@Override
	public double calculate(int currentGeneration, int totalGenerations) {
        double value = maxValue - (currentGeneration/totalGenerations) * (maxValue - minValue);
        return value;
	}
}

