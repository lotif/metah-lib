package br.unifor.metahlib.problems.continuous;

import java.util.List;
import java.util.Vector;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Solution;

public abstract class Perturber extends NeighborhoodStructure {
	
	static public enum DimensionSelector { ALEATORY, SEQUENCIAL }; 
	
	protected OptimizableFunction function;
	
	protected DimensionSelector dimensionSelector = DimensionSelector.ALEATORY;
	protected int perturbedDimensionsCount = 1;
	protected double maxPercentChange = 0.05;
	
    private int lastPerturbedDimension = -1;

    private int[] choiceDimensionsForPerturb(){
        int[] idxs = new int[perturbedDimensionsCount];
        if ( perturbedDimensionsCount == 1 ){
            idxs[0] = random.nextInt(function.getDimensionCount());

        } else {
            if (dimensionSelector == DimensionSelector.ALEATORY){
                Vector<Integer> leftIdxs = new Vector<Integer>();
                for (int i = 0; i < function.getDimensionCount(); ++i){
                	leftIdxs.add(i);
                }

                for (int i = 0; i < idxs.length; ++i){
                    int j = random.nextInt(leftIdxs.size());
                    idxs[i] = leftIdxs.remove(j);
                }

            } else {
                for ( int i = 0; i < idxs.length; ++i ){
                	lastPerturbedDimension++;
                	idxs[i] = lastPerturbedDimension;
                    if ( lastPerturbedDimension >= function.getDimensionCount() - 1){
                    	lastPerturbedDimension = 0;
                    }

                }
            }
        }

        return idxs;
    }

	public Perturber(OptimizableFunction function){
		this.function = function;
	}

	@Override
	public List<double[]> getNeighbours(List<double[]> parents) {
		assert(false); // deprecated
		return null;
	}
	
	@Override
	public Solution getRandomNeighbor(Solution solution){
		Solution n = solution.duplicate();
		Object[] values = n.getValues();
		int[] idxs = choiceDimensionsForPerturb();
		for (int i = 0; i < idxs.length; ++i){
			int idx = idxs[i];
			double value = perturb(idx, (Double) values[idx]);
			values[idx] = function.rangeValue(idx, value);
		}
		n.setValues(values);
		
		return n;
	}
	
	protected abstract double perturb(int dimension, double value);
}
