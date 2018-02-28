//*******************************************************************
//@author: Luke R. Prescott
//
//@file: outList.java ~ The sorted outList object that will be returned.
//
//CSI 403 – Spring 2018 – Project #2 – Priority Queue 
//
//Goal:
//	To simulate a task management system with a priority queue.
//
//Problem: 
//	Provide a RESTful service which accepts as a POST of JSON a list of enqueue and dequeue statements onto an in-memory job queue.1  
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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
//Imported Libraries:
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import com.fasterxml.jackson.databind.ObjectMapper;

import csi403.inList.command;

/*
 * The sorted outList object that will be returned
 * 	which contains a arraylist of strings for easy returning 
 * 	after sorting in pri-queue.
 */
public class outList {
	
	ArrayList<String> outList = new ArrayList<String>(7);
	
	//Constructors
	/**
	 * 
	 */
	public outList() {
	}

	/**
	 * @param outList
	 */
	public outList(ArrayList<String> outList) {
		this.outList = outList;
	}
	
	/*
	 * This constructor accepts input from the user supplied inList, adding job objects with 
	 * 	the respective data to a priority queue object, then assigning this.outList's ArrayList
	 * 	object to the in-order polling of the priority queue.
	 */
	public outList(inList inList) {
		
		//Create the priority queue using an initial size of 7, and the defined comparator below.
		Queue<job> PriorityQueue = new PriorityQueue<>(7, comparePriority);
		
		//Loop through the inList's commands enqueueing and dequeueing to the p.q. when required
		for(command x : inList.getInList()) {
			if (x.getCmd().toLowerCase().equals("enqueue")) {
				job addJob = new job(x.getName(), x.getPri());
				PriorityQueue.add(addJob);
			} else if (x.getCmd().toLowerCase().equals("dequeue")) {
				PriorityQueue.poll();
			}
		}
		
		//Poll through the p.q. ending when null, or assigning outLists ArrayList to the inordered polling
		//	of the p.q.
		while(true){
			job jobSearch = PriorityQueue.poll();
			if(jobSearch == null) 
				break;
			else
				this.getOutList().add(jobSearch.getName());
			/*
			System.out.println("Project: " + jobSearch.getName() 
				+ " | Priority: " + jobSearch.getPri());
			*/
		}
		
	}
	
	//This overrides the compare function, that is to be passed to the priority queue;
	//	it compares job objects by their priority.
	public static Comparator<job> comparePriority = new Comparator<job>(){
		
		@Override
		public int compare(job j1, job j2) {
            return (int) (j1.getPri() - j2.getPri());
        }
	};

	//Getters and setters
	/**
	 * @return the outList
	 */
	public ArrayList<String> getOutList() {
		return outList;
	}

	/**
	 * @param outList the outList to set
	 */
	public void setOutList(ArrayList<String> outList) {
		this.outList = outList;
	}

	
	/*
	 * The job object only contains a String name, and intPriority. This is the data type that is to 
	 * 	sorted in the priority queue. 
	 */
	public static class job {
		private String name;
		private int pri;
		
		//Constructors
		/**
		 * @param name
		 * @param pri
		 */
		public job(String name, int pri) {

			this.name = name.toLowerCase();
			this.pri = pri;
		}
		/**
		 * 
		 */
		public job() {
		}
		
		//Getters and setters
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

	/**
	 * @param args
	 * 
	 * For testing puporses.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inList testInlist = new inList();
		
        String msg = "{\"inList\":["
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job6\",\"pri\":5},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job2\",\"pri\":1},"
        		+ "{\"cmd\":\"dequeue\"},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job1\",\"pri\":0},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job3\",\"pri\":2},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job5\",\"pri\":4},"
        		+ "{\"cmd\":\"dequeue\"},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job7\",\"pri\":6},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job4\",\"pri\":3}]}";
        System.out.println(msg);
        
        ObjectMapper mapper = new ObjectMapper();

        //Read in the inList and then construct the outList
        try {
        	testInlist = mapper.readValue(msg, inList.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		outList testOut = new outList(testInlist);
		
		/*
		for(String x : testOut.getOutList()) {
			System.out.println("Project: " + x);
		}
		*/
		
        JsonSerializer serializer = new JsonSerializer();
		System.out.println(serializer.serialize(testOut));
	}

	
}
