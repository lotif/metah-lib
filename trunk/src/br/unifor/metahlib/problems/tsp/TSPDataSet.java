package br.unifor.metahlib.problems.tsp;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * TSP problem dataSet. The properties of this class represents the TSPLib file content.
 */
public class TSPDataSet {
	
    /**
     * Supported edged weight types.  
     */
	static public enum EdgeWeightType { GEO, EUC_2D };
    
	/**
	 * Coordinates of cities. 
	 */
	Vector<Point2D.Double> cities = new Vector<Point2D.Double>();
	
    /**
     * DataSet name.
     */
	String name;
	
	/**
	 * Comments about this dataSet.
	 */
    String comment;
    
    /**
     * Quantity of cities.
     */
    int dimension;
    
    /**
     * Format of edge weight. Determines how distances are calculated.  
     */
    EdgeWeightType edgeWeightType;
    
    /**
     * Cities distances matrix. Zero-indexed arrays.
     */
    int[][] distances;
    
    /**
     * Constructs a new TSPDataSet from the informed TSPLib file.
     * @param file TSPlib file
     * @throws IOException
     * @throws EdgeWeightTypeNotSupported
     */
    public TSPDataSet(File file) throws IOException, EdgeWeightTypeNotSupported{
        loadFile(file);
        distances = calcDistances();
    }
    
    /**
     * Reads TSPlib file contents.
     * @param file TSPlib file
     * @throws IOException
     * @throws EdgeWeightTypeNotSupported
     */
    private void loadFile(File file) throws IOException, EdgeWeightTypeNotSupported{

        /* Format TSP file
        NAME: ulysses16.tsp
        TYPE: TSP
        COMMENT: Odyssey of Ulysses (Groetschel/Padberg)
        DIMENSION: 16
        EDGE_WEIGHT_TYPE: GEO
        DISPLAY_DATA_TYPE: COORD_DISPLAY
        NODE_COORD_SECTION
         1 38.24 20.42
         2 39.57 26.15
         3 40.56 25.32
         4 36.26 23.12
         5 33.48 10.54
         6 37.56 12.19
         7 38.42 13.11
         8 37.52 20.44
         9 41.23 9.10
         10 41.17 13.05
         11 36.08 -5.21
         12 38.47 15.13
         13 38.15 15.35
         14 37.51 15.17
         15 35.49 14.32
         16 39.36 19.56
         EOF
         */

    	BufferedReader reader = new BufferedReader(new FileReader(file));
        boolean readingHeader = true;
        double x, y;
        String headerName;
        String headerValue;
        String[] items;
        String line = reader.readLine().trim();
        while (!line.isEmpty() && !line.equals("EOF")){
            if (readingHeader){
                items= line.split(":");
                headerName = items[0].toUpperCase();
                headerValue = items.length > 1 ? items[1].trim() : "";
                if (headerName.equals("NAME")){
                    name = headerValue;
                } else if (headerName.equals("DIMENSION")){
                    dimension = Integer.parseInt(headerValue);
                } else if (headerName.equals("EDGE_WEIGHT_TYPE")){
                	try {
                		edgeWeightType = EdgeWeightType.valueOf(headerValue);
                	} catch (IllegalArgumentException e){
                		throw new EdgeWeightTypeNotSupported(headerValue);
                	}
                } else if (headerName.equals("NODE_COORD_SECTION")){
                    readingHeader = false;
                }

            } else {
                items = line.split("\\s");
                assert( cities.size() + 1 == Integer.parseInt(items[0]));
                x = Double.parseDouble(items[1]);
                y = Double.parseDouble(items[2]);
                cities.add(new Point2D.Double(x, y));
            }

            line = reader.readLine().trim();
        }

        assert( dimension == cities.size());
    }

    /**
     * Constant specified in TSPLib documentation to calculate geographics coordinates.
     */
    static private double RRR = 6378.388;

    /**
     * Constant PI. Used the same precision of TSPLib documentation.
     */
    static private double PI = 3.141592;
    
    /**
     * Converts a degree coordinate to a radian coordinate. 
     * @param coordinate degree coordinate
     * @return radian coordinate
     */
    private double degreeToRadian(double coordinate){
    	int deg = (int) coordinate;
    	double min = coordinate - deg;
    	double rad = (PI * (deg + (5 * min)/3)) / 180;
    	return rad;
    }
    
    /**
     * Round function specified in TSPLib documentation.
     * @param d value to round
     * @return rounded value
     */
    private int nint(double d){
    	int r;
    	if (d >= 0){
    		r = (int)(d+0.5);
    	} else {
    		r = (int)(d-0.5);
    	}
    	return r;
    }
    
	/**
	 * Calculates the distance between two cities. The first city index is 1.
	 * The distances are integer values as specified by the TSPLib documentation. 
	 * @param i the index of the city of origin
	 * @param j the index of the destination city
	 * @return distance between the two cities
	 */
    private int calcDistance(int i, int j){
    	int distance;
    	Point2D.Double p1 = cities.get(i-1);
    	Point2D.Double p2 = cities.get(j-1);
    	switch (edgeWeightType){
    		case EUC_2D:
    			double xd = p1.x - p2.x;
    			double xy = p1.y - p2.y;
    			distance = nint(Math.sqrt(xd*xd + xy*xy));
    			break;
    	
    		case GEO:
    			 double q1 = Math.cos(degreeToRadian(p1.y) - degreeToRadian(p2.y)); 
    			 double q2 = Math.cos(degreeToRadian(p1.x) - degreeToRadian(p2.x)); 
    			 double q3 = Math.cos(degreeToRadian(p1.x) + degreeToRadian(p2.x)); 
    			 distance = (int) ( RRR * Math.acos( 0.5*((1.0+q1)*q2 - (1.0-q1)*q3) ) + 1.0);
    			break;
    	
    		default:
    			distance = -1;
    			assert(false);    			
    	}
    	
    	return distance;
    }
    
	/**
	 * Calculates distances between cities.
	 * @return distance matrix between cities.
	 */
    private int[][] calcDistances(){
    	int[][] distances = new int[dimension][dimension];
    	int d;
    	for (int i = 1; i <= dimension; ++i){
    		for(int j = 1; j <= dimension; ++j){
    			d = i == j ? 0 : calcDistance(i,j); 
    			distances[i-1][j-1] = d;
    		}
    	}
    	
    	return distances;
    }

    /**
     * Name of dataSet.
     */
    public String getName(){
        return name;
    }

	/**
	 * Comments about this dataSet.
	 */
    public String getComment(){
        return comment;
    }

    /**
     * Returns the quantity of cities.
     */
    public int getDimension(){
        return dimension;
    }

	/**
	 * Returns the cities coordinates. 
	 */
    public Vector<Point2D.Double> getCities(){
        return cities;
    }
    
	/**
	 * Returns the distance between two cities. The first city index is 1.
	 * The distances are integer values as specified by the TSPLib documentation. 
	 * @param i the index of the city of origin
	 * @param j the index of the destination city
	 * @return distance between the two cities
	 */
    public int getDistance(int i, int j) {
    	return distances[i-1][j-1];
    }
}
