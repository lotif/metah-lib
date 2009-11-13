package br.unifor.metahlib.metaheuristics.ga;

public class Population {

	/*
    public Vector<Individuo> individuos;

    public void adicionar( Individuo individuo ){
        individuos.add( individuo );
    }

    public void adicionar( Individuo[] individuos ){
        for ( int i = 0; i < individuos.length; ++i ){
            this.individuos.add( individuos[i] );
        }
    }

    public double getSomatorioValor(){
        double soma = 0.0;
        for ( int i = 0; i < individuos.size(); ++i ){
            soma+= get(i).getValor();
        }

        return soma;
    }

    public double getMediaValor(){
        return getSomatorioValor() / getTamanho();
    }

    public Individuo getMelhorIndividuo(){
        Individuo individuo = individuos.firstElement();
        double melhor = individuo.getFitness();
        double fitness;
        for ( int i = 1; i < individuos.size(); ++i ){
            fitness = get(i).getFitness();
            if ( fitness > melhor ){
                melhor = fitness;
                individuo = get(i);
            }
        }

        return individuo;
    }

    public int getTamanho(){
        return individuos.size();
    }

    public Individuo get( int index ){
        return individuos.get(index);
    }

    public Individuo[] getArray(){
        Individuo[] resultado = new Individuo[individuos.size()];
        for ( int i = 0; i < resultado.length; ++i ){
            resultado[i] = individuos.get(i);
        }

        return resultado;
    }

    
    public Populacao(){
        individuos = new Vector<Individuo>();
    }
    */
}
