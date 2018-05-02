//*******************************************************************
//@author: Luke R. Prescott
//
//@file: friend.java ~ The inList object that will be supplied,
//						which contains friendship objects.
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

import java.util.ArrayList;

//friend ~ The inList object that will be supplied,
//			which contains friendship objects.
public class friend {
	
	//Each friend has a name and a list of friends
	String name;
	ArrayList<friend> friends = new ArrayList<friend>();
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the friends
	 */
	public ArrayList<friend> getFriends() {
		return friends;
	}
	
	/**
	 * @param friends the friends to set
	 */
	public void setFriends(ArrayList<friend> friends) {
		this.friends = friends;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "friend [name=" + name + "]";
	}
	
	/**
	 * @param name
	 * @param friends
	 */
	public friend(String name, ArrayList<friend> friends) {
		this.name = name;
		this.friends = friends;
	}
	
	/**
	 * @param name
	 */
	public friend(String name) {
		this.name = name;
	}
	
	/**
	 * empty constructor
	 */
	public friend() {
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 * This is an auto-generated (by eclipse) equals method for friend objects
	 */
	@Override
	public boolean equals(Object obj) {
		//Refs
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		//Classes
		if (getClass() != obj.getClass())
			return false;
		
		//Cast then attributes
		friend other = (friend) obj;
		if (friends == null) {
			if (other.friends != null)
				return false;
		} 
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	
}
