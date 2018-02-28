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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import csi403.inList.command;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List; 

@SuppressWarnings("unused")
public class JsonClassDiscerner {

    public JsonClassDiscerner() {
    }

    public String discern(String jsonStr) {
    	
    	//Some error checking
    	if(jsonStr.contains("{}") || jsonStr.contains("{ }") || jsonStr.isEmpty()) {
    		return "empty JSON";
    	} else if (jsonStr.contains(".")) {
    		return "non-integer value(s)";
    	} else if(!jsonStr.contains("inList")) {
    		return "no inList object";
    	} 
    	
        ObjectMapper mapper = new ObjectMapper();
        //mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        try {
        	inList inList = mapper.readValue(jsonStr, inList.class); 
        	//JsonNode jsonNode = mapper.readTree(jsonStr);
        	
        	//Some error checking and sanitation now that we have the data
        	if(inList.getInList().length == 0) {
        		return "empty inList";
        	}
        	for (command x : inList.getInList()) {
        		if(!x.getCmd().toLowerCase().equals("enqueue") && !x.getCmd().toLowerCase().equals("dequeue")) {
        			return "bad command";
        		}
        		else if(x.getPri() < 0) {
        			return "bad priority";
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
        
        msg = "{\"inList\":["
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job6\",\"pri\":5},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job2\",\"pri\":1},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job1\",\"pri\":0},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job3\",\"pri\":2},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job5\",\"pri\":4},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job6\",\"pri\":5},"
        		+ "{\"cmd\":\"enqueue\",\"name\":\"job4\",\"pri\":3}]}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{\"inList\":[{\"cmd\":\"enqueue\",\"name\":\"job2\",\"pri\":100}]}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{\"lister\":[{\"cmd\":\"enqueue\",\"name\":\"job3\",\"pri\":1}]}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{\"inList\":[{\"cmd\":\"enqueue\",\"name\":\"job4\",\"pri\":-1}]}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{\"inList\":[{\"cmd\":\"enqueue\",\"name\":\"job5\",\"pri\":2.0}]}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{\"inList\":[{\"cmd\":\"enqueue\",\"name\":\"job6\",\"pri\":2.1}]}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{\"inList\":[{\"cmd\":\"dequeue\"}, {\"cmd\":\"dequeue\"},{   }]}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{		}";
        System.out.println(msg);
        testingFunction(discerner.discern(msg));
        
        msg = "{\"inList\":[{}]}";
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
    		System.out.println("{\"message\" : \"" + inString + "\"}");
    	}
    	System.out.println("******************"
    			+ "***************************"
    			+ "***************************");
    }

}
