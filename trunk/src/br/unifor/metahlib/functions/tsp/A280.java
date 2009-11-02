package br.unifor.metahlib.functions.tsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Implementation of the TSPProblemDefinition abstract class for the A280 TSP instance
 * 
 * @author marcelo lotif
 *
 */
public class A280 extends TSPProblemDefinition {

	public A280(File tsp) throws IOException {
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
			String s = "" + line.charAt(0) + line.charAt(1) + line.charAt(2);
			int id = Integer.parseInt(s.trim());
			
			String x = "" + line.charAt(4) + line.charAt(5) + line.charAt(6);
			String y = "" + line.charAt(8) + line.charAt(9) + line.charAt(10);
			
			String xy = Double.parseDouble(x.trim()) + "," + Double.parseDouble(y.trim());
			
			cities.put(id, xy);
			
			line = br.readLine();
		}
		
		int size = cities.size();
		
		double[][] distanceMatrix = new double[size][size];
		
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				String si = cities.get(i);
				String sj = cities.get(j);
				
				String[] xyi = si.split(",");
				String[] xyj = sj.split(",");
				
				double xd = Double.parseDouble(xyi[0]) - Double.parseDouble(xyj[0]);
				double yd = Double.parseDouble(xyi[1]) - Double.parseDouble(xyj[1]);
				
				distanceMatrix[i][j] = Math.sqrt(xd*xd + yd*yd);
			}
		}
		
		return distanceMatrix;
	}
	
	@Override
	public A280 clone(){
		try {
			return new A280(tsp);
		} catch (IOException e){
			return null;
		}
	}
}
