package csi403;

import java.util.Arrays;

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
