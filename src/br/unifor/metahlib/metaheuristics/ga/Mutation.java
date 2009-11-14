package br.unifor.metahlib.metaheuristics.ga;

public abstract class Mutation {
    public abstract boolean mutate(Object[] genes, double mutationProbability);
}
