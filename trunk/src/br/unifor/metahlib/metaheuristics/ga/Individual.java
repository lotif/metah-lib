package br.unifor.metahlib.metaheuristics.ga;

public class Individual {
/*
 *     protected Random random = new Random();
    protected FuncaoObjetivo funcao;
    protected Object[] genes;

    private Double cacheValor;

    public int compareTo(Object o){
        double eval_x1 = getValor();
        double eval_x2 = ((Individuo) o).getValor();
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

    public Individuo[] reproduzir( Individuo individuo, double probCrossover, Crossover operador ) throws CloneNotSupportedException{
        Individuo filho1 = (Individuo) clone();
        Individuo filho2 = (Individuo) individuo.clone();

        if ( random.nextDouble() < probCrossover ){
            Object[][] filhos = operador.crossover(genes, individuo.genes);
            filho1.genes = filhos[0];
            filho2.genes = filhos[1];
        }

        Individuo[] filhos = new Individuo[] { filho1, filho2 };

        return filhos;
    }

    public void mutar( double pm, Mutacao operador ){
        if ( operador.mutar(genes, pm) ){
            cacheValor = null;
        }
    }


    public double getFitness(){
        double fitness = getValor();
        if ( funcao.getTipo() == TipoOtimizacao.MINIMIZACAO ){
            fitness = -1 * fitness;
        }

        return fitness;

    }

    public double getValor(){
        if ( cacheValor == null ){
            cacheValor = funcao.avaliar(genes);
        }
        
        return cacheValor;
    }

    public Object[] getGenes(){
        return genes;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Individuo clone = (Individuo) super.clone();
        clone.funcao = funcao;
        clone.genes = genes.clone();
        clone.cacheValor = cacheValor;

        return clone;
    }

    public Individuo( FuncaoObjetivo funcao ){
        this.funcao = funcao;
        this.genes = funcao.iniciar();
    }

 */
}
