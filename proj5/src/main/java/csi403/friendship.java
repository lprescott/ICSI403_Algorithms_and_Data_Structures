//*******************************************************************
//@author: Luke R. Prescott
//
//@file: friendship.java ~ The friendship object that will be supplied,
//						which was contained in the inList object object.
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

//friendship ~ The friendship object that will be supplied,
//	which was contained in the inList object object.
public class friendship {
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "friends [friends=" + Arrays.toString(friends) + "]";
	}

	String [] friends;

	/**
	 * @return the friends
	 */
	public String[] getFriends() {
		return friends;
	}

	/**
	 * @param friends the friends to set
	 */
	public void setFriends(String[] friends) {
		this.friends = friends;
	}

	/**
	 * @param friends
	 */
	public friendship(String[] friends) {
		this.friends = friends;
	}

	/**
	 * empty constructor
	 */
	public friendship() {}


}
