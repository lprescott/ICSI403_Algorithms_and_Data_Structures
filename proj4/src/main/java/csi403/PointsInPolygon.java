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
public class PointsInPolygon extends HttpServlet {

  // Standard servlet method 
    public void init() throws ServletException { 
        // Do any required initialization here - likely none
    }
    
    // Standard servlet method - we will handle a POST operation
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        doService(request, response); 
    }

    // Standard Servlet method
    public void destroy() { 
        // Do any required tear-down here, likely nothing.
    }

    // Standard servlet method - we will not respond to GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException { 
        // Set response content type and return an error message
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        // We can always create JSON by hand just concating a string.  
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
        
        //Store discerned response from inputted JSON
        String discerned = new JsonClassDiscerner().discern(jsonStr); 
        
        // Set response content type to be JSON
        response.setContentType("application/json");
        // Send back the name of the class as a JSON message
        PrintWriter out = response.getWriter();
        
        if (discerned.equals("inList")) {
        	//Create and objectmapper and serializer needed for inputting inList, and outputting outList
            ObjectMapper mapper = new ObjectMapper();
            JsonSerializer serializer = new JsonSerializer();

            //Read in the inList and then construct the outList
            inList inList = mapper.readValue(jsonStr, inList.class);

            //Construct
            outList outList = new outList(inList);
            
            if (outList.getClass() == null) {
            	 out.println("{\"message\" : \"unknown error\"}");
            	 return;
            } else {
                //Serialize the outList and output
                out.println(serializer.serialize(outList));
            }

        } else {
            // e.printStackTrace(); 
            out.println("{\"message\" : \"" + discerned + "\"}");
        }
    }
}

