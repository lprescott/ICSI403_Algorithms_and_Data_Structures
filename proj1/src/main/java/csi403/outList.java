//*******************************************************************
//@author: Luke R. Prescott
//
//@file: outList.java ~ The sorted (using insertionSort method) outList object that will be returned.
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

//Package:
package csi403;

//Libraries:
import java.io.Serializable;

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
	
	//Constructor(s):
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
	
	//This function sorts an inputed integer array by insertion, returning the sorted list.
	public static int[] sort(int inList[]){
		int temp; 
		//Traverse the inList.
		for (int x = 1; x < inList.length; x++) { 
			//Traverse the sorted part of the list (to the right).
			for(int z = x ; z > 0 ; z--){
				//If the value at pos. j < j - 1, swap them
				if(inList[z] < inList[z-1]){
					temp = inList[z];
					inList[z] = inList[z-1];
					inList[z-1] = temp;
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
		int testList[] = { 18, -21, -95, -13, -87, 17, -6, 24, -10, -37, -74, 
			-50, -41, -90, -83, 80, 78, 19, -28, 39, -47, 33, 5, 20, 44, -98, 
			96, 61, 73, 69, 76, 47, 2, -91, -56, 30, 65, -62, -53, 58, -46
		};
		
		//call on testList
		printList(testList);
		
		//Start the timer.
		System.out.println("\n\nStarting timer and insertionSort...");
		long startTime = System.nanoTime();
		
		//Call insertionSort.
		testList = sort(testList);
		
		//End the timer.
		System.out.println("Done...\n");
		long endTime = System.nanoTime();

		//Calculate and print the elapsed nanoseconds and milliseconds.
		long elapsedTime = endTime - startTime;
	    long milliseconds = elapsedTime / 1000000;
		System.out.println("Elapsed time in nanoseconds: " + elapsedTime + ", in milliseconds ("+elapsedTime+"/1000000): " + milliseconds);
		
		//Print the sorted list
		System.out.print("\nThe sorted list:\n");
		printList(testList);
	}
}
