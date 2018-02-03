//*******************************************************************
//  The supplied httpservlet class, to enact algorithms on supplied POST requests.
// 
//  Edits have only been made to the "main worker method" doService, which is called in doPost.
//*******************************************************************

package csi403;

// Import required java libraries
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.fasterxml.jackson.databind.ObjectMapper;


// Extend HttpServlet class
public class DiscernJsonService extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Standard servlet method 
    public void init() throws ServletException { 
        // Do any required initialization here - likely none
    }
    
    // Standard servlet method - we will handle a POST operation
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        doService(request, response); 
    }

    // Standard servlet method
    public void destroy() { 
        // Do any required tear-down here, likely nothing.
    }

    // Standard servlet method - we will not respond to GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        // Set response content type and return an error message
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        // We can always create JSON by hand just concatenating a string.  
        out.println("{ 'message' : 'Use POST!'}");
    }
    
    // Our main worker method
    private void doService(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        // Get received JSON data from HTTP request
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String jsonStr = "";
        if(br != null){
            jsonStr = br.readLine();
        }
        
        // Create Json reader object and discern the class from the JSON message 
        String className = new JsonClassDiscerner().discern(jsonStr); 
        
        // Set response content type to be JSON
        response.setContentType("application/json");
        
        // Send back the name of the class as a JSON message
        PrintWriter out = response.getWriter();
        
        //If the inputed JSON creates a valid inList.
        if(className.equals("inList")) {
        	//Create and objectmapper and serializer needed for inputting inList, and outputting outList
            ObjectMapper mapper = new ObjectMapper();
            JsonSerializer serializer = new JsonSerializer();

            //Read in the inList and then construct the outList
            inList inList = mapper.readValue(jsonStr, inList.class);
            
            //Error checking (bad input)
            if (inList.getInList().length == 0) {
                // e.printStackTrace(); 
                out.println("{\"message\" : \"Empty inList\"}");
                return;
            }
            
            int x; //Check for non-integers
            for (x=0; x<inList.getInList().length; x++) {
            	if((double) inList.getInList()[x] % 1.0 != 0) {
                    // e.printStackTrace(); 
                    out.println("{\"message\" : \"non-integer inList\"}");
                    return;
            	}
            } //End error checking

            //Construct
            outList outList = new outList(inList);

            //Serialize the outList and output
            out.println(serializer.serialize(outList));
        } 
        //Else return an error
        else {
            // e.printStackTrace(); 
            out.println("{\"message\" : \"Malformed JSON\"}"); 
        }
    }
}
