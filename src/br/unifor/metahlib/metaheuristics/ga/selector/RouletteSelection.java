package br.unifor.metahlib.metaheuristics.ga.selector;

public class RouletteSelection {
	/*
    private Random random = new Random();

    private double getMenorFitness( Individuo[] individuos ){
        double menor = Double.MAX_VALUE;
        for ( int i = 0; i < individuos.length; ++i ){
            menor = Math.min(menor, individuos[i].getFitness());
        }
        return menor;
    }

    public Individuo[] selecionar( Individuo[] individuos, int qtdIndividuos ){
        / * Base ser‡ utilizado para garantir que todos os valores somados sejam positivos. * /
        double base = getMenorFitness(individuos);
        if ( base <= 0 ){
            base = (-1 * base) + 1;
        }

        double soma = 0.0;
        for ( int i = 0; i < individuos.length; ++i ){
            soma+= base + individuos[i].getFitness();
        }

        double[] probabilidades = new double[individuos.length];
        for ( int i = 0; i < probabilidades.length; ++i ){
            probabilidades[i] = (base + individuos[i].getFitness()) / soma;
        }

        Individuo[] selecao = new Individuo[qtdIndividuos];
        for ( int i = 0; i < selecao.length; ++i ){
            double prob = random.nextDouble();
            int j = -1;
            while ( prob > 0 ){
                j++;
                prob-= probabilidades[j];
            }
            selecao[i] = individuos[j];
        }

        return selecao;

    }
    */
}
