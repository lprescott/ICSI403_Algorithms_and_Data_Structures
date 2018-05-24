# I CSI 403 Algorithms and Data Structures (3)
Description of common data structures such as lists, push-down stores, queues, trees, and graphs. Definition of algorithm efficiency and efficient algorithms for integer and polynomial arithmetic, sorting, set manipulation, shortest paths, pattern matching, and Fourier transforms.

## proj0:
A boilerplate for future projects, proj0 implements the jackson library to map and serialize json to and from java objects. This notion will be used for all below projects.

## proj1 Sorting:
Goal(s):
1. Establish your development and release environment to be used for class projects. 
2. Implement any one sorting algorithm (e.g. one from the Cormen text).

Problem: 
1. Provide a RESTful service which accepts a POST of an array of integers in JSON format and returns the numbers as a JSON object in sorted order.  
2.  Any sort algorithm may be used, but you must supply the source code for the sorting algorithm.

    Example input:     
    { 
        “inList”      :    [ 5, 35, 1, 272, 12, 0, -2, 12 ] 
    } 
    
    Example output:    
    { 
        “outList”     :    [ -2, 0, 1, 5, 12, 12, 35, 272 ], 
        “algorithm”   :    “quicksort”, 
        “timeMS”      :    52 
    } 

## proj2 Priority Queue:
Goal:
1. To simulate a task management system with a priority queue.

Problem: 
1. Provide a RESTful service which accepts as a POST of JSON a list of enqueue and dequeue statements onto an in-memory job queue.
2. Each job definition contains a name and a priority, with 0 being the best priority and positive integers representing lower priorities.
3. Return the JSON representing the state of the queue (the list of job names, in priority order), after all enqueue and dequeue statements have been processed.  
	
	Example input:    	
	{ “inList” : 
		[ 
			{ “cmd” : “enqueue”, “name” : ”job1”, “pri” : 4 }, 
		 	{ “cmd” : “enqueue”, “name” : ”job2”, “pri” : 3 },
		  	{ “cmd” : “dequeue” },
		  	{ “cmd” : “enqueue”, “name” : ”job3”, “pri” : 0 },
		  	{ “cmd” : “enqueue”, “name” : ”job4”, “pri” : 1 },
		  	{ “cmd” : “dequeue” }
   	    ] 
    } 
	
    Example output: 	
	{ “outList” : 
		[ “job4”, “job1” ] 
	} 

## proj3 HashCollisions:
Goal:
	Write an HTTP service which accepts an array of strings and returns the sets of strings which collide in the hash table in the order they appeared in the input list.

Problem: 
	Receive as input an array of strings.  
	Return the set of strings which collide in the hash table in the order they appear in the input list.  
	The hash algorithm should be implemented as the sum of the ASCII values of the lower-cased letters.  For example, “Bob”, “bob”, and “obb” all hash to the same number.
	
    Example input:    	
	{ "inList":
        [ "Bob", "boj", "obb", "job", "BOB", "foo"]
	}

    Example output: 	
    {"outList":
        [
            [
                "Bob",
                "obb",
                "BOB"
            ],
            [
                "boj",
                "job"
            ]
    	]
	}

## proj4 Points In Polygon:
Goal:
	Given an ordered list of points which represent a polygon, return the 
  number of points with integer (x, y) coordinates enclosed by the polygon.

Problem: 
	Provide a RESTful service which accepts as a POST of JSON an ordered 
  list of points represented as (x, y) coordinates.  The points define 
  the perimeter of a polygon.  

  Return the number of points with integer (x, y) coordinates which are 
  fully enclosed by the polygon.  Points on the polygon itself are not 
  included in the count – the points to be included in the count must 
  be wholly within the area bounded by the polygon.  

  The grid is at maximum 19x19.  
  You can assume all points on the polygon are positive integers between 0 and 
  18 inclusive.
	
	Example input: 
    { “inList” : 
        [  
            { “x” : 2,  “y” : 1 }, 
            { “x” : 2,  “y” : 4 }, 
            { “x” : 8,  “y” : 4 }, 
            { “x” : 11, ”y” : 1 }		
        ] 
    } 

	Example output: 	
	{“count”:13} 

## proj5 Friend of a Friend:
Goal:
	Write an HTTP service which accepts a graph of people where edges represent friendship relationships.  
	Return the suggested edges which would create direct friend relationships between two people who currently 
	are at most one friend away – two people who are “friends of a friend” (FOAFs). 

Problem: 
	Receive as input an array of friend relationships.  
	Return a set of arrays each representing a new suggested friend relationship.
	
Example input: 

	{ “inList” : [ 
			{ “friends” : [ “Albert”, “Betty” ] }, 
			{ “friends” : [ “Betty”, “Cathy” ] }, 
			{ “friends” : [ “Cathy”, “Denis” ] }, 
			{ “friends” : [ “Denis”, “Albert” ] },
			{ “friends” : [ “Tony”, “Bruce” ] }
    ] } 

Example output: 	

	{ “outList” : [  [ “Albert”, “Cathy” ] , [  “Betty”, “Denis” ]  ]  } 

