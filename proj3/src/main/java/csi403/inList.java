//*******************************************************************
//@author: Luke R. Prescott
//
//@file: inList.java ~ The inList object supplied which will contain an ArrayList of Strings to be added to a HashTable.
//
//CSI 403 – Spring 2018 – Project #3 – HashCollisions 
//
//Goal:
//	Write an HTTP service which accepts an array of strings and returns the sets of strings which collide in the hash table in the order they appeared in the input list.
//
//Problem: 
//	Receive as input an array of strings.  
//	Return the set of strings which collide in the hash table in the order they appear in the input list.  
//	The hash algorithm should be implemented as the sum of the ASCII values of the lower-cased letters.  For example, “Bob”, “bob”, and “obb” all hash to the same number.
//	
//Example input:    	
//	{  
//  	"inList":[  
//       	"Bob",
//     	 	"boj",
//     	 	"obb",
//     	 	"job",
//     	 	"BOB",
//     	 	"foo"
//   	]
//	}
//	{
//   	"outList":[
//      	[
//         		"Bob",
//         		"obb",
//         		"BOB"
//      	],
//      	[
//         		"boj",
//         		"job"
//      	]
//   	]
//	}
//	
//	Erroneous input (e.g. malformed JSON) should be handled gracefully.  
//
//*******************************************************************

package csi403;
import java.util.ArrayList;

/*
 * The unsorted inList object that will be supplied.
 */
public class inList {
	
	ArrayList<String> inList = new ArrayList<String>(7);
	
	//Constructors:
	/**
	 * @param commands
	 */
	public inList(ArrayList <String> inList) {
		this.inList = inList;
	}	
	public inList() {
		//Assign nothing
	}
	
	//Getters and setters:
	/**
	 * @return the inList
	 */
	public ArrayList<String> getInList() {
		return inList;
	}
	/**
	 * @param inList the inList to set
	 */
	public void setInList(ArrayList<String> inList) {
		this.inList = inList;
	}
	
	
}
