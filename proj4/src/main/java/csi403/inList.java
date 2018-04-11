//*******************************************************************
//@author: Luke R. Prescott
//
//@file: inList.java ~ The sorted inList object that will be supplied,
//						which contains coordinate objects.
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

/*
 * The sorted inList object that will be supplied.
 */
public class inList {
	
	private coordinate [] inList;
	
	//Constructors:
	/**
	 * @param commands
	 */
	public inList(coordinate [] inList) {
		this.inList = inList;
	}	
	public inList() {
		//Assign nothing
	}
	
	//Getters and setters:
	/**
	 * @return the inList
	 */
	public coordinate [] getInList() {
		return inList;
	}
	/**
	 * @param inList the inList to set
	 */
	public void setInList(coordinate [] inList) {
		this.inList = inList;
	}
	
	
	/*
	 * The coordinate object contains a int x and int y val.
	 */
	public static class coordinate {
        private int x;
        private int y;
		
		//Constructor(s)
		/**
		 * @param x
		 * @param y
		 */
		public coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		//Getters and setters
		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}
		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}
		/**
		 * @param x the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}
		/**
		 * @param y the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}
	}
}
