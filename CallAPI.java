//Author: Matthew Lee
//In collaboration with Timothy Poehlman for WWU CS Hackathon 2019
//TODO: Add Documentation

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.*;

public class CallAPI {
	
	public static JSONObject getJSON(String inputURL) {
		String data = null;
		try {
			//converting the string into something recognized as a URL
			URL url = new URL(inputURL);
			//opening a connection with the API using the URL
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			//Scanning the JSON file at the URL
			Scanner sc = new Scanner(url.openStream());
			while(sc.hasNext()) {
				//This is rather inefficient (copying the whole thing to a string), but I don't think it can be avoided.
				data += sc.nextLine();
			}
			sc.close();
		} catch (IOException E){
			throw new RuntimeException("Invalid URL Format");
		}
		
		//removing empty space at beginning of JSON that was tripping up the JSON conversion
		int i = data.indexOf("{");
		data = data.substring(i);
		//If the program runs through the try-catch block without any issues, it makes it here. If it doesn't, well that's a problem with the code above.
		//Either way I shouldn't have to worry about accidentally using null variables here; if they're null, it's because the above code didn't run, and that already would throw an exception
		//and stop during runtime.
		JSONObject convertedData = new JSONObject(data.trim());
		return convertedData;
	}
}
