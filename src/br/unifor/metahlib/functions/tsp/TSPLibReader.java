package br.unifor.metahlib.functions.tsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * An universal reader for the TSPLib TSP instances
 * 
 * @author Paulo Moreno
 * @author Marcelo Lotif
 *
 */
public class TSPLibReader extends TSPProblemDefinition {
	
	/**
	 * Contructor of the class
	 * 
	 * @param tsp the file where the TSP instance data is stored
	 * @throws IOException
	 */
	public TSPLibReader(File tsp) throws IOException {
		super(tsp);
	}

	/**
	 * Populates the cities distance matrix depending on the TSP instance
	 * 
	 * @param tsp the TSP instance definition file
	 * @return the populated distance matrix
	 * @throws IOException
	 */
	@Override
	protected double[][] buildDistanceMatrix(File tsp) throws IOException {
		Hashtable<Integer, String> cities = new Hashtable<Integer, String>();
		
		BufferedReader br = new BufferedReader(new FileReader(tsp));
			
		String line = br.readLine();
		
		while(!line.trim().equals("NODE_COORD_SECTION")){
			line = br.readLine();
		}
		
		line = br.readLine();
		while(!line.trim().equalsIgnoreCase("EOF")){
			String[] s = line.split("\\s");
			double[] values = new double[3];
			
			int i = 0;
			for(int k = 0; k < s.length; k++){
				if(i < 3){
					try{
						values[i] = Double.parseDouble(s[k]);;
						i++;					
					} catch (NumberFormatException e) { }
				}
			}
			
			int id = (int)values[0];
			
			String xy = values[1] + "," + values[2];
			
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

	/**
	 * Clones this instance
	 * 
	 * @return a clone instance of this class 
	 */
	@Override
	public TSPLibReader clone(){
		try {
			return new TSPLibReader(tsp);
		} catch (IOException e){
			return null;
		}
	}
	
}
