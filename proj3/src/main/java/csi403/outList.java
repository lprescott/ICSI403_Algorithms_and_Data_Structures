//*******************************************************************
//@author: Luke R. Prescott
//
//@file: outList.java ~ The outList object returned which will contain and ArrayList of ArrayList Strings that collided in the HashTable.
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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//Imported Libraries:
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

import com.fasterxml.jackson.databind.ObjectMapper;

/*

 */
@SuppressWarnings("unused")
public class outList {

	ArrayList<ArrayList<String>> outList = new ArrayList<ArrayList<String>>();
	
	//Constructors
	/**
	 * @param outList
	 */
	public outList(ArrayList<ArrayList<String>> outList) {
		this.outList = outList;
	}
	
	//This constructor accepts a inList object as a parameter, adding the inList's values to a HashTable using the hash 
	//	function. It then loop through the HashTable adding ArrayLists with length > 1 to the outlist that has to be returned.
	// 	These Lists with length > 1 are the HashTable's collisions.
	public outList(inList inList) {
		
		//Create a new HashTable with key value pairs of a Integer, and a ArrayList of strings
		Hashtable<Integer, ArrayList<String>> table = new Hashtable<Integer, ArrayList<String>>();
		
		int index;
		//Loop through the inList, adding its values to the HashTable
		for(String x : inList.getInList()) {
			index = hash(x);
			ArrayList<String> innerList = new ArrayList<String>();
			innerList.add(x);
			
			//Check if the HashTable has a collision or not
			if(table.containsKey(index)) {
				ArrayList<String> temp = new ArrayList<String>();
				temp = table.get(index);
				temp.addAll(innerList);
				table.replace(index, temp);
				
			} else {
				table.put(index, innerList);
			}
		}
		
		//Loop through the HashTable adding lists with collisions to the outputted outList
		for(Entry<Integer, ArrayList<String>> entry : table.entrySet()) {
			if(entry.getValue().size() == 1) {
				//System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue().get(0));
			} else {
				ArrayList<String> tempList = new ArrayList<String>();
				for (String temp : entry.getValue()) {
					tempList.add(temp);
					//System.out.println("Key: " + entry.getKey() + " Value: " + temp);
				}
				outList.add(tempList);	
			}	
		}
	}
	
	/**
	 * 
	 */
	public outList() {
	}

	//Getters and setters
	/**
	 * @return the outList
	 */
	public ArrayList<ArrayList<String>> getOutList() {
		return outList;
	}

	/**
	 * @param outList the outList to set
	 */
	public void setOutList(ArrayList<ArrayList<String>> outList) {
		this.outList = outList;
	}
	
	//This hash function fulfills the following requirements: 
	//	The hash algorithm should be implemented as the sum of the ASCII values of the lower-cased letters.  
	
	public static int hash(String inString) {
		int sum = 0; int ascii = 0;
		for(char x : inString.toLowerCase().toCharArray()) {
			ascii =  (int) x;
			sum += ascii;
		}
		return sum;
	}
	
	/**
	 * @param args
	 * 
	 * For testing puporses.
	 */
	public static void main(String[] args) {
		inList testInlist = new inList();
		
        String msg = "{\"inList\":[\"Bob\",\"boj\",\"obb\",\"job\",\"BOB\",\"foo\"]}";  

        System.out.println(msg);
        
        ObjectMapper mapper = new ObjectMapper();

        //Read in the inList and then construct the outList
        try {
        	testInlist = mapper.readValue(msg, inList.class);
		} catch (IOException e) {
			e.printStackTrace();
			
		}		
		outList testOut = new outList(testInlist);
		
		/*
		for(String x : testOut.getOutList()) {
			System.out.println("Project: " + x);
		}
		*/
		
        JsonSerializer serializer = new JsonSerializer();
		System.out.println(serializer.serialize(testOut));
	}
	

}
