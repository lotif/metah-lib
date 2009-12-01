package br.unifor.metahlib.base;

/**
 * Class implementing some simple utility methods. Some methods here are the
 * same found in Weka software {@link http://www.cs.waikato.ac.nz/ml/weka/}.
 * 
 * @author Eibe Frank 
 * @author Yong Wang 
 * @author Len Trigg 
 * @author Julien Prados
 * @author Nathanael de Castro Costa
 */
public class Utils {

	/**
	 * Sorts a given array of doubles in ascending order and returns an array of
	 * integers with the positions of the elements of the original array in the
	 * sorted array. NOTE THESE CHANGES: the sort is no longer stable and it
	 * doesn't use safe floating-point comparisons anymore. Occurrences of
	 * Double.NaN are treated as Double.MAX_VALUE
	 * 
	 * @param array
	 *            this array is not changed by the method!
	 * @return an array of integers with the positions in the sorted array.
	 */
	public static/* @pure@ */int[] sort(/* @non_null@ */double[] array) {

		int[] index = new int[array.length];
		array = (double[]) array.clone();
		for (int i = 0; i < index.length; i++) {
			index[i] = i;
			if (Double.isNaN(array[i])) {
				array[i] = Double.MAX_VALUE;
			}
		}
		quickSort(array, index, 0, array.length - 1);
		return index;
	}

	/**
	 * Implements quicksort according to Manber's "Introduction to Algorithms".
	 * 
	 * @param array
	 *            the array of doubles to be sorted
	 * @param index
	 *            the index into the array of doubles
	 * @param left
	 *            the first index of the subset to be sorted
	 * @param right
	 *            the last index of the subset to be sorted
	 */
	// @ requires 0 <= first && first <= right && right < array.length;
	// @ requires (\forall int i; 0 <= i && i < index.length; 0 <= index[i] &&
	// index[i] < array.length);
	// @ requires array != index;
	// assignable index;
	private static void quickSort(/* @non_null@ */double[] array, /* @non_null@ */
			int[] index, int left, int right) {

		if (left < right) {
			int middle = partition(array, index, left, right);
			quickSort(array, index, left, middle);
			quickSort(array, index, middle + 1, right);
		}
	}

	/**
	 * Partitions the instances around a pivot. Used by quicksort and
	 * kthSmallestValue.
	 * 
	 * @param array
	 *            the array of doubles to be sorted
	 * @param index
	 *            the index into the array of doubles
	 * @param l
	 *            the first index of the subset
	 * @param r
	 *            the last index of the subset
	 * @return the index of the middle element
	 */
	private static int partition(double[] array, int[] index, int l, int r) {

		double pivot = array[index[(l + r) / 2]];
		int help;

		while (l < r) {
			while ((array[index[l]] < pivot) && (l < r)) {
				l++;
			}
			while ((array[index[r]] > pivot) && (l < r)) {
				r--;
			}
			if (l < r) {
				help = index[l];
				index[l] = index[r];
				index[r] = help;
				l++;
				r--;
			}
		}
		if ((l == r) && (array[index[r]] > pivot)) {
			r--;
		}

		return r;
	}

	/**
	   * Computes the mean for an array of doubles.
	   *
	   * @param vector the array
	   * @return the mean
	   */
	  public static /*@pure@*/ double mean(double[] vector) {
	  
	    double sum = 0;

	    if (vector.length == 0) {
	      return 0;
	    }
	    for (int i = 0; i < vector.length; i++) {
	      sum += vector[i];
	    }
	    return sum / (double) vector.length;
	  }

}
