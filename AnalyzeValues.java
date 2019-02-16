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
	
	static double estimateTomorrowClosing(JSONObject input) {
		double difference = 0;
		JSONObject dailySeries = input.getJSONObject("Time Series (Daily)");
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
	
	static double estimateWeekClosing(JSONObject input) {
		double difference = 0;
		JSONObject weeklySeries = input.getJSONObject("Time Series (Weekly)");
		JSONArray arrayVersion = weeklySeries.names();
		JSONObject thisWeek = weeklySeries.getJSONObject(arrayVersion.getString(0));
		double thisWeekNum = Double.valueOf(thisWeek.getString("4. close"));
		
		for(int i = 0; i < 7; i++){
			JSONObject week = weeklySeries.getJSONObject(arrayVersion.getString(i));
			JSONObject weekBefore = weeklySeries.getJSONObject(arrayVersion.getString(i+1));
			double weekNum = Double.valueOf(week.getString("4. close"));
			double afterNum = Double.valueOf(weekBefore.getString("4. close"));
			difference += (weekNum - afterNum);
		}
		//returning thisWeek's closing value added to the average difference in closing over the past week
		return (thisWeekNum + difference);
	}
	
	
	
	
	
	
}



