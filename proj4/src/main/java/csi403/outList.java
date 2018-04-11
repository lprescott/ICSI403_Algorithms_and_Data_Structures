//*******************************************************************
//@author: Luke R. Prescott
//
//@file: outList.java ~ The outputted outList object which contains the count
//							of points contained within the polygon.
//
//CSI 403 – Spring 2018 – Project #4 – Points In Polygon
//
//Goal:
//	Given an ordered list of points which represent a polygon, return the 
//  number of points with integer (x, y) coordinates enclosed by the polygon.
//
//Problem: 
//	Provide a RESTful service which accepts as a POST of JSON an ordered 
//  list of points represented as (x, y) coordinates.  The points define 
//  the perimeter of a polygon.  
//
//  Return the number of points with integer (x, y) coordinates which are 
//  fully enclosed by the polygon.  Points on the polygon itself are not 
//  included in the count – the points to be included in the count must 
//  be wholly within the area bounded by the polygon.  
//
//  The grid is at maximum 19x19.  
//  You can assume all points on the polygon are positive integers between 0 and 
//  18 inclusive.
//	
//	Example input: 
//  { “inList” : 
//      [  
//          { “x” : 2,  “y” : 1 }, 
//          { “x” : 2,  “y” : 4 }, 
//          { “x” : 8,  “y” : 4 }, 
//          { “x” : 11, ”y” : 1 }		
//      ] 
//  } 
//
//	Example output: 	
//	{“count”:13} 
//	
//	Erroneous input (e.g. malformed JSON) should be handled gracefully.  
//
//*******************************************************************

package csi403;

//Imported Libraries:
import java.awt.geom.Line2D;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;
import csi403.inList.coordinate;

@SuppressWarnings("unused")
public class outList {

	private int count;
	
	//Constructors
	public outList() {
		//do nothiing
	}
	
	/*
	 * This constructor takes an inList object denoting a polygon and computes the number of 
	 * 	contained points by calling the contains method below (a ray casting approach)
	 */
	public outList(inList inList){
	
		this.count = 0;
		
		int minX = 18, maxX = 0;
		int minY = 18, maxY = 0;
		
		//Determine the maximum and minimum x and y values
		//	This minimizes the tests done.
		for(coordinate searcher : inList.getInList()) {
			if(searcher.getX() < minX) {
				minX = searcher.getX();
			}
			if(searcher.getY() < minY) {
				minY = searcher.getY();
			}
			if(searcher.getX() > maxX) {
				maxX = searcher.getX();
			}
			if(searcher.getY() > maxY) {
				maxY = searcher.getY();
			}
		}
		
		//List to contain all the points inside the polygon
		ArrayList <coordinate> inside = new ArrayList<coordinate>();
		
		//System.out.println("minX:" + minX + " minY:" + minY + " maxX:" + maxX + " maxY:" + maxY);
		
		//Loop through the min and max x and y values checking if they are contained in the polygon
		//	Incrementing the count if the function contains returns true
		for(int x = minX; x < maxX; x ++) {
			for(int y = minY; y < maxY; y++) {
				coordinate temp = new coordinate(x, y);
				if(contains(temp, inList.getInList())) {
					//System.out.println(temp);
					inside.add(temp);
				}
			}
		}
		
		//Increment the count ONLY if the point is inside the polygon and its distance to any line segment is never 0
		for(coordinate temp : inside) {
			if(!onEdge(temp, inList.getInList())) {
				//System.out.println(onEdge(temp, inList.getInList()));
				count ++;
			} else {
				//System.out.println(onEdge(temp, inList.getInList()));
				//do nothing
			}
		}
		
	}

	/*
	 * returns true if the supplied coordinate is on the edge of the polygon
	 * else returns false
	 */
	private boolean onEdge(coordinate temp, coordinate[] inList) {
		for(int i = 0; i < inList.length; i++) {
			
			//If the you are at the end of the list, link back to the beginning
			if(i != inList.length - 1) {
				coordinate p1 = inList[i];
				coordinate p2 = inList[i+1];
				Line2D.Double line = new Line2D.Double((double)p1.getX(), (double)p1.getY(), (double)p2.getX(), (double)p2.getY());
				if (line.ptLineDist(temp.getX(), temp.getY()) == 0) {
					return true;
				}
			//Else just use the next point to calculate the line segment
			} else {
				coordinate p1 = inList[i];
				coordinate p2 = inList[0];
				Line2D.Double line = new Line2D.Double((double)p1.getX(), (double)p1.getY(), (double)p2.getX(), (double)p2.getY());
				if (line.ptLineDist(temp.getX(), temp.getY()) == 0) {
					return true;
				}			
			}
		}
		return false;
	}

	//Getters and setters
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Returns true if the given point is contained inside the boundary, edges not included.
	 */
	public boolean contains(coordinate test, coordinate[] polygon) {
		
		//Initialized vars
		int i; int j;
		boolean result = false;
		
		//Loop through the vertices of the supplied polygon, use ray casting to determine if the 
		//	supplied point is valid.
		for (i = 0, j = polygon.length - 1; i < polygon.length; j = i++) {
			if ((polygon[i].getY() > test.getY()) != (polygon[j].getY() > test.getY()) && (test.getX() < (polygon[j].getX() - polygon[i].getX()) * (test.getY() - polygon[i].getY()) / (polygon[j].getY()-polygon[i].getY()) + polygon[i].getX())) {
				result = !result;
			}
		}
		return result;
	}
	
	/**
	 * @param args
	 * 
	 * For testing puporses.
	 */
	public static void main(String[] args) {
		inList testInlist = new inList();
		
        String msg = "{\"inList\":[{\"x\":2,\"y\":1},{\"x\":2,\"y\":4},{\"x\":8,\"y\":4},{\"x\":11,\"y\":1}]}";  
        
		System.out.println(msg);
        
        ObjectMapper mapper = new ObjectMapper();

        //Read in the inList and then construct the outList
        try {
        	testInlist = mapper.readValue(msg, inList.class);
		} catch (IOException e) {
			e.printStackTrace();
			
		}		
		outList testOut = new outList(testInlist);
		
        JsonSerializer serializer = new JsonSerializer();
		System.out.println(serializer.serialize(testOut));
	}

}
