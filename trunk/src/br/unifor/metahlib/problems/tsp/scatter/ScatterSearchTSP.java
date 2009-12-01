package br.unifor.metahlib.problems.tsp.scatter;

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
		for(Edge e : union){
		
			if(aEdges.contains(e) && bEdges.contains(e)){
				intersectionCounter++;
			}
		}

		return union.size() - intersectionCounter;
	}

	@Override
	protected List<Variable> getVariables(List<Solution> solutions) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Solution mountSolution(List<Solution> refSet,
			List<Variable> variables, List<Double> scores) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean solutionContainsVariable(Solution solution, Variable var) {

		// TODO Auto-generated method stub
		return false;
	}
	
	private class Edge {

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

	@Override
	protected void improvement(List<Solution> solutions) {

		// TODO Auto-generated method stub
		
	}
}
