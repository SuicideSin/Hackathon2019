//Author: Matthew Lee
//In collaboration with Timothy Poehlman for WWU CS Hackathon 2019
//TODO: Add Documentation

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Scanner;
import org.json.*;

//Today's High
//Today's low
//Yesterday ending
//
public class AnalyzeValues{
	
	static double estimateTomorrowClosing(JSONObject input, int time) {
		double difference = 0;
		String timePeriod;
		if(time == 0) {
			timePeriod = "Time Series (Daily)";
		} else if (time == 1) {
			timePeriod = "Weekly Time Series";
		} else {
			timePeriod = "Monthly Time Series";
		}
		JSONObject dailySeries = input.getJSONObject(timePeriod);
		JSONArray arrayVersion = dailySeries.names();
		JSONObject today = dailySeries.getJSONObject(arrayVersion.getString(0));
		double todayNum = Double.valueOf(today.getString("4. close"));
		
		for(int i = 0; i < 7; i++){
			JSONObject day = dailySeries.getJSONObject(arrayVersion.getString(i));
			JSONObject dayBefore = dailySeries.getJSONObject(arrayVersion.getString(i+1));
			double dayNum = Double.valueOf(day.getString("4. close"));
			double afterNum = Double.valueOf(dayBefore.getString("4. close"));
			difference += (dayNum - afterNum);
		}
		//returning today's closing value added to the average difference in closing over the past week
		return (todayNum + difference);
	}
	
	
	
	static String currentStats(JSONObject input) {
		//getting today's opening and closing values - same as the first part of the estimateTomorrowClosing() function
		JSONObject dailySeries = input.getJSONObject("Time Series (Daily)");
		JSONArray arrayVersion = dailySeries.names();
		JSONObject today = dailySeries.getJSONObject(arrayVersion.getString(0));
		
		String open = today.getString("1. open");
		String close = today.getString("4. close");
		String high = today.getString("2. high");
		String low = today.getString("3. low");
		
		return "Opening Price: " + open + " Closing Price: " + close + " High: " + high + " Low: " + low;
	}
	
}



