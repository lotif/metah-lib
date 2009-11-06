package br.unifor.metahlib.pso;

public class PSO {

}

/*
import busca.*;
import funcoes.FuncaoObjetivo;
import funcoes.FuncaoObjetivoNumerica;
import funcoes.TipoOtimizacao;
import java.util.Random;

public class OtimizacaoEnxameParticulas extends AlgoritmoBusca {

    private Random random = new Random();

    public int tamanhoPopulacao = 10;
    public double c1 = 2.05;
    public double c2 = 2.05;
    public Inercia inercia;
    public Vizinhanca vizinhanca;

    @Override
    protected String getDescricaoParametros(){
        return String.format( "TamPopulacao=%d,c1=%.2f,c2=%.2f,qtdMaxGeracoes=%d,vizinhanca=%s,inercia=%s",
                tamanhoPopulacao, c1, c2, qtdMaxIteracoes, vizinhanca.getClass().getName(),
                inercia.toString());
    }

    private Particula[] iniciar(){
        Particula[] particulas = new Particula[tamanhoPopulacao];
        for ( int i = 0; i < particulas.length; ++i){
            particulas[i] = new Particula((FuncaoObjetivoNumerica) funcao);
        }

        vizinhanca.ligaVizinhos(particulas);

        return particulas;
    }

    private double[] calculaAtracao( Object[] atual, Object[] melhor, double constAceleracao ){
        double[] variacao = new double[atual.length];
        for ( int i = 0; i < variacao.length; ++i ){
            variacao[i] = (random.nextDouble() * constAceleracao) * 
                          ((Double) melhor[i] - (Double) atual[i]);
        }

        return variacao;
    }

    private boolean eMelhor( Particula p1, Particula p2 ){
        return normaliza_eval_x(p1.getValor()) > normaliza_eval_x(p2.getValor());
    }

    private boolean eMelhor( double v1, double v2 ){
        return normaliza_eval_x(v1) > normaliza_eval_x(v2);
    }

    private void incVelocidade( double[] velocidade, double[] atracao){
        assert( velocidade.length == atracao.length );
        for ( int i = 0; i < velocidade.length; ++i ){
            velocidade[i] = velocidade[i] + atracao[i];
        }
    }

    private double calculaMedia( Particula[] particulas ){
        double soma = 0.0;
        for ( int i = 0; i < particulas.length; ++i ){
            soma+= particulas[i].getValor();
        }
        
        return soma / particulas.length;
    }

    private Particula getMelhorParticula( Particula[] particulas ){
        Particula melhorParticula = particulas[0];
        double melhorValor = normaliza_eval_x(melhorParticula.getValor());
        double valor;
        for ( int i = 1; i < particulas.length; ++i ){
            valor = normaliza_eval_x(particulas[i].getValor());
            if ( valor > melhorValor ){
                melhorParticula = particulas[i];
                melhorValor = valor;
            }
        }

        return melhorParticula;
    }

    @Override
    protected void executa(ResultadoBusca r) throws Exception {
        
        Particula[] particulas = iniciar();

        Particula p;
        Particula[] vizinhos;
        double[] velocidade;
        double[] atracao;

        Object[] pos_i;
        Object[] pos_g;

        Particula melhorParticula = getMelhorParticula(particulas);
        r.evolucaoMelhor.add(melhorParticula.getValor());
        r.evolucaoMedia.add(calculaMedia(particulas));

        r.xInicial = melhorParticula.getPosicao();

        int t = 0;
        int a = 0;
        while ( t < qtdMaxIteracoes && a < qtdMaxAvaliacoes ){
            for ( int i = 0; i < particulas.length; ++i ){
                p = particulas[i];
                if ( eMelhor(p.getValor(), p.getValorMelhorPosicao())){
                    p.setMelhorPosicao(p.getPosicao());
                }
                pos_i = p.getMelhorPosicao();

                vizinhos = p.getVizinhos();
                pos_g = vizinhos[0].getPosicao();
                double eval_pos_g = vizinhos[0].getValor();
                for ( int j = 1; j < vizinhos.length; j++){
                    if ( eMelhor(vizinhos[j].getValor(), eval_pos_g) ){
                        pos_g = vizinhos[j].getPosicao();
                        eval_pos_g = vizinhos[j].getValor();
                    }
                }

                velocidade = p.getVelocidade().clone();

                for ( int j = 0; j < velocidade.length; ++j ){
                    velocidade[j] = velocidade[j] * inercia.calcula(t, qtdMaxIteracoes);
                }

                atracao = calculaAtracao( p.getPosicao(), pos_i, c1);
                incVelocidade( velocidade, atracao );

                atracao = calculaAtracao( p.getPosicao(), pos_g, c2);
                incVelocidade( velocidade, atracao );

                p.setVelocidade(velocidade);
                p.move();
                
                a++;
                if ( eMelhor(p, melhorParticula) ){
                    r.qtdIteracoesAteMelhorSolucao = t + 1;
                    r.qtdAvaliacoesAteMelhorSolucao = a;
                    melhorParticula = (Particula) p.clone();
                }

            }


            //p = getMelhorParticula(particulas);
            r.evolucaoMelhor.add(melhorParticula.getValor());
            r.evolucaoMedia.add(calculaMedia(particulas));

            t++;
        }

        r.x = melhorParticula.getPosicao();
        r.eval_x = avaliarX( melhorParticula.getPosicao());
        r.qtdIteracoes = t;
        r.qtdAvaliacoes = a;
    }

    public OtimizacaoEnxameParticulas( FuncaoObjetivo funcao ){
        super(funcao);
        tipo = TipoOtimizacao.MAXIMIZACAO;
        qtdMaxIteracoes = 100;
    }

}
*/
