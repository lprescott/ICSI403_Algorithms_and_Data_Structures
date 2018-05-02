//*******************************************************************
//@author: Luke R. Prescott
//
//@file: inList.java ~ The inList object that was supplied and discerned from JSON,
//						this is supplied to outList in its constructor
//
//CSI 403 – Spring 2018 – Project #5 – FOAFs - Friends of a Friend
//
//Goal:
//	Write an HTTP service which accepts a graph of people where edges represent friendship relationships.  
//	Return the suggested edges which would create direct friend relationships between two people who currently 
//	are at most one friend away – two people who are “friends of a friend” (FOAFs). 
//
//Problem: 
//	Receive as input an array of friend relationships.  
//	Return a set of arrays each representing a new suggested friend relationship.
//	
//Example input: 
//
//	{ “inList” : [ 
//			{ “friends” : [ “Albert”, “Betty” ] }, 
//			{ “friends” : [ “Betty”, “Cathy” ] }, 
//			{ “friends” : [ “Cathy”, “Denis” ] }, 
//			{ “friends” : [ “Denis”, “Albert” ] },
//			{ “friends” : [ “Tony”, “Bruce” ] }
//    ] } 
//
//Example output: 	
//
//	{ “outList” : [  [ “Albert”, “Cathy” ] , [  “Betty”, “Denis” ]  ]  } 
//
//		Do not duplicate the suggestions (in the example above, do not also suggest [ “Cathy”, “Albert” ].  
//	If no new friend relationships can be suggested, return the empty array.  { "outList" : [ ] }
//	Erroneous input (e.g. malformed JSON) should be handled gracefully.  
//
//*******************************************************************


package csi403;

import java.util.Arrays;

//inList ~ The inList object that was supplied and discerned from JSON,
//			this is supplied to outList in its constructor
public class inList {
	friendship [] inList;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "inList [inList=" + Arrays.toString(inList) + "]";
	}

	/**
	 * empty constructor
	 */
	public inList() {}
	
	/**
	 * @param inList
	 */
	public inList(friendship[] inList) {
		this.inList = inList;
	}

	/**
	 * @return the inList
	 */
	public friendship[] getInList() {
		return inList;
	}

	/**
	 * @param inList the inList to set
	 */
	public void setInList(friendship[] inList) {
		this.inList = inList;
	}
	
	
}
