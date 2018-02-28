//*******************************************************************
//@author: Luke R. Prescott
//
//@file: inList.java ~ The unsorted inList object that will be supplied,
//						which contains command objects.
//
//CSI 403 – Spring 2018 – Project #2 – Priority Queue 
//
//Goal:
//	To simulate a task management system with a priority queue.
//
//Problem: 
//	Provide a RESTful service which accepts as a POST of JSON a list of enqueue and dequeue statements onto an in-memory job queue.
//	Each job definition contains a name and a priority, with 0 being the best priority and positive integers representing lower priorities.
//	Return the JSON representing the state of the queue (the list of job names, in priority order), after all enqueue and dequeue statements have been processed.  
//	
//	Example input:    	
//	{ “inList” : 
//		[ 
//			{ “cmd” : “enqueue”, “name” : ”job1”, “pri” : 4 }, 
//		 	{ “cmd” : “enqueue”, “name” : ”job2”, “pri” : 3 },
//		  	{ “cmd” : “dequeue” },
//		  	{ “cmd” : “enqueue”, “name” : ”job3”, “pri” : 0 },
//		  	{ “cmd” : “enqueue”, “name” : ”job4”, “pri” : 1 },
//		  	{ “cmd” : “dequeue” }
//   	] 
//   } 
//	Example output: 	
//	{ “outList” : 
//		[ “job4”, “job1” ] 
//	} 
//	
//	Erroneous input (e.g. malformed JSON) should be handled gracefully.  
//
//*******************************************************************

package csi403;

/*
 * The unsorted inList object that will be supplied.
 */
public class inList {
	
	private command [] inList;
	
	//Constructors:
	/**
	 * @param commands
	 */
	public inList(command [] inList) {
		this.inList = inList;
	}	
	public inList() {
		//Assign nothing
	}
	
	//Getters and setters:
	/**
	 * @return the inList
	 */
	public command [] getInList() {
		return inList;
	}
	/**
	 * @param inList the inList to set
	 */
	public void setInList(command [] inList) {
		this.inList = inList;
	}
	
	
	/*
	 * The command object contains a cmd String, name String and int priority
	 * all which are parts of the standard supplied inList data types.
	 * 
	 * Three constructors have been created for all use cases.
	 */
	public static class command {
		private String cmd;
		private String name;
		private int pri;
		
		//Constructors
		/**
		 * @param cmd
		 * @param name
		 * @param pri
		 */
		public command(String cmd, String name, int pri) {
			this.cmd = cmd.toLowerCase();
			this.name = name.toLowerCase();
			this.pri = pri;
		}
		/**
		 * @param cmd
		 */
		public command(String cmd) {
			this.cmd = cmd.toLowerCase();
		}
		/**
		 * 
		 */
		public command() {
		}
		
		//Getters and setters
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
}
