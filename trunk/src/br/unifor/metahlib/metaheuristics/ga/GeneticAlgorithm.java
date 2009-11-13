package br.unifor.metahlib.metaheuristics.ga;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;

public class GeneticAlgorithm extends Heuristic {

	public GeneticAlgorithm(Problem problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Solution execute() {
		// TODO Auto-generated method stub
		return null;
	}
/*
 * 
 *     public int tamanhoPopulacao = 50;
    public double pc = 0.75;
    public double pm = 0.10;
    public Seletor seletorReproducao;
    public Seletor seletorNovaGeracao;
    public Crossover operadorCrossover;
    public Mutacao operadorMutacao;

    @Override
    protected String getDescricaoParametros(){
        return String.format( "TamIndiv=%d,TamPopul=%d,pc=%.2f,pm=%.2f,qtdMaxGeracoes=%d," +
                              "%s,%s,selReproducao=%s,selNovaGeracao=%s",
                tamanhoIndividuo, tamanhoPopulacao, pc, pm, qtdMaxIteracoes,
                operadorCrossover.getClass().getName(), operadorMutacao.getClass().getName(),
                seletorReproducao.getClass().getName(), seletorNovaGeracao.getClass().getName());
    }

    protected Populacao iniciar() {
        Populacao populacao = new Populacao();
        for ( int i = 0; i < tamanhoPopulacao; ++i ){
            populacao.adicionar( new Individuo(funcao) );
        }
        tamanhoIndividuo = funcao.getQtdDimensoes();

        return populacao;
    }
    
    protected Populacao reproduzir( Populacao populacao ) throws CloneNotSupportedException{
        Populacao populacaoFilhos = new Populacao();
        Individuo[] selecao = seletorReproducao.selecionar(populacao.getArray(),
                                                           populacao.getTamanho());

        for ( int i = 0; i < selecao.length; i+=2 ){
            Individuo[] filhos = selecao[i].reproduzir(selecao[i+1], pc, operadorCrossover);
            populacao.adicionar(filhos);
            populacaoFilhos.adicionar(filhos);
        }

        return populacaoFilhos;
    }

    protected void mutar( Populacao populacao ){
        for ( int i = 0; i < populacao.getTamanho(); ++i ){
            populacao.get(i).mutar( pm, operadorMutacao );
        }
    }

    protected Populacao selecionar( Populacao populacao ) throws CloneNotSupportedException{
        Individuo[] selecao = seletorNovaGeracao.selecionar(populacao.getArray(), tamanhoPopulacao);

        Populacao novaGeracao = new Populacao();
        for ( int i = 0; i < selecao.length; ++i ){
            novaGeracao.adicionar( (Individuo) (selecao[i].clone()));
        }

        return novaGeracao;
    }

    @Override
    protected void executa(ResultadoBusca r) throws Exception {
        Populacao p = iniciar();
        Populacao filhos;

        Individuo individuo = p.getMelhorIndividuo();
        Individuo melhorIndividuo = individuo;

        r.evolucaoMelhor.add(individuo.getValor());
        r.evolucaoMedia.add(p.getMediaValor());

        r.xInicial = melhorIndividuo.getGenes();

        int t = 0;
        int a = 0;
        while ( t < qtdMaxIteracoes && a < qtdMaxAvaliacoes ){
            filhos = reproduzir(p);
            mutar(filhos);
            p = selecionar(p);

            individuo = p.getMelhorIndividuo();
            if ( individuo.getFitness() > melhorIndividuo.getFitness() ){
                r.qtdIteracoesAteMelhorSolucao = t + 1;
                r.qtdAvaliacoesAteMelhorSolucao = a + tamanhoPopulacao;
                melhorIndividuo = (Individuo) individuo.clone();
                assert( melhorIndividuo.getFitness() == individuo.getFitness());
                assert( melhorIndividuo != individuo );
            }

            r.evolucaoMelhor.add(individuo.getValor());
            r.evolucaoMedia.add(p.getMediaValor());

            t++;
            a+= tamanhoPopulacao;
        }

        r.x = melhorIndividuo.getGenes();
        r.eval_x = avaliarX( melhorIndividuo.getGenes());
        r.qtdIteracoes = t;
        r.qtdAvaliacoes = a;
    }

    public AlgoritmoGenetico( FuncaoObjetivo funcao ) throws Exception {
        super(funcao);
        this.tipo = TipoOtimizacao.MAXIMIZACAO;
        this.qtdMaxIteracoes = 100;
    }
 * 
 */
	
	
}
