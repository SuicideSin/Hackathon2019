//Author: Matthew Lee
//In collaboration with Timothy Poehlman and Griffin Nicolino for WWU CS Hackathon 2019
//TODO: Add Documentation

import com.google.gson.*;

//Today's High
//Today's low
//Yesterday ending
//
public class AnalyzeValues{
	
	static double estimateTomorrowClosing(JsonObject input, int time) {
		double difference = 0;
		String timePeriod;
		if(time == 0) {
			timePeriod = "Time Series (Daily)";
		} else if (time == 1) {
			timePeriod = "Weekly Time Series";
		} else {
			timePeriod = "Monthly Time Series";
		}
		JsonObject dailySeries = input.getAsJsonObject(timePeriod);
		JsonArray arrayVersion = dailySeries.getAsJsonArray();
		JsonObject today = arrayVersion.get(0).getAsJsonObject();
		//alternatively, simply retrieve this as a double. today.get("4. close").getAsDouble();
		//I'm not sure if this .get(int).toString() business works, but I think it should, as Eclipse tends to warn me if something is out of place.
		double todayNum = Double.valueOf(today.get("4. close").toString());
		
		
		if(time == 0) {
			for(int i = 0; i < 7; i++){
				JsonObject day = dailySeries.getAsJsonObject(arrayVersion.get(i).toString());
				JsonObject dayBefore = dailySeries.getAsJsonObject(arrayVersion.get(i+1).toString());
				double dayNum = Double.valueOf(day.get("4. close").toString());
				double afterNum = Double.valueOf(dayBefore.get("4. close").toString());
				difference += (dayNum - afterNum);
			}
			difference /=7;
		} else if(time == 2){
			for(int i = 0; i < 12; i++){
				JsonObject day = dailySeries.getAsJsonObject(arrayVersion.get(i).toString());
				JsonObject dayBefore = dailySeries.getAsJsonObject(arrayVersion.get(i+1).toString());
				double dayNum = Double.valueOf(day.get("4. close").toString());
				double afterNum = Double.valueOf(dayBefore.get("4. close").toString());
				difference += (dayNum - afterNum);
			}
			difference /=12;
		} else {
			for(int i = 0; i < 4; i++){
				JsonObject day = dailySeries.getAsJsonObject(arrayVersion.get(i).toString());
				JsonObject dayBefore = dailySeries.getAsJsonObject(arrayVersion.get(i+1).toString());
				double dayNum = Double.valueOf(day.get("4. close").toString());
				double afterNum = Double.valueOf(dayBefore.get("4. close").toString());
				difference += (dayNum - afterNum);
			}
			difference /= 4;
		}
		
		
		
		//returning today's closing value added to the average difference in closing over the past week
		return (todayNum + difference);
	}
	
	static String lastTradingDayStats(JsonObject input) {
		//getting today's opening and closing values - same as the first part of the estimateTomorrowClosing() function
		JsonObject dailySeries = input.getAsJsonObject("Time Series (Daily)");
		JsonArray arrayVersion = dailySeries.getAsJsonArray();
		JsonObject today = dailySeries.getAsJsonObject(arrayVersion.get(0).toString());
		
		String open = today.get("1. open").toString();
		String close = today.get("4. close").toString();
		String high = today.get("2. high").toString();
		String low = today.get("3. low").toString();
		
		return "Opening Price: " + open + " Closing Price: " + close + " High: " + high + " Low: " + low;
	}
	
	static String intradayStats(JsonObject input) {
		JsonObject currentSeries = input.getAsJsonObject("Time Series (1min)");
		JsonArray arrayVersion = currentSeries.getAsJsonArray();
		JsonObject currenttime = currentSeries.getAsJsonObject(arrayVersion.get(0).toString());
		
		String open = currenttime.get("1. open").toString();
		String close = currenttime.get("4. close").toString();
		String high = currenttime.get("2. high").toString();
		String low = currenttime.get("3. low").toString();
		
		return "Opening Price: " + open + " Closing Price: " + close + " High: " + high + " Low: " + low;
	}
	
	
	static String foreignExchange(JsonObject input) {
		JsonObject currentExchange = input.getAsJsonObject("Realtime Currency Exchange Rate");
		String from = currentExchange.get("2. From_Currency Name").toString();
		String to = currentExchange.get("4. To_Currency Name").toString();
		String value = currentExchange.get("5. Exchange Rate").toString();
		
		return "A single " + from + " is equal to " + value + " " + to;
	}
	
	
	static void generateGraph(JsonObject input) {
		int[] values = new int[365];
		int[] indices = new int[365];
		JsonObject dailySeries = input.getAsJsonObject("Time Series (Daily)");
		JsonArray arrayVersion = dailySeries.getAsJsonArray();
		for(int i = 0; i < 365; i++){
			JsonObject day = dailySeries.getAsJsonObject(arrayVersion.get(i).toString());
			double dayNum = Double.valueOf(day.get("4. close").toString());
			//the graph library being used can't handle 
			dayNum*=100;
			values[i] = (int) dayNum;
		}
		for(int i = 0; i<365; i++) {
			indices[i] = i;
		}
		
		
		
	}
}



