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
	protected Hashtable<Integer, String> buildCitiesTable(File tsp) throws IOException {
		cities = new Hashtable<Integer, String>();
		
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
		return cities;
	}
	
	@Override
	public double getDistance(int i, int j) {
		String si = cities.get(i);
		String sj = cities.get(j);
		
		String[] xyi = si.split(",");
		String[] xyj = sj.split(",");
		
		double xd = Double.parseDouble(xyi[0]) - Double.parseDouble(xyj[0]);
		double yd = Double.parseDouble(xyi[1]) - Double.parseDouble(xyj[1]);
		
		double dij = Math.sqrt(xd*xd + yd*yd);
		
		return Math.round(dij);
	}

}
