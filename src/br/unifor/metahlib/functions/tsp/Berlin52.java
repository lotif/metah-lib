package br.unifor.metahlib.functions.tsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Implementation of the TSPProblemDefinition abstract class for the Berlin52 TSP instance
 * 
 * @author marcelo lotif
 *
 */
public class Berlin52 extends TSPProblemDefinition {

	public Berlin52(File tsp) throws IOException {
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
			String[] s = line.split(" ");
			int id = Integer.parseInt(s[0]);
			
			String xy = s[1] + "," + s[2];
			
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
