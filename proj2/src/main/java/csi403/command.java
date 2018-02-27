/**
 * 
 */
package csi403;

/**
 * @author lukep
 *
 */
public class command {
	String cmd;
	String name;
	int pri;
	
	/**
	 * @return the cmd
	 */
	public String getCmd() {
		return cmd;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the pri
	 */
	public int getPri() {
		return pri;
	}
	/**
	 * @param cmd the cmd to set
	 */
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param pri the pri to set
	 */
	public void setPri(int pri) {
		this.pri = pri;
	}
}
