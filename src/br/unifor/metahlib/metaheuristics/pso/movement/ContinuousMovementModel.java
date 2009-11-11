package br.unifor.metahlib.metaheuristics.pso.movement;

import java.util.Random;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.pso.MovementModel;
import br.unifor.metahlib.metaheuristics.pso.Particle;

/**
 * Implements a movement model for continuous search space. 
 */
public class ContinuousMovementModel extends MovementModel {
	
	/**
	 * Vector with max velocity for each dimension of the problem.
	 */
	private double[] maxVelocity;
    
	/**
	 * Vector with minimal velocity for each dimension of the problem.
	 */
	private double[] minVelocity;
	
	/**
	 * Problem random number generator.
	 */
	private Random random;
	
	/**
	 * Problem to be optimized.
	 */
	private Problem problem;
    
    public ContinuousMovementModel(Problem problem){
    	super();
    	this.problem = problem;  
    	this.random = problem.getRandom();
    	
    	int len = problem.getDimension();
    	maxVelocity = new double[len];
    	minVelocity = new double[len];
        for (int i = 0; i < len; ++i){
        	double[] range = problem.getSolutionValueRange(i);
        	double delta = range[1] - range[0];
            maxVelocity[i] = 0.5 * delta;
            minVelocity[i] = -1 * maxVelocity[i];
        }
    }
    

	/**
	 * Moves a particle in the search space.
	 * @param p particle
	 */
	@Override
	public void move(Particle p) {
		Solution s = p.getPosition();
		double[] velocity = p.getVelocity();
    	Object[] values = s.getValues();
        for (int i = 0; i < values.length; ++i){
            values[i] = (Double) values[i] + velocity[i];
        }
        s.setValues(values);
	}
	
	/**
	 * Limits the calculated velocity for a valid value for the informed dimension. 
	 * @param dimension dimension index
	 * @param velocity calculated velocity
	 * @return ranged velocity
	 */
	public double rangeVelocity(int dimension, double velocity){
		return Math.min(Math.max(velocity, minVelocity[dimension]), maxVelocity[dimension]);
	}
	
	/**
	 * Creates a random particle velocity vector.
	 * @return velocity vector
	 */
	public double[] newRandomVelocity(){
		double[] velocity = new double[problem.getDimension()];
        for (int i = 0; i < velocity.length; ++i){
            double delta = maxVelocity[i] - minVelocity[i];
            velocity[i] = (random.nextDouble() * delta) + minVelocity[i];
        }
	
        return velocity;
	}
}
