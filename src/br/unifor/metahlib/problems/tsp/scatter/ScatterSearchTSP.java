package br.unifor.metahlib.problems.tsp.scatter;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.metaheuristics.scatter.ScatterSearch;
import br.unifor.metahlib.problems.tsp.TSPProblem;

public final class ScatterSearchTSP extends ScatterSearch {

	protected TSPProblem problem;

	public ScatterSearchTSP(TSPProblem problem, int refSetSize) {

		super(problem, refSetSize);
	}

	protected double getSolutionsDifference(Solution a, Solution b) {

		Object[] aValues = a.getValues();
		Object[] bValues = b.getValues();

		Set<Edge> aEdges = new HashSet<Edge>();
		Set<Edge> bEdges = new HashSet<Edge>();
		Set<Edge> union = new HashSet<Edge>();

		for (int i = 0; i < aValues.length - 1; i++) {

			Edge aEdge = new Edge(aValues[i], aValues[i + 1]);
			aEdges.add(aEdge);

			Edge bEdge = new Edge(bValues[i], bValues[i + 1]);
			bEdges.add(bEdge);

			union.add(aEdge);
			union.add(bEdge);
		}

		Edge aEdge = new Edge(aValues[aValues.length - 1], aValues[0]);
		aEdges.add(aEdge);

		Edge bEdge = new Edge(bValues[bValues.length - 1], bValues[0]);
		bEdges.add(bEdge);

		union.add(aEdge);
		union.add(bEdge);

		int intersectionCounter = 0;
		for (Edge e : union) {

			if (aEdges.contains(e) && bEdges.contains(e)) {
				intersectionCounter++;
			}
		}

		return union.size() - intersectionCounter;
	}

	@Override
	protected List<Variable> getVariables(List<Solution> solutions) {

		Set<Variable> vars = new HashSet<Variable>();

		for (Solution s : solutions) {

			Object[] values = s.getValues();

			for (int i = 0; i < values.length - 1; i++) {

				Edge edge = new Edge(values[i], values[i + 1]);
				vars.add(edge);

			}

			Edge aEdge = new Edge(values[values.length - 1], values[0]);
			vars.add(aEdge);
		}

		return new ArrayList<Variable>(vars);
	}

	@Override
	protected boolean solutionContainsVariable(Solution solution, Variable var) {

		boolean contains = false;

		Object[] values = solution.getValues();

		for (int i = 0; i < values.length - 1; i++) {

			Edge edge = new Edge(values[i], values[i + 1]);

			if (edge.equals(var)) {
				contains = true;
				break;
			}
		}

		if (!contains) {

			Edge edge = new Edge(values[values.length - 1], values[0]);

			if (edge.equals(var)) {
				contains = true;
			}

		}

		return contains;
	}

	@Override
	protected Solution mountSolution(List<Solution> refSet,
			List<Variable> variables, List<Double> scores) {

		List<Variable> oneScoresVariables = new ArrayList<Variable>();
		List<Variable> zeroScoresVariables = new ArrayList<Variable>();

		for (int i = 0; i < scores.size(); i++) {

			if (scores.get(i) == 1.0) {
				oneScoresVariables.add(variables.get(i));
			} else {
				zeroScoresVariables.add(variables.get(i));
			}
		}

		// XXX
		assert (oneScoresVariables.size() + zeroScoresVariables.size() == scores
				.size());

		List<Variable> partialSolution = partialSolutionByOneScores(oneScoresVariables);

		Solution complete = completeSolution(zeroScoresVariables,
				partialSolution);

		return complete;
	}

	private List<Variable> partialSolutionByOneScores(
			List<Variable> oneScoresVariables) {

		List<Variable> partial = new ArrayList<Variable>();

		Set<Object> visited = new HashSet<Object>();

		Edge first = (Edge) oneScoresVariables.remove(0);
		partial.add(first);
		visited.add(first.o1);
		visited.add(first.o2);

		ArrayList<Point2D.Double> cities = problem.getDataSet().getCities();

		for (int i = 0; i < partial.size(); i++) {

			Edge edge1 = (Edge) partial.get(i);

			List<Edge> next = new ArrayList<Edge>();

			for (int j = 0; j < oneScoresVariables.size(); j++) {

				Edge edge2 = (Edge) oneScoresVariables.get(j);

				if (edge1.o2.equals(edge2.o1)) {

					if (visited.contains(edge2.o2)) {
						oneScoresVariables.remove(j);
					} else {
						next.add(edge2);
					}

					j--;
				}
			}

			double bestCost = Double.POSITIVE_INFINITY;
			Edge bestVar = null;

			for (Edge edge : next) {

				int city1 = cities.indexOf(edge.o1);
				int city2 = cities.indexOf(edge.o2);

				double cost = problem.getDataSet().getDistance(city1, city2);

				if (cost < bestCost) {
					bestCost = cost;
					bestVar = edge;
				}
			}

			if (bestVar != null) {
				partial.add(bestVar);
			}
		}

		return partial;
	}

	private Solution completeSolution(List<Variable> zeroScoresVariables,
			List<Variable> partialSolution) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void improvement(List<Solution> solutions) {

		// TODO Auto-generated method stub

	}

	private class Edge implements Variable {

		private Object o1;

		private Object o2;

		public Edge(Object o1, Object o2) {

			this.o1 = o1;
			this.o2 = o2;
		}

		@Override
		public boolean equals(Object obj) {

			boolean ret = false;

			if (obj instanceof Edge) {
				Edge edge = (Edge) obj;
				ret = edge.o1.equals(this.o1) && edge.o2.equals(this.o2);
			}

			return ret;
		}

		@Override
		public int hashCode() {

			return (Integer.toString(o1.hashCode()) + Integer.toString(o2
					.hashCode())).hashCode();
		}
	}

}
