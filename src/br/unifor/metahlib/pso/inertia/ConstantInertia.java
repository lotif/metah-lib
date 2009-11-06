package br.unifor.metahlib.pso.inertia;

import br.unifor.metahlib.pso.Inertia;

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
