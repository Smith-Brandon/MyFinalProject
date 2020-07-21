package hibernate_package;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public class ViewAll implements Runnable {

	public ViewAll() throws JsonParseException, JsonMappingException, IOException {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					"C:/Users/mormo.000/eclipse-workspace/MyProject/src/hibernate_package/savedJSON/clean.txt")); 
			String line = reader.readLine();
			while (line != null) {
				line = reader.readLine();
				System.out.println("Name: " + line);
				// read next line
				line = reader.readLine();
				System.out.println("Symbol: " + line);
				line = reader.readLine();
				System.out.println("Price: " + line);
				line = reader.readLine();
				System.out.println("Average Yield: " + line);
				line = reader.readLine();
				System.out.println("Dividend Yield: " + line);
				line = reader.readLine();
				System.out.println("Shares Purchased: " + line);
				line = reader.readLine();
				System.out.println("Approximate 12M Dividend: " + line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}