package csi403;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class outList {
	
	ArrayList<ArrayList<String>> outList = new ArrayList<ArrayList<String>>();
	
	/**
	 * @param inList
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
			tempA.getFriends().add(tempB.getName());
			tempB.getFriends().add(tempA.getName());

			//Check if tempA exists in friends list and proceed accordingly
			if((finder = contains(friends, tempA)) == null) {
				friends.add(tempA);
			} else {
				finder.getFriends().add(tempB.getName());
			}
			
			//Check if tempB exists in friends list and proceed accordingly
			if((finder = contains(friends, tempB)) == null) {
				friends.add(tempB);
			} else {
				finder.getFriends().add(tempA.getName());
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

	public friend contains(ArrayList<friend> friends, friend friend) {
		for(friend searcher : friends) {
			if(searcher.getName().equals(friend.getName())) {
				return searcher;
			}
		} 
		
		return null;
	}
	
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

}
