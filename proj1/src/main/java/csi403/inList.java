//*******************************************************************
//@author: Luke R. Prescott
//
//@file: inList.java ~ The unsorted inList object that will be supplied.
//
//CSI 403 – Spring 2018 – Project #1 – Sorting 
//
//Goal(s):
//(1) Establish your development and release environment to be used for class projects. 
//(2) Implement any one sorting algorithm (e.g. one from the Cormen text).
//
//Problem: 
//(1) Provide a RESTful service which accepts a POST of an array of integers in JSON format and returns the numbers as a JSON object in sorted order.  
//(2) Any sort algorithm may be used, but you must supply the source code for the sorting algorithm.
//
//  Example input:     { “inList”      :    [ 5, 35, 1, 272, 12, 0, -2, 12 ] } 
//  Example output:    { “outList”     :    [ -2, 0, 1, 5, 12, 12, 35, 272 ], 
//                       “algorithm”   :    “quicksort”, 
//                       “timeMS”      :    52 } 
//
//*******************************************************************

package csi403;

public class inList {
	//Properties
	private int [] inList;

	//Getter(s) and Setter(s)
	public int [] getInList() {
		return inList;
	}
	public void setInList(int [] inList) {
		this.inList = inList;
	}
	
	//Constructor(s)
	public inList (int [] list) {
		this.inList = list;
	}//End constructor(s)
	
	public inList() {
		//Assign nothing
	}
}
