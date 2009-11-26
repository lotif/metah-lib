package br.unifor.metahlib.metaheuristics.pso;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

/**
 * The Particle Swarm Optimization algorithm.
 */
public class ParticleSwamOptimization extends Heuristic {
	
	/**
	 * Quantity of particles (solutions).
	 */
	private int populationSize = 10;
	
	/**
	 * "Cognitive" acceleration coefficient. 
	 */
	private double c1 = 2.05;

	/**
	 *  "Social" acceleration coefficient. 
	 */
    private double c2 = 2.05;
    
    /**
     * Max quantity of iterations;
     */
    private int max_it = 500;
    
	/**
	 * Movement model that controls the particle moves.
	 */
    private MovementModel movementModel;
    
    /**
     * Determines the neighborhood topology of particles.
     */
    private NeighborhoodTopology neighborhoodTopology;
    
    /**
     * Calculates the inertia of particles.
     */
    private Inertia inertia;
	
    /**
     * Class constructor.
     * @param problem problem to optimize.
     * @param neighborhoodTopology particle neighborhood topology
     * @param inertia particle inertia calculator
     * @param movementModel particle movement model
     */
    public ParticleSwamOptimization(Problem problem, NeighborhoodTopology neighborhoodTopology, Inertia inertia,
    		MovementModel movementModel) {
		super(problem);
		this.neighborhoodTopology = neighborhoodTopology;
		this.inertia = inertia;
		this.movementModel = movementModel;
	}
	
    /**
     * Constructs a particle swarm.
     * @return array of Particle
     */
	private Particle[] newSwarm(){
       Particle[] particles = new Particle[populationSize];
        for (int i = 0; i < particles.length; ++i){
        	particles[i] = new Particle(problem, movementModel);
        }

        neighborhoodTopology.bind(particles);

        return particles;
    }
	
    /**
     * Calculates the attraction of current particle position to other position. 
     * @param position particle position
     * @param attractor attractor position
     * @param accelerationCoefficient controls the effect of the attractor
     * @return position attraction 
     */
	private double[] calcAttraction(Object[] position, Object[] attractor, double accelerationCoefficient){
        double[] attraction = new double[position.length];
        for ( int i = 0; i < attraction.length; ++i ){
            attraction[i] = (problem.getRandom().nextDouble() * accelerationCoefficient) * 
                           ((Double) attractor[i] - (Double) position[i]);
        }

        return attraction;
    }
	
    /**
     * Updates the particle velocity vector.
     * @param velocity current velocity
     * @param attraction attraction to a new position
     */
	private void incVelocity(double[] velocity, double[] attraction){
        assert(velocity.length == attraction.length);
        for (int i = 0; i < velocity.length; ++i){
            velocity[i]+= attraction[i];
        }
    }
	
    /**
     * Get the best particle of array or null if array is empty.
     * @param particles particle array
     * @return the best particle
     */
	private Particle getBestParticle(Particle[] particles){
		Particle best;
		if (particles.length > 0){
	        best = particles[0];
	        double bestValue = best.getValue();
	        double value;
	        for (int i = 1; i < particles.length; ++i ){
	            value = particles[i].getValue();
	            if (value > bestValue){
	                best = particles[i];
	                bestValue = value;
	            }
	        }
	        
		} else {
			best = null;
		}

        return best;
    }

	@Override
	public Solution execute() {
        Particle[] particles = newSwarm();
        Particle p;
        Particle bestParticle;
        Particle bestNeighbor;
        double[] velocity;
        double[] attraction;
        Solution pos_i;
        Solution pos_g;

        bestParticle = getBestParticle(particles).duplicate();
        do {
            for (int i = 0; i < particles.length; ++i){
                p = particles[i];
                if ( p.getValue() < p.getBestValue()){
                    p.setBestPosition(p.getPosition());
                }
                pos_i = p.getBestPosition();

                bestNeighbor = getBestParticle(p.getNeighbors());
                pos_g = bestNeighbor.getPosition();

                velocity = p.getVelocity().clone();
                for ( int j = 0; j < velocity.length; ++j ){
                    velocity[j] = velocity[j] * inertia.calculate(current_it, max_it);
                }

                attraction = calcAttraction(p.getPosition().getValues(), pos_i.getValues(), c1); 
                incVelocity(velocity, attraction);

                attraction = calcAttraction(p.getPosition().getValues(), pos_g.getValues(), c2);
                incVelocity(velocity, attraction);

                p.setVelocity(velocity);
                p.move();
                
                if ( p.getValue() < bestParticle.getValue()){
                    bestParticle = p.duplicate();
                    System.out.println("Improved to: " + bestParticle.getPosition());
                }
            }

        } while (endIteration(bestParticle.getPosition()));

        return bestParticle.getPosition();
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public double getC1() {
		return c1;
	}

	public void setC1(double c1) {
		this.c1 = c1;
	}

	public double getC2() {
		return c2;
	}

	public void setC2(double c2) {
		this.c2 = c2;
	}

	public MovementModel getMovementModel() {
		return movementModel;
	}

	public void setMovementModel(MovementModel movementModel) {
		this.movementModel = movementModel;
	}

	public NeighborhoodTopology getNeighborhoodTopology() {
		return neighborhoodTopology;
	}

	public void setNeighborhoodTopology(NeighborhoodTopology neighborhoodTopology) {
		this.neighborhoodTopology = neighborhoodTopology;
	}

	public Inertia getInertia() {
		return inertia;
	}

	public void setInertia(Inertia inertia) {
		this.inertia = inertia;
	}
	
}

/*
    @Override
    protected String getDescricaoParametros(){
        return String.format( "TamPopulacao=%d,c1=%.2f,c2=%.2f,qtdMaxGeracoes=%d,vizinhanca=%s,inercia=%s",
                tamanhoPopulacao, c1, c2, qtdMaxIteracoes, vizinhanca.getClass().getName(),
                inercia.toString());
    }

    private double calculaMedia( Particula[] particulas ){
        double soma = 0.0;
        for ( int i = 0; i < particulas.length; ++i ){
            soma+= particulas[i].getValor();
        }
        return soma / particulas.length;
    }
}
*/
