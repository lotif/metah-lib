package br.unifor.metahlib.functions.tsp;

import java.io.File;
import java.io.IOException;

/**
 * Defines an instance of the Traveling Salesman Problem.
 * 
 * @author marcelo lotif
 *
 */
public abstract class TSPProblemDefinition {

	/**
	 * The distance matrix for all the cities of this TSP problem instance.
	 */
	protected double[][] distanceMatrix; 
	
	/**
	 * The TSP instance
	 */
	protected File tsp;
	
	/**
	 * The constructor of the class
	 * 
	 * @param tsp the file which contains the TSP definition
	 * @throws IOException
	 */
	public TSPProblemDefinition(File tsp) throws IOException {
		this.tsp = tsp;
		distanceMatrix = buildDistanceMatrix(tsp);
	}
	
	/**
	 * Populates the cities distance matrix depending on the TSP instance
	 * 
	 * @param tsp the TSP instance definition file
	 * @return the populated distance matrix
	 * @throws IOException
	 */
	protected abstract double[][] buildDistanceMatrix(File tsp) throws IOException; 
	
	/**
	 * Clones this instance
	 */
	public abstract TSPProblemDefinition clone();
	
	/**
	 * The method which calculates the distance between two cities
	 * 
	 * @param i the ID of the city of origin
	 * @param j the ID of the destination city
	 * @return the distance between the two cities
	 */
	public double getDistance(int i, int j){
		return distanceMatrix[i][j];
	}

	/**
	 * @return the number of cities in this instance
	 */
	public int getNumberOfCities(){
		return distanceMatrix.length;
	}
	
	public double[][] getDistanceMatrix() {
		return distanceMatrix;
	}

	public void setDistanceMatrix(double[][] distanceMatrix) {
		this.distanceMatrix = distanceMatrix;
	}

	public File getTsp() {
		return tsp;
	}

	public void setTsp(File tsp) {
		this.tsp = tsp;
	}
	
}
