package csi403; 

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.List; 

public class JsonClassDiscerner {

    public JsonClassDiscerner() {
    }

    public String discern(String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try { 
            Person person = mapper.readValue(jsonStr, Person.class);
            return "person"; 
        }
        catch (Exception e) {
            // e.printStackTrace(); 
        }
        try {
            Pet pet = mapper.readValue(jsonStr, Pet.class);
            return "pet"; 
        }
        catch (Exception e) {
            // e.printStackTrace(); 
        }
        try {
            inList list = mapper.readValue(jsonStr, inList.class);
            return "inList"; 
        }
        catch (Exception e) {
            // e.printStackTrace(); 
        }
        return "<unknown>"; 
    }


    // test app 
    public static void main(String[] args) {
        String msg;
        JsonClassDiscerner discerner = new JsonClassDiscerner();
        System.out.println("************************************"); 
        
        msg = "{ \"name\" : \"Fido\", \"species\" : \"Dog\" }";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
        
        msg = "{ \"name\" : \"Fido\", \"lastName\" : \"Dog\" }";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
                
        msg = "{ \"lastName\" : \"Smith\", \"firstName\" : \"Mary\" }";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
                
        msg = "{ \"name\" : \"Fido\", \"species\" : \"Dog\", " +
            "\"owner\" : " + "{ \"lastName\" : \"Smith\", \"firstName\" : \"Mary\" }" + " }";
        System.out.println(msg); 
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");

        msg = "{ \"name\" : \"Fido\", \"species\" : \"Dog\", " +
            "\"person\" : " + "{ \"lastName\" : \"Smith\", \"firstName\" : \"Mary\" }" + " }";
        System.out.println(msg); 
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
        
        msg = "{ \"inList\" : [1,2,3,4,5]}";
        System.out.println(msg);
        System.out.println(discerner.discern(msg));

        System.out.println("************************************");
    }
}
