package br.unifor.metahlib.metaheuristics.gls.tsp;

import java.util.List;

import br.unifor.metahlib.metaheuristics.gls.PenalizedFeatures;

public class TSPPenalizedFeatures implements PenalizedFeatures {

	private List<TSPFeature> penalizedFeatures;
	
	public TSPPenalizedFeatures(List<TSPFeature> penalizedFeatures){
		this.penalizedFeatures = penalizedFeatures;
	}
	
	@Override
	public double hasTheFeature(double[] solution, int featureIndex) {
		
		TSPFeature feature = penalizedFeatures.get(featureIndex);
		
		for(int i = 0; i < solution.length - 1; i++){
			if(solution[i] == feature.getI() || solution[i] == feature.getJ()){
				if(i == 0){
					if(solution[solution.length - 1] == feature.getI() || solution[solution.length - 1] == feature.getJ()){
						return feature.getCost();
					}
				}
				if(solution[i + 1] == feature.getI() || solution[i + 1] == feature.getJ()){
					return feature.getCost();
				}
			}
		}
		
		return 0;
	}

	public int getTotalFeatures(){
		return penalizedFeatures.size();
	}
	
}
