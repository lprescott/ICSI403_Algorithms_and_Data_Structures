package csi403;

import java.util.Arrays;

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
