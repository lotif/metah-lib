package br.unifor.metahlib.problems.tsp.scatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.unifor.metahlib.base.Solution;
import br.unifor.metahlib.base.Utils;
import br.unifor.metahlib.heuristics.hillclimbing.HillClimbing;
import br.unifor.metahlib.metaheuristics.scatter.ScatterSearch;
import br.unifor.metahlib.problems.tsp.TSPProblem;
import br.unifor.metahlib.problems.tsp.neighborhood.TwoOpt;

/**
 * Implementation of scatter search for the TSP problem.
 * 
 * @author Nathanael de Castro Costa
 */
public final class ScatterSearchTSP extends ScatterSearch {

	protected TSPProblem problem;

	public ScatterSearchTSP(TSPProblem problem, int refSetSize) {

		super(problem, refSetSize);

		this.problem = problem;
	}

	@Override
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

	protected Solution mountSolution(List<Solution> refSet,
			List<Variable> variables) {

		Graph graph = new Graph(problem.getDimension());

		for (Variable var : variables) {

			if (var.getBinaryScore()) {
				Edge e1 = (Edge) var;

				graph.addEdge(e1);
			}
		}

		graph.limitNeighbors();

		List<Object> path = graph.getBestPath();

		Solution solution = null;

		if (path.size() - 1 == problem.getDimension()) {

			Object[] values = new Object[problem.getDimension()];

			for (int i = 0; i < values.length; i++) {
				values[i] = path.get(i);
			}

			solution = new Solution(problem);
			solution.setValues(values);
		}

		return solution;
	}

	@Override
	protected void improvement(List<Solution> solutions) {

		for (int i = 0; i < solutions.size(); i++) {

			problem.setInitialSolution(solutions.get(i));

			HillClimbing hc = new HillClimbing(problem, new TwoOpt(),
					HillClimbing.DEFAULT, 10, 10, 0.95);

			solutions.set(i, hc.execute());
		}
	}

	private class Edge extends Variable {

		private Object o1;

		private Object o2;

		private double cost;

		private double realScore;

		private boolean binaryScore;

		public Edge(Object o1, Object o2) {

			this.o1 = o1;
			this.o2 = o2;

			this.cost = problem.getDataSet().getDistance((Integer) o1,
					(Integer) o2);
		}

		@Override
		public boolean equals(Object obj) {

			boolean ret = false;

			if (obj instanceof Edge) {
				Edge edge = (Edge) obj;
				ret = edge.o1.equals(this.o1) && edge.o2.equals(this.o2);
				ret = ret || edge.o1.equals(this.o2) && edge.o2.equals(this.o1);
			}

			return ret;
		}

		@Override
		public int hashCode() {

			return (Integer.toString(o1.hashCode()) + Integer.toString(o2
					.hashCode())).hashCode();
		}

		@Override
		public String toString() {

			return o1.toString() + " " + o2.toString() + ": " + cost;
		}

		@Override
		public void setBinaryScore(boolean score) {

			this.binaryScore = score;
		}

		@Override
		public void setRealScore(double score) {

			this.realScore = score;
		}

		@Override
		public boolean getBinaryScore() {

			return binaryScore;
		}

		@Override
		public double getRealScore() {

			return realScore;
		}
	}

	private class Graph {

		private final static int NEIGHBORS_LIM = 2;

		private Edge[][] matrix;

		public Graph(int numvertices) {

			matrix = new Edge[numvertices][numvertices];
		}

		public void addEdge(Edge edge) {

			int v1 = ((Integer) edge.o1) - 1;
			int v2 = ((Integer) edge.o2) - 1;

			matrix[v1][v2] = edge;
			matrix[v2][v1] = edge;
		}

		public List<Integer> getNeighbors(int i) {

			List<Integer> neighbors = new ArrayList<Integer>();

			for (int j = 0; j < matrix[i].length; j++) {
				if (i != j && matrix[i][j] != null) {
					neighbors.add(j);
				}
			}

			return neighbors;
		}

		public void limitNeighbors() {

			for (int i = 0; i < matrix.length - 1; i++) {
				for (int j = i + 1; j < matrix[i].length; j++) {

					if (matrix[i][j] != null) {

						List<Integer> neighbors1 = getNeighbors(i);
						List<Integer> neighbors2 = getNeighbors(j);

						if (neighbors1.size() > NEIGHBORS_LIM
								|| neighbors2.size() > NEIGHBORS_LIM) {

							double sb1 = secondBestScore(i, neighbors1);
							double sb2 = secondBestScore(j, neighbors2);

							if (neighbors1.size() > NEIGHBORS_LIM
									&& neighbors2.size() <= NEIGHBORS_LIM) {

								if (matrix[i][j].realScore <= sb1) {

									matrix[i][j] = null;
									matrix[j][i] = null;
								}
							} else if (neighbors1.size() <= NEIGHBORS_LIM
									&& neighbors2.size() > NEIGHBORS_LIM) {

								if (matrix[i][j].realScore <= sb2) {

									matrix[i][j] = null;
									matrix[j][i] = null;
								}
							} else {
								if (matrix[i][j].realScore <= sb1
										&& matrix[i][j].realScore <= sb2) {

									matrix[i][j] = null;
									matrix[j][i] = null;
								}
							}
						}
					}
				}
			}
		}

		public List<Object> getBestPath() {

			double bestValue = Double.NEGATIVE_INFINITY;
			List<Object> bestPath = new ArrayList<Object>();

			for (int i = 0; i < matrix.length; i++) {

				if (!bestPath.contains(i)) {

					Object[] resp = getPath(i);

					double value = (Double) resp[0];

					if (value > bestValue) {
						bestValue = value;
						bestPath = (List<Object>) resp[1];
					}
				}
			}

			for (int i = 0; i < bestPath.size(); i++) {
				bestPath.set(i, ((Integer) bestPath.get(i)) + 1);
			}
			return bestPath;

		}

		private Object[] getPath(int i) {

			Object[] resp = null;
			List<Object> path = new ArrayList<Object>();
			double score = 0.0;

			List<Integer> neighbors = getNeighbors(i);

			Set<Integer> visited = new HashSet<Integer>();
			if (neighbors.size() > 0) {
				visited.add(i);
				Object[] l = getPath(neighbors.get(0), visited);
				if (l == null) {
					path.add(i);
				} else {
					path = (List<Object>) l[1];
					path.add(0, i);
					score = (Double) l[0];
					score = score + matrix[i][(Integer) path.get(1)].realScore;
					Collections.reverse(path);
				}
			}

			if (neighbors.size() > 1) {
				visited.add(i);
				Object[] r = getPath(neighbors.get(1), visited);
				if (r != null) {
					path.addAll((List<Object>) r[1]);
					score = score + (Double) r[0];
				}
			}

			resp = new Object[] { score, path };

			return resp;
		}

		private Object[] getPath(Integer i, Set<Integer> visited) {

			visited.add(i);

			Object[] resp = null;
			List<Object> path = new ArrayList<Object>();
			double score = 0.0;

			List<Integer> neighbors = getNeighbors(i);

			for (Integer n : neighbors) {
				if (!visited.contains(n)) {
					resp = getPath(n, visited);
				}
			}

			if (resp == null) {
				path.add(i);
			} else {
				path = (List<Object>) resp[1];
				path.add(0, i);
				score = (Double) resp[0];
				score = score + matrix[i][(Integer) path.get(1)].realScore;
			}

			resp = new Object[] { score, path };

			return resp;
		}

		private double secondBestScore(int i, List<Integer> vertices) {

			double ret = -1;
			if (vertices.size() > 1) {
				double[] scores = new double[vertices.size()];

				for (int j = 0; j < vertices.size(); j++) {
					scores[j] = matrix[i][vertices.get(j)].realScore;
				}

				int[] sortedVertices = Utils.sort(scores);

				ret = matrix[i][vertices
						.get(sortedVertices[sortedVertices.length - 2])].realScore;
			}

			return ret;
		}

	}

}
