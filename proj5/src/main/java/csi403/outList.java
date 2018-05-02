//*******************************************************************
//@author: Luke R. Prescott
//
//@file: outList.java ~ The outList object that will be returned,
//						which contains only the names of people to 
//						that have been deemed possible friends.
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

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

//outList ~ The outList object that will be returned,
//					which contains only the names of people to 
//					that have been deemed possible friends.
public class outList {
	
	//outList is an arraylist of arraylists containing strings
	//	as to correctly return in the defined form
	ArrayList<ArrayList<String>> outList = new ArrayList<ArrayList<String>>();
	
	/**
	 * @param args
	 * 
	 * For testing puporses.
	 */
	public static void main(String[] args) {
		inList testInlist = new inList();
		
        String msg = "{\"inList\":[{\"friends\":[\"Albert\", \"Betty\"]},{\"friends\":[\"Betty\", \"Cathy\"]},{\"friends\":[\"Cathy\", \"Denis\"]},{\"friends\":[\"Denis\", \"Albert\"]},{\"friends\":[\"Tony\", \"Bruce\"]}]}";  

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

	
	/**
	 * This constructor find this possible friendships to be returned, and 
	 * 	relies on the contains method; see below.
	 * 
	 * First outList analyzes inList, creating the necessary friend objects and 
	 * 	assigning the correct friends to be on their friends list. 
	 * 	This essentially creates a graph structure.
	 * 
	 * Second the outList method loops through the newly created array of 
	 * 	friend objects, searching for friends of friends that are one degree away.
	 * 	Fulfilling the requirement of not already being a friend, and not being them
	 * 	selves.
	 * 
	 * @param inList is supplied after being discerned from JSON
	 */
	public outList(inList inList) {
		
		//Create new ArrayList object that holds friend objects
		ArrayList<friend> friends = new ArrayList<friend>();
		
		//Loop through the inList, creating and adding all people to friends ArrayList while assigning friendships
		for(friendship searcher : inList.getInList()) {
			
			//Initializations of friend objects used to add to friends list
			friend tempA = new friend(searcher.getFriends()[0]);
			friend tempB = new friend(searcher.getFriends()[1]);
			friend finder;
			
			//Set tempA and B's friends
			tempA.getFriends().add(tempB);
			tempB.getFriends().add(tempA);

			//Check if tempA exists in friends list and proceed accordingly
			if((finder = contains(friends, tempA)) == null) {
				friends.add(tempA);
			} else {
				finder.getFriends().add(tempB);
			}
			
			//Check if tempB exists in friends list and proceed accordingly
			if((finder = contains(friends, tempB)) == null) {
				friends.add(tempB);
			} else {
				finder.getFriends().add(tempA);
			}
		}
		
		//Create a new array of friends, copy data from friends ArrayList
		friend [] friendsArray = friends.toArray(new friend[friends.size()]);
		
		//This loops through all friends, their friendships, then their friendships
		//	looking for possible friendships (people with one degree of separation, 
		//	that are not on their friends list)
		for(int x = 0; x < friends.size(); x++) {
			
			//System.out.println("\n" + friendsArray[x].getName());
			for(int y = 0; y < friendsArray[x].getFriends().size(); y++) {
				
				//System.out.println("\t" + friendsArray[x].getFriends().get(y).getName());
				for(int z = 0; z < friendsArray[x].getFriends().get(y).getFriends().size(); z++) {
					
					//System.out.println("\t\t" + friendsArray[x].getFriends().get(y).getFriends().get(z).getName());
					//System.out.println("\t\t\t" + !friendsArray[x].getFriends().get(y).getFriends().get(z).equals(friendsArray[x]));
					
					//This checks if the current person found is not friends yet with the current friend
					if((!friendsArray[x].getFriends().get(y).getFriends().get(z).equals(friendsArray[x]))) {
						
						//These two arraylists contain both possible ordering (John, Tim) vs. (Tim, John) as to
						//	make sure that either do not already exist in the outlist to be returned
						ArrayList<String> origin = new ArrayList<String>();
						origin.add(friendsArray[x].getFriends().get(y).getFriends().get(z).getName());
						origin.add(friendsArray[x].getName());
				
						ArrayList<String> flipped = new ArrayList<String>();
						flipped.add(friendsArray[x].getName());
						flipped.add(friendsArray[x].getFriends().get(y).getFriends().get(z).getName());
						
						//This makes sure that either ordering does not already exist in the outlist
						if(!((this.getOutList().contains(origin)) || (this.getOutList().contains(flipped)))){
							
							//A possible friendship has been found, add them to the returnable
							ArrayList<String> temp = new ArrayList<String>();
							temp.add(friendsArray[x].getName());
							temp.add(friendsArray[x].getFriends().get(y).getFriends().get(z).getName());
							this.getOutList().add(temp);
						}
					}
				}
			}
		}
	}

	/**
	 * @param outList
	 */
	public outList(ArrayList<ArrayList<String>> outList) {
		this.outList = outList;
	}
	
	/**
	 * empty constructor
	 */
	public outList() {
	}

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "outList [outList=" + outList + "]";
	}
	
	/**
	 * The method contains is used to determine if the supplied ArrayList (first param)
	 * 	contains the supplied friend (second param). 
	 * 
	 * It does so by using an advanced forloop, and only returning the searcher friend when 
	 * 	it finds equality.
	 * 
	 * @param listoffriends
	 * @param friend
	 * @return null if there is nothing found, or friend object if found
	 */
	public friend contains(ArrayList<friend> listoffriends, friend friend) {
		for(friend searcher : listoffriends) {
			if(searcher.equals(friend)) {
				return searcher;
			}
		} 	
		return null;
	}	
}
