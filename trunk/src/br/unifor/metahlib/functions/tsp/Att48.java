package br.unifor.metahlib.functions.tsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

public class Att48 extends TSPProblemDefinition {
	
	public Att48(File tsp) throws IOException {
		super(tsp);
	}

	@Override
	protected double[][] buildDistanceMatrix(File tsp) throws IOException {
		Hashtable<Integer, String> cities = new Hashtable<Integer, String>();
		
		BufferedReader br = new BufferedReader(new FileReader(tsp));
		for(int i = 0; i < 6; i++){
			br.readLine();
		}
			
		String line = br.readLine();
		while(!line.trim().equalsIgnoreCase("EOF")){
			String[] s = line.split(" ");
			int id = Integer.parseInt(s[0]);
			
			String xy = s[1] + "," + s[2];
			
			cities.put(id, xy);
			
			line = br.readLine();
		}
		
		int size = cities.size();
		
		double[][] distanceMatrix = new double[size][size];
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				String si = cities.get(i + 1);
				String sj = cities.get(j + 1);
				
				String[] xyi = si.split(",");
				String[] xyj = sj.split(",");
				
				double xd = Double.parseDouble(xyi[0]) - Double.parseDouble(xyj[0]);
				double yd = Double.parseDouble(xyi[1]) - Double.parseDouble(xyj[1]);
				
				distanceMatrix[i][j] = Math.round(Math.sqrt(xd*xd + yd*yd));
			}
		}
		
		return distanceMatrix;
		
	}

	@Override
	public Att48 clone(){
		try {
			return new Att48(tsp);
		} catch (IOException e){
			return null;
		}
	}
}
