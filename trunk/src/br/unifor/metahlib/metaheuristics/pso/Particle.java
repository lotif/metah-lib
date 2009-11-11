package br.unifor.metahlib.metaheuristics.pso;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * Implements the concept of a particle of PSO algorithm.
 */
public class Particle implements Comparable<Particle>, Cloneable {
	
	/**
	 * Problem to optimize. 
	 */
	private Problem problem;
	
	/**
	 * Movement model that controls the particle moves.
	 */
	private MovementModel movementModel;

    /**
     * Current particle position.
     */
	private Solution position;
	
	/**
	 * Best visited position.
	 */
	private Solution bestPosition;

    /**
     * Current particle velocity.
     */
	private double[] velocity;
    
    /**
     * Neighbors that influence the particle.
     */
    private Particle[] neighbors;
    
    /**
     * Returns the current position.
     * @return current position
     */
    public Solution getPosition(){
        return position;
    }

    /**
     * Returns the best visited position.
     * @return best visited position
     */
    public Solution getBestPosition(){
        return bestPosition;
    }

    /**
     * Sets the best visited position.
     * @param value best position
     */
    public void setBestPosition(Solution value){
		bestPosition = value.duplicate();
    }

    /**
     * Returns the current particle velocity.
     * @return current velocity vector
     */
    public double[] getVelocity(){
        return velocity;
    }

    
    /**
     * Sets the current particle velocity.
     * @param value velocity vector
     */
    public void setVelocity(double[] value){
        for ( int i = 0; i < velocity.length; ++i ){
            velocity[i] = movementModel.rangeVelocity(i, value[i]);
        }
    }

    /**
     * Moves the particle using the current position and velocity.
     */
    public void move(){
    	movementModel.move(this);
    }

    /**
     * Returns the neighbors that influence the particle movement.
     * @return vector of particles
     */
    public Particle[] getNeighbors(){
        return neighbors;
    }

    /**
     * Sets the neighbors that influence the particle movement.
     * @param neighbours vector of particles
     */
    public void setNeighbors(Particle[] neighbors){
        this.neighbors = neighbors;
    }

    /**
     * Returns 
     * @return
     */
    public double getValue(){
        return position.getCost();
    }

    public double getBestValue(){
        return bestPosition.getCost();
    }
    
    public int compareTo(Particle p){
    	double c1 = getValue();
    	double c2 = p.getValue();
    	if (c1 == c2){
    		return 0;
    	} else {
    		return c1 < c2 ? 1 : -1; 	
    	}
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Particle p = duplicate();
        return p;
    }
    
    /**
     * Creates a copy.
     * @return copy of this particle.
     */
    public Particle duplicate(){
        Particle p = new Particle(problem, movementModel);
        p.movementModel = movementModel;
        p.position = position.duplicate();
        p.bestPosition = bestPosition.duplicate();
        p.velocity = velocity.clone();
        p.neighbors = neighbors.clone();
        return p;
    }

    public Particle(Problem problem, MovementModel movementModel){
    	this.problem = problem;
        this.movementModel = movementModel;
        this.position = problem.newRandomSolution();
        this.bestPosition = this.position.duplicate();
        this.velocity = movementModel.newRandomVelocity();
    }
}