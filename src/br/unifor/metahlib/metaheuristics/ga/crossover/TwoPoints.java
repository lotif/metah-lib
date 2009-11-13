package br.unifor.metahlib.metaheuristics.ga.crossover;

public class TwoPoints {
	
	/*
    public Object[][] crossover( Object[] genes1, Object[] genes2){
        assert( genes1.length == genes2.length );
        int len = genes1.length;
        Object[] filho1 = new Object[len];
        Object[] filho2 = new Object[len];

        int r1;
        int r2;

        switch ( len ){
            case 1:
                r1 = 0;
                r2 = 1;
                break;

            default:
                r1 = random.nextInt(len-1);
                r2 = random.nextInt(len-r1) + r1;
        }


        for ( int i = 0; i < r1; ++i ){
            filho1[i] = genes1[i];
            filho2[i] = genes2[i];
        }

        for ( int i = r1; i < r2; ++i ){
            filho1[i] = genes2[i];
            filho2[i] = genes1[i];
        }

        for ( int i = r2; i < len; ++i ){
            filho1[i] = genes1[i];
            filho2[i] = genes2[i];
        }

        Object[][] filhos = new Object[][] { filho1, filho2 };

        return filhos;

    }
    */
}
