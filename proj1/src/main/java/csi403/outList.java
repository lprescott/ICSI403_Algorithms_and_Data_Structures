//*******************************************************************
//@author: Luke R. Prescott
//
//@file: outList.java ~ The sorted (using insertionSort) outList object that will be returned.
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

import java.io.Serializable;
import java.lang.Math;

public class outList implements Serializable{
	//Properties
	private int [] outList;
	private String algorithm;
	private long timeMS;
	
	//Getter(s) and Setter(s)
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public long getTimeMS() {
		return timeMS;
	}
	public void setTimeMS(long timeMS) {
		this.timeMS = timeMS;
	}
	public int [] getOutList() {
		return outList;
	}
	public void setOutList(int [] outList) {
		this.outList = outList;
	}
	
	//Constructor(s)
	public outList (inList list) {
		//Attributes
		long startTime, endTime, elapsedTime;
		
		//Hard-code algorithm name, "algorithm"
		this.algorithm = "insertion sort";
		
		//Start timer and then call insertionSort, assign the newly sorted list to outList
		startTime = System.currentTimeMillis();
		this.outList = sort(list.getInList());
		
		//End timer, calculate elapsed time, and then assign "timeMS" with elapsed 
		endTime = System.currentTimeMillis();
	    elapsedTime = endTime - startTime;
	    this.timeMS = elapsedTime;
	}
	
	public outList() {
		//Assign nothing
	}//End constructor(s)
	
	//This function sorts and inputed integer array by insertion sort.
	public static int[] sort(int inList[]){
	  int temp; 
	  //Traverse the inList.
	  for (int i = 1; i < inList.length; i++) { 
	      //Traverse the sorted part of the list (to the right).
	      for(int j = i ; j > 0 ; j--){
	          //If the value at pos. j < j - 1, swap them
	          if(inList[j] < inList[j-1]){
	                 temp = inList[j];
	                 inList[j] = inList[j-1];
	                 inList[j-1] = temp;
	             }
	         }
	     }
	     return inList;
	}

	 //This function prints the inputed list on one line for testing.
	 public static void printList(int list[]){
	     for (int x = 0; x < list.length; x++){
	         System.out.print(list[x] + " "); 
	     }
	 }

	 //The main method to call insertionSort, and printList methods.
	 public static void main(String args []){
	     
	     //Create and print a list for testing.
	     System.out.print("The unsorted list:\n");
	     int testList[] = {13, 2, 1, -5, 6, 4, 15};
	     printList(testList);
	     
	     //Start the timer.
	     System.out.println("\n\nStarting timer and insertionSort...");
	     long startTime = System.nanoTime();
	     
	     //Call insertionSort.
	     testList = sort(testList);
	     
	     //End the timer.
	     System.out.println("Done...\n");
	     long endTime = System.nanoTime();

	     //Calculate the time elapsed.
	     long elapsedTime = endTime - startTime;
	     
	     double milliseconds = (elapsedTime/1000000);
	     
	     //Print time elapsed and sorted list.
	     System.out.println("Elapsed time in milliseconds: " + milliseconds);
	     
	     System.out.print("The sorted list:\n");
	     printList(testList);
	 }
}
