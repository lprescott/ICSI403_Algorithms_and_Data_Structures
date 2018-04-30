package csi403;

import java.util.ArrayList;

public class friend {
	
	String name;
	ArrayList<String> friends = new ArrayList<String>();
	
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
	public ArrayList<String> getFriends() {
		return friends;
	}
	
	/**
	 * @param friends the friends to set
	 */
	public void setFriends(ArrayList<String> friends) {
		this.friends = friends;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "friend [name=" + name + ", friends=" + friends + "]";
	}
	
	/**
	 * @param name
	 * @param friends
	 */
	public friend(String name, ArrayList<String> friends) {
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
	
	
	
}
