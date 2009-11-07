package br.unifor.metahlib.problems.continuous;

import java.util.List;
import java.util.Vector;

import br.unifor.metahlib.base.NeighborhoodStructure;
import br.unifor.metahlib.base.Solution;

/**
 * Perturb the values of a possible solution for a continuous optimization problem. 
 */
public abstract class Perturber extends NeighborhoodStructure {
	
	/**
	 * Indicates how the dimensions are chosen for perturbation. 
	 */
	static public enum DimensionSelector { ALEATORY, SEQUENCIAL }; 
	
	/**
	 * Function to be optimized.
	 */
	protected OptimizableFunction function;
	
	/**
	 * Indicates how the dimensions are chosen for perturbation. 
	 */
	protected DimensionSelector dimensionSelector = DimensionSelector.ALEATORY;
	
	/**
	 * Quantity of perturbed dimensions simultaneously.
	 */
	protected int perturbedDimensionsCount = 1;
	
	/**
	 * Maximal percent change of a dimension during a perturbation. 
	 */
	protected double maxPercentChange = 0.05;
	
    /**
     * Last perturbed dimension;
     */
	private int lastPerturbedDimension = -1;

    /**
     * Choices the dimensions that will be perturbed.
     * @return array of dimension indexes
     */
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

	/**
	 * Class constructor.
	 * @param function function to be optimized
	 */
	public Perturber(OptimizableFunction function){
		this.function = function;
	}

	@Override
	public List<double[]> getNeighbours(List<double[]> parents) {
		assert(false); // deprecated
		return null;
	}
	
	/**
	 * Create a new random neighbor solution.
	 * @param solution base of neighborhood
	 * @return a random solution into neighborhood of informed solution 
	 */
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
	
	/**
	 * Perturb the value. 
	 * @param dimension dimension of value
	 * @param value value to be perturbed
	 * @return perturbed value
	 */
	protected abstract double perturb(int dimension, double value);
}
