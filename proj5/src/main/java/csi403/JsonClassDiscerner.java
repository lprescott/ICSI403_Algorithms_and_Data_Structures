//*******************************************************************
//  The supplied discerner class, to detect the object type of received JSON.
// 
//  Edits have been made to the main method for testing purposes.
//  Edits have also been in the discern function, implementing inList and
//		added error checking before and after data was object mapped.
//
//	testingFunction(String inString) - aids in quick testing of error handling
//		by accepting a string which contains the response for the discern func.
//
//*******************************************************************

package csi403; 

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.List; 

@SuppressWarnings("unused")
public class JsonClassDiscerner {

    public JsonClassDiscerner() {
    }

    public String discern(String jsonStr) {

    	//Some error checking
    	if(jsonStr.contains("{}") || jsonStr.isEmpty()) {
    		return "Malformed JSON";
        }
        
        ObjectMapper mapper = new ObjectMapper();
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
        	inList inList = mapper.readValue(jsonStr, inList.class); 
        	        	
        	//JsonNode jsonNode = mapper.readTree(jsonStr);
        	
        	//Some error checking and sanitation now that we have the data
        	if (inList.getInList().length == 0) {
        		return "Empty inList";
        	}
        	        	
        	for(int x = 0; x < inList.getInList().length; x ++) {
        		if(inList.getInList()[x].getFriends().length != 2) {
                	return "Invalid # of friends";
        		}
        	}
        	
        	return "inList";
        	
        } catch(Exception e) {
            //e.printStackTrace();
        	return "Malformed JSON";
        }
    }


    // test app 
    public static void main(String[] args) {
        String msg;
        JsonClassDiscerner discerner = new JsonClassDiscerner();
        
        msg = "{\"inList\":[]}";  
        System.out.println(msg);
        testingFunction(discerner.discern(msg));  
        
        msg = "{\"inList\":}";  
        System.out.println(msg);
        testingFunction(discerner.discern(msg)); 
        
        msg = "{\"inList\":[{\"friends\":[\"Albert\", \"Luke\", \"Betty\"]},{\"friends\":[\"Betty\", \"Cathy\"]},{\"friends\":[\"Cathy\", \"Denis\"]},{\"friends\":[\"Denis\", \"Albert\"]},{\"friends\":[\"Tony\", \"Bruce\"]}]}";  
        System.out.println(msg);
        testingFunction(discerner.discern(msg)); 
        
        msg = "{\"inList\":[{ },{\"friends\":[\"Betty\", \"Cathy\"]},{\"friends\":[\"Cathy\", \"Denis\"]},{\"friends\":[\"Denis\", \"Albert\"]},{\"friends\":[\"Tony\", \"Bruce\"]}]}";  
        System.out.println(msg);
        testingFunction(discerner.discern(msg)); 
        
        msg = "{\"inList\":[{\"friends\":[\"Albert\", \"Betty\"]},{\"friends\":[\"Betty\", \"Cathy\"]},{\"friends\":[\"Cathy\", \"Denis\"]},{\"friends\":[\"Denis\", \"Albert\"]},{\"friends\":[\"Tony\", \"Bruce\"]}]}";  
        System.out.println(msg);
        testingFunction(discerner.discern(msg));  
    }

    /*
    * This function is meant to enable easier testing of JSON discerning.
    */
    public static void testingFunction(String inString) {
    	if(inString.equals("inList")) {
    		System.out.println("inList");
    	} else {
    		System.out.println("{\"message\":\"" + inString + "\"}");
    	}
    	System.out.println("******************"
    			+ "***************************"
    			+ "***************************");
    }
}