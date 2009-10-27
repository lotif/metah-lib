package br.unifor.metahlib.functions.tsp;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Defines an instance of the Traveling Salesman Problem.
 * 
 * @author marcelo lotif
 *
 */
public abstract class TSPProblemDefinition {

	/**
	 * A hash table of cities of a TSP instance.
	 * The key is the "ID" of the city, usually a sequential ID.
	 * The value is a list of comma separated values of the 2-D coordinates of the city 
	 * specified by the key. 
	 */
	protected Hashtable<Integer, String> cities;
	
	/**
	 * The constructor of the class
	 * 
	 * @param tsp the file which contains the TSP definition
	 * @throws IOException
	 */
	public TSPProblemDefinition(File tsp) throws IOException {
		buildCitiesTable(tsp);
	}
	
	/**
	 * Populates the cities hash table depending on the TSP instance
	 * 
	 * @param tsp the tsp instance definition file
	 * @return the populated hash table 
	 * @throws IOException
	 */
	protected abstract Hashtable<Integer, String> buildCitiesTable(File tsp) throws IOException; 
	
	/**
	 * The method which calculates the distance between two cities
	 * 
	 * @param i the ID of the city of origin
	 * @param j the ID of the destination city
	 * @return the distance between the two cities
	 */
	public abstract double getDistance(int i, int j);

	/**
	 * @return the number of cities in this instance
	 */
	public int getNumberOfCities(){
		return cities.size();
	}
	
}
