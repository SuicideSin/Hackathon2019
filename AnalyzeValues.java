//Author: Matthew Lee
//In collaboration with Timothy Poehlman and Griffin Nicolino for WWU CS Hackathon 2019
//TODO: Add Documentation

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
		
		
		if(time == 0) {
			for(int i = 0; i < 7; i++){
				JSONObject day = dailySeries.getJSONObject(arrayVersion.getString(i));
				JSONObject dayBefore = dailySeries.getJSONObject(arrayVersion.getString(i+1));
				double dayNum = Double.valueOf(day.getString("4. close"));
				double afterNum = Double.valueOf(dayBefore.getString("4. close"));
				difference += Math.abs(dayNum - afterNum);
			}
			difference /=7;
		} else if(time == 2){
			for(int i = 0; i < 12; i++){
				JSONObject day = dailySeries.getJSONObject(arrayVersion.getString(i));
				JSONObject dayBefore = dailySeries.getJSONObject(arrayVersion.getString(i+1));
				double dayNum = Double.valueOf(day.getString("4. close"));
				double afterNum = Double.valueOf(dayBefore.getString("4. close"));
				difference += Math.abs(dayNum - afterNum);
			}
			difference /=12;
		} else {
			for(int i = 0; i < 4; i++){
				JSONObject day = dailySeries.getJSONObject(arrayVersion.getString(i));
				JSONObject dayBefore = dailySeries.getJSONObject(arrayVersion.getString(i+1));
				double dayNum = Double.valueOf(day.getString("4. close"));
				double afterNum = Double.valueOf(dayBefore.getString("4. close"));
				difference += Math.abs(dayNum - afterNum);
			}
			difference /= 4;
		}
		
		
		
		//returning today's closing value added to the average difference in closing over the past week
		return (todayNum + difference);
	}
	
	static String lastTradingDayStats(JSONObject input) {
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
	
	static String intradayStats(JSONObject input) {
		JSONObject currentSeries = input.getJSONObject("Time Series (1min)");
		JSONArray arrayVersion = currentSeries.names();
		JSONObject currenttime = currentSeries.getJSONObject(arrayVersion.getString(0));
		
		String open = currenttime.getString("1. open");
		String close = currenttime.getString("4. close");
		String high = currenttime.getString("2. high");
		String low = currenttime.getString("3. low");
		
		return "Opening Price: " + open + " Closing Price: " + close + " High: " + high + " Low: " + low;
	}
	
	
	static String foreignExchange(JSONObject input) {
		JSONObject currentExchange = input.getJSONObject("Realtime Currency Exchange Rate");
		String from = currentExchange.getString("2. From_Currency Name");
		String to = currentExchange.getString("4. To_Currency Name");
		String value = currentExchange.getString("5. Exchange Rate");
		
		return "A single " + from + " is equal to " + value + " " + to;
	}
	
	
	static void generateGraph(JSONObject input) {
		int[] values = new int[365];
		int[] indices = new int[365];
		JSONObject dailySeries = input.getJSONObject("Time Series (Daily)");
		JSONArray arrayVersion = dailySeries.names();
		for(int i = 0; i < 365; i++){
			JSONObject day = dailySeries.getJSONObject(arrayVersion.getString(i));
			double dayNum = Double.valueOf(day.getString("4. close"));
			//the graph library being used can't handle 
			dayNum*=100;
			values[i] = (int) dayNum;
		}
		for(int i = 0; i<365; i++) {
			indices[i] = i;
		}
		
		
		
	}
}



