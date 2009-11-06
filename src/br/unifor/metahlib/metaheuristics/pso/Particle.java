package br.unifor.metahlib.metaheuristics.pso;

import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public class Particle {
    /*
	private Problem problem;
    private Solution position;
    private Solution bestPosition;
    private double[] velocity;
    private double maxVelocity;
    private double minVelocity;
    private Particle[] neighbors;

    public Solution getPosition(){
        return position;
    }

    public Solution getBestPosition(){
        return bestPosition;
    }

    public void setBestPosition(Solution value){
        try {
			bestPosition = (Solution) value.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			assert(false);
		}
    }

    public double[] getVelocity(){
        return velocity;
    }

    public void setVelocity(double[] value){
        for ( int i = 0; i < velocity.length; ++i ){
            velocity[i] = Math.min(Math.max(value[i], minVelocity), maxVelocity);
        }
    }

    public void move(){
    	Object[] values = position.getValues();
        for (int i = 0; i < values.length; ++i){
        	assert(false);
        	// TODO: To implement movement model
            //values[i] = funcao.limitarX((Double) posicao[i] + velocity[i]);
        }
    }

    public Particle[] getNeighbors(){
        return neighbors;
    }

    public void setNeighbors(Particle[] neighbors){
        this.neighbors = neighbors;
    }

    public double getValue(){
        return position.getCost();
    }

    public double getBestValue(){
        return bestPosition.getCost();
    }

    public int compareTo(Object o){
        double eval_x1 = getValor();
        double eval_x2 = ((Particula) o).getValor();
        if ( eval_x1 == eval_x2 ){
            return 0;
        } else {
            if ( funcao.eMelhor(eval_x1, eval_x2) ){
                return 1;
            } else {
                return -1;
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Particula clone = (Particula) super.clone();
        clone.funcao = funcao;
        clone.posicao = posicao.clone();
        clone.melhorPosicao = bestPosition.clone();
        clone.velocidade = velocity.clone();
        clone.velocidadeMax = maxVelocity;
        clone.velocidadeMin = minVelocity;
        clone.vizinhos = vizinhos.clone();
        clone.valorPosicao = valorPosicao;
        clone.valorMelhorPosicao = valorMelhorPosicao;

        return clone;
    }

    public Particula( FuncaoObjetivoNumerica funcao ){
        this.funcao = funcao;
        posicao = funcao.iniciar();
        setMelhorPosicao(posicao);
        
        double variacao = funcao.x_max - funcao.x_min;
        maxVelocity = 0.5 * variacao;
        minVelocity = -1 * maxVelocity;
        
        variacao = maxVelocity - minVelocity;
        velocity = new double[funcao.getQtdDimensoes()];
        for ( int i = 0; i < velocity.length; ++i){
            velocity[i] = (Math.random() * variacao) + minVelocity;
        }
    }
	*/
}
