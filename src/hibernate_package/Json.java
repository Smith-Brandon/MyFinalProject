package hibernate_package;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class Json {
	public static String filePath;
	public static String EntryToJSON(JsonEntry entry) throws IOException {
		FileWriter file;
        ObjectMapper mapper = new ObjectMapper(); 
        String s = "";

        try {
            s = mapper.writeValueAsString(entry);
        } catch (JsonProcessingException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
			String tempData = mapper.writeValueAsString(Json.getTemp("JSON"));

            file = new FileWriter("C:/Users/mormo/OneDrive/Desktop/savedJSON/JSON.txt");
            file.write(tempData + "\n" + s);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }
	
	public static String JSONToEntry (String s) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        String result = "";
        try {
        	JsonEntry temp = mapper.readValue(s, JsonEntry.class);
            result = temp.toString();
        } catch (JsonParseException e) {
            System.err.println(e.toString());
        } catch (JsonMappingException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println(e.toString());
        }

        return result;
    }
	
	public static String getTemp (String option) throws JsonParseException, JsonMappingException, IOException {
      //Get StringBuilder object from file
		
		if(option == "JSON") {
      		filePath = "C:/Users/mormo.000/eclipse-workspace/MyProject/src/hibernate_package/savedJSON/JSON.txt";
		}else if(option == "clean") {
			filePath = "C:/Users/mormo.000/eclipse-workspace/MyProject/src/hibernate_package/savedJSON/clean.txt";
		}
      		StringBuilder contentBuilder = new StringBuilder();
      		 
              try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8)) 
              {
                  stream.forEach(t -> contentBuilder.append(t).append("\n"));
              }
              catch (IOException e) 
              {
                  e.printStackTrace();
              }
              // Convert to string
              String content = contentBuilder.toString();
        return content;
    }
	
	
}
