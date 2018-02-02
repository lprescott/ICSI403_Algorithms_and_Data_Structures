package csi403;

import java.io.Serializable;

public class outList implements Serializable{
	private int [] outList;
	private String algorithm;
	private int timeMS;
	
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public int getTimeMS() {
		return timeMS;
	}
	public void setTimeMS(int timeMS) {
		this.timeMS = timeMS;
	}
	public int [] getOutList() {
		return outList;
	}
	public void setOutList(int [] outList) {
		this.outList = outList;
	}
}
