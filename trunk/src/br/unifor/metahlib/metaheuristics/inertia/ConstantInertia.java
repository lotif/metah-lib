package br.unifor.metahlib.metaheuristics.inertia;

import br.unifor.metahlib.metaheuristics.pso.Inertia;

public class ConstantInertia extends Inertia {
    
	private double value;

    public ConstantInertia(double value){
        this.value = value;
    }
	
    @Override
    public String toString(){
        return "w=" + value;
    }

	@Override
	public double calculate(int currentGeneration, int totalGenerations) {
		return value;
	}

}
