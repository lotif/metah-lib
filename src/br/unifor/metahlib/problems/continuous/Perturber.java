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
			values[idx] = perturb(idx, (Double) values[idx]);
		}
		n.setValues(values);
		
		return n;
	}
	
	/**
	 * Perturb the value of a dimension. 
	 * @param dimension dimension of value
	 * @param value value to be perturbed
	 * @return perturbed value
	 */
	public abstract double perturb(int dimension, double value);

	/**
	 * Returns how the dimensions are chosen for perturbation. 
	 * @return dimension selector strategy
	 */
	public DimensionSelector getDimensionSelector() {
		return dimensionSelector;
	}

	/**
	 * Sets how  the dimensions are chosen for perturbation.
	 * @param dimensionSelector dimension selector strategy
	 */
	public void setDimensionSelector(DimensionSelector dimensionSelector) {
		this.dimensionSelector = dimensionSelector;
	}

	/**
	 * Returns the quantity of perturbed dimensions simultaneously.
	 * @return quantity dimensions
	 */
	public int getPerturbedDimensionsCount() {
		return perturbedDimensionsCount;
	}

	/**
	 * Sets the quantity of perturbed dimensions simultaneously.
	 * @param perturbedDimensionsCount quantity dimensions
	 */
	public void setPerturbedDimensionsCount(int perturbedDimensionsCount) {
		assert(perturbedDimensionsCount <= function.getDimensionCount());
		this.perturbedDimensionsCount = perturbedDimensionsCount;
	}

	/**
	 * Returns the maximal percent change of a dimension during a perturbation.
	 * @return number into interval [0,1] 
	 */
	public double getMaxPercentChange() {
		return maxPercentChange;
	}

	/**
	 * Sets the maximal percent change of a dimension during a perturbation.
	 * @param maxPercentChange number into interval [0,1]
	 */
	public void setMaxPercentChange(double maxPercentChange) {
		assert(maxPercentChange >= 0 && maxPercentChange <= 1);
		this.maxPercentChange = maxPercentChange;
	}
}
