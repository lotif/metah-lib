package br.unifor.metahlib.metaheuristics.scatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.unifor.metahlib.base.Heuristic;
import br.unifor.metahlib.base.Problem;
import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.base.Utils;
import br.unifor.metahlib.heuristics.FirstImprovement;
import br.unifor.metahlib.problems.tsp.neighborhood.KOpt;

/**
 * An implementation of scatter search metaheuristic.
 * 
 * @author Nathanael de Castro Costa
 */
public abstract class ScatterSearch extends Heuristic {

	/**
	 * The reference set size
	 */
	protected int refSetSize;

	/**
	 * Candidade set size used in diversification generator method
	 */
	private int candidateSetSize;

	/**
	 * Used to know the number of solutions chosen by the best cost
	 */
	private int numBestElements;

	/**
	 * Constructor of the class.
	 * 
	 * @param problem
	 *            the problem to be solved
	 * @param refSetSize
	 *            the reference set (or solutions) size
	 */
	public ScatterSearch(Problem problem, int refSetSize) {

		super(problem);

		this.refSetSize = refSetSize;

		this.candidateSetSize = 10 * refSetSize;

		numBestElements = refSetSize / 2;

	}

	@Override
	public Solution execute() {

		List<Solution> refSet = initialPhase();

		return scatterSearchPhase(refSet);

	}

	/**
	 * The initial phase of the scatter search. A set of solutions (the
	 * reference set) are generated to start the scatter search. The size of
	 * solutions set should be equals of refSetSize variable.
	 * 
	 * @return the reference set
	 */
	protected List<Solution> initialPhase() {

		Solution seed = seedGeneration();

		List<Solution> refSet = new ArrayList<Solution>(refSetSize);

		do {
			List<Solution> candidateSet = diversificationGenerator(seed);
			candidateSet = initialImprovement(candidateSet);
			referenceSetUpdate(refSet, candidateSet);
			removeEqualsSolutions(refSet);
		} while (refSet.size() < refSetSize);

		endIteration(refSet.get(0));

		return refSet;
	}

	/**
	 * Creates a solution which it is used to generated others trials solutions
	 * at the initial phase
	 * 
	 * @return the seed solution
	 */
	protected Solution seedGeneration() {

		return problem.getInitialSolution();
	}

	/**
	 * Produces trial solutions from seed solution
	 * 
	 * @param seed
	 *            the seed solution which is used to produce the trial solutions
	 * @return a list of trial solutions
	 */
	protected List<Solution> diversificationGenerator(Solution seed) {

		final List<Solution> solutions = new ArrayList<Solution>(
				candidateSetSize);

		for (int i = 0; i < candidateSetSize; i++) {

			Solution solution = new Solution(problem);

			int n = problem.getDimension();

			Object[] values = new Object[n];

			// h > 0 and h < n
			int h = random.nextInt(n - 1) + 1;

			for (int s = h, pos = 0; s > 0; s--) {

				int rLim = (n - s) / h;

				for (int r = 0; r <= rLim; r++, pos++) {

					values[pos] = seed.getValues()[s + (r * h) - 1];
				}
			}

			solution.setValues(values);

			solutions.add(solution);
		}

		return solutions;
	}

	/**
	 * Makes an improvement over the candidate set of trial solutions
	 * 
	 * @param candidateSet
	 *            trial solutions to be improved
	 * @return candidate set improved
	 */
	protected List<Solution> initialImprovement(List<Solution> candidateSet) {

		final List<Solution> solutions = new ArrayList<Solution>(candidateSet
				.size());

		for (int i = 0; i < candidateSet.size(); i++) {

			Solution solution = candidateSet.get(i);

			FirstImprovement fi = new FirstImprovement(problem, new KOpt(
					problem, 2), solution, 100);

			solution = fi.execute();

			solutions.add(solution);

		}

		return solutions;
	}

	/**
	 * Updates the reference set
	 * 
	 * @param refSet
	 *            reference set to be updated
	 * @param solution
	 *            solution to be added into reference set
	 */
	protected void referenceSetUpdate(final List<Solution> refSet,
			final List<Solution> candidateSet) {

		double[] costs = new double[candidateSet.size()];

		for (int i = 0; i < candidateSet.size(); i++) {
			costs[i] = candidateSet.get(i).getCost();
		}

		int[] origPos = Utils.sort(costs);

		// best solutions (intensification)
		int r1 = numBestElements;

		// number of solutions for diversification
		int r2 = refSetSize - r1;

		int[] intensificationSet = Arrays.copyOf(origPos, r1);

		// candidate diversification set
		int[] candDiverSet = new int[origPos.length - r1];
		System.arraycopy(origPos, r1, candDiverSet, 0, candDiverSet.length);

		int[] diversificationSet = getOrderedDiversificationSet(candidateSet,
				intensificationSet, candDiverSet);

		for (int i = 0; i < intensificationSet.length
				&& refSet.size() < refSetSize; i++) {
			refSet.add(candidateSet.get(intensificationSet[i]));
		}

		for (int i = 0; i < r2 && refSet.size() < refSetSize; i++) {
			refSet.add(candidateSet.get(diversificationSet[i]));
		}

	}

	/**
	 * returns the indices of solutions for diversification set ordered from the
	 * best to worst difference
	 */
	private int[] getOrderedDiversificationSet(List<Solution> candidateSet,
			int[] intensSet, int[] candDiverSet) {

		double[][] diffMatrix = new double[candDiverSet.length][intensSet.length];

		for (int i = 0; i < candDiverSet.length; i++) {
			for (int j = 0; j < intensSet.length; j++) {

				diffMatrix[i][j] = -getSolutionsDifference(candidateSet
						.get(candDiverSet[i]), candidateSet.get(intensSet[j]));
			}
		}

		double[] meanDiff = new double[candDiverSet.length];

		for (int i = 0; i < meanDiff.length; i++) {
			meanDiff[i] = Utils.mean(diffMatrix[i]);
		}

		return Utils.sort(meanDiff);
	}

	/**
	 * Returns the difference between two solutions.
	 * 
	 * @param solution
	 * @param solution2
	 * @return the difference between solutions passed by arguments
	 */
	protected abstract double getSolutionsDifference(Solution solution,
			Solution solution2);

	private void removeEqualsSolutions(List<Solution> refSet) {

		Set<Integer> hashCodes = new HashSet<Integer>(refSet.size());

		for (int i = 0; i < refSet.size(); i++) {

			int hashCode = Arrays.hashCode(refSet.get(i).getValues());

			if (hashCodes.contains(hashCode)) {
				refSet.remove(i);
				i--;
			}

			hashCodes.add(hashCode);
		}
	}

	/**
	 * Starts the scatter search phase
	 * 
	 * @param refSet
	 *            the initial reference set
	 * @return the best solution found
	 */
	protected Solution scatterSearchPhase(List<Solution> refSet) {

		Solution solution = null;

		for (int i = 0; i < max_it; i++) {
			Subsets subsets = subsetGeneration(refSet);
			List<Solution> solutions = solutionCombination(subsets, refSet);
			improvement(solutions);

			List<Solution> refSetTmp = new ArrayList<Solution>(refSetSize);
			referenceSetUpdate(refSetTmp, solutions);

			refSetTmp.addAll(refSet);
			refSet = new ArrayList<Solution>(refSetSize);
//			removeEqualsSolutions(refSetTmp);
			referenceSetUpdate(refSet, refSetTmp);

			solution = refSet.get(0);
			endIteration(solution);
			System.out.println(i + ": "+solution.getCost() + " "
					+ problem.getOptimalSolutionCost());
		}

		
		return solution;
	}

	/**
	 * Generates subsets of solutions
	 * 
	 * @param refSet
	 *            the reference set which is used for generates de subsets
	 * @return the subsets generated
	 */
	protected Subsets subsetGeneration(List<Solution> refSet) {

		double[] costs = new double[refSet.size()];

		for (int i = 0; i < costs.length; i++) {
			costs[i] = refSet.get(i).getCost();
		}

		Subsets subsets = new Subsets();

		addTwoElementsSubset(subsets);

		int[] sortedCostsIndices = Utils.sort(costs);

		addThreeElementsSubset(subsets, sortedCostsIndices);

		addFourElementsSubset(subsets, sortedCostsIndices);

		addBestElementsSubset(subsets, sortedCostsIndices);

		return subsets;

	}

	private void addTwoElementsSubset(Subsets subsets) {

		for (int i = 0; i < refSetSize - 1; i++) {

			for (int j = i + 1; j < refSetSize; j++) {

				List<Integer> list = new ArrayList<Integer>(2);
				list.add(i);
				list.add(j);

				subsets.addSubset(list);
			}
		}
	}

	private void addThreeElementsSubset(Subsets subsets,
			int[] sortedCostsIndices) {

		List<List<Integer>> twoElementsSubset = subsets.getSubset(2);

		for (List<Integer> list : twoElementsSubset) {

			int best = -1;

			for (int i = 0; i < sortedCostsIndices.length; i++) {

				if (!list.contains(sortedCostsIndices[i])) {
					best = sortedCostsIndices[i];
					break;
				}
			}

			list.add(best);
			subsets.addSubset(list);
		}
	}

	private void addFourElementsSubset(Subsets subsets, int[] sortedCostsIndices) {

		List<List<Integer>> threeElementsSubset = subsets.getSubset(3);

		for (List<Integer> list : threeElementsSubset) {

			int best = -1;

			for (int i = 0; i < sortedCostsIndices.length; i++) {

				if (!list.contains(sortedCostsIndices[i])) {
					best = sortedCostsIndices[i];
					break;
				}
			}

			list.add(best);
			subsets.addSubset(list);
		}
	}

	private void addBestElementsSubset(Subsets subsets, int[] sortedCostsIndices) {

		List<Integer> bestElements = new ArrayList<Integer>(numBestElements);

		for (int i = 0; i < numBestElements; i++) {
			bestElements.add(sortedCostsIndices[i]);
		}

		subsets.addSubset(bestElements);
	}

	/**
	 * Combines solutions in the subsets to generate others solutions
	 * 
	 * @param subsets
	 *            the solutions (represented by theirs indices)
	 * @param refSet
	 *            the reference set with the original solutions
	 * @return a set of new solutions
	 */
	protected List<Solution> solutionCombination(Subsets subsets,
			List<Solution> refSet) {

		List<Solution> result = new ArrayList<Solution>();

		// for (Integer type : new int[] { 2, 4, 3, numBestElements }) {
		for (Integer type : new int[] { 2 }) {

			List<List<Integer>> subsetsList = subsets.getSubset(type);

			for (List<Integer> subset : subsetsList) {

				double[] costs = new double[subset.size()];

				for (int j = 0; j < costs.length; j++) {
					costs[j] = refSet.get(subset.get(j)).getCost();
				}

				double weightsSum = 0;
				for (Double cost : costs) {

					weightsSum += 1.0 / cost;
				}

				List<Double> weights = new ArrayList<Double>(subset.size());

				for (Double cost : costs) {

					double weight = cost;

					weights.add((1.0 / weight) / weightsSum);
				}

				List<Solution> solutions = new ArrayList<Solution>(subset
						.size());
				for (Integer solutionIndex : subset) {
					solutions.add(refSet.get(solutionIndex));
				}

				List<Variable> variables = getVariables(solutions);

				for (Variable var : variables) {

					double score = 0.0;

					for (int s = 0; s < subset.size(); s++) {

						Solution solution = refSet.get(subset.get(s));

						if (solutionContainsVariable(solution, var)) {

							score += weights.get(s);
						}
					}

					var.setRealScore(score);

					score = Math.floor(score + 0.5);

					var.setBinaryScore(score == 1.0 ? true : false);
				}

				Solution solution = mountSolution(refSet, variables);

				if (solution != null)
					result.add(solution);
			}
		}
		return result;
	}

	/**
	 * Gets the variables of all solutions
	 * 
	 * @param solutions
	 *            solutions which theirs variables will be extracted
	 * @return a set of variables
	 */
	protected abstract List<Variable> getVariables(List<Solution> solutions);

	/**
	 * Verifies if a solution contains a variable
	 * 
	 * @param solution
	 *            the solution to be verified
	 * @param var
	 *            the variable to be found
	 * @return <code>true</code> if the variable was found in the solution
	 */
	protected abstract boolean solutionContainsVariable(Solution solution,
			Variable var);

	/**
	 * Assembles a solution from the variables and theirs scores
	 * 
	 * @param refSet
	 *            reference set
	 * @param variables
	 *            variables found in solutions of reference set
	 * @return the assembled solution
	 */
	protected abstract Solution mountSolution(List<Solution> refSet,
			List<Variable> variables);

	protected abstract void improvement(List<Solution> solutions);

	protected class Subsets {

		Map<Integer, List<Integer>> subsets;

		Map<Integer, List<Integer>> subsetsIndex;

		public Subsets() {

			this.subsets = new HashMap<Integer, List<Integer>>();
			this.subsetsIndex = new HashMap<Integer, List<Integer>>();
		}

		public List<Integer> split(List<List<Integer>> subset) {

			Set<Integer> set = new HashSet<Integer>();

			for (List<Integer> integer : subset) {
				for (Integer integer2 : integer) {
					set.add(integer2);
				}
			}

			return new ArrayList<Integer>(set);
		}

		public void addSubset(List<Integer> indices) {

			int[] indices_ = new int[indices.size()];

			for (int i = 0; i < indices_.length; i++) {
				indices_[i] = indices.get(i);
			}

			Arrays.sort(indices_);

			int hashCode = Arrays.hashCode(indices_);

			if (!subsets.containsKey(hashCode)) {
				subsets.put(hashCode, indices);

				List<Integer> list = subsetsIndex.get(indices_.length);

				if (list == null) {
					list = new ArrayList<Integer>();
					subsetsIndex.put(indices_.length, list);
				}

				list.add(hashCode);
			}
		}

		public List<List<Integer>> getSubset(int numElements) {

			List<Integer> list = subsetsIndex.get(numElements);

			List<List<Integer>> subset = new ArrayList<List<Integer>>(list
					.size());

			for (Integer integer : list) {

				List<Integer> orig = subsets.get(integer);

				List<Integer> copy = new ArrayList<Integer>(orig);

				subset.add(copy);
			}

			return subset;
		}

	}

	protected abstract class Variable {

		public abstract double getRealScore();

		public abstract boolean getBinaryScore();

		public abstract void setRealScore(double score);

		public abstract void setBinaryScore(boolean score);

		public abstract int hashCode();

		public abstract boolean equals(Object o);
	}
}
