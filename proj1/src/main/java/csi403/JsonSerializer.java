//*******************************************************************
//  The supplied serializer class, to convert an object into JSON format.
// 
//  Edits have only been made to the main method for testing purposes.
//*******************************************************************

package csi403;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Vector;
import java.util.List; 

public class JsonSerializer {

    public JsonSerializer() {
    }

    public String serialize(Object obj) {
        String str = null; 
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            str = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            str = "<error>"; 
        }
        return str;
    }


    // test app 
    public static void main(String[] args) {
        JsonSerializer serializer = new JsonSerializer();

        System.out.println("************************************");
        
        outList testList = new outList();
        testList.setAlgorithm("quicksort");
        testList.setTimeMS(52);
        int testArray[] = { 1,2,3,4,5 }; 
        testList.setOutList(testArray);
        System.out.println(serializer.serialize(testList));
        
        System.out.println("************************************");

    }
    
}

