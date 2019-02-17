//Author: Matthew Lee
//In collaboration with Timothy Poehlman and Griffin Nicolino for WWU CS Hackathon 2019
//TODO: Add Documentation

import java.util.Set;

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
		//from what I understand, due to the way the keySet() function is constructed, using the map interface, the resulting set preserves the order of entry, and it preserves it when
		//converting to another ordered data structure as well.
		Set<String> keys =  dailySeries.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);
		JsonObject today = dailySeries.getAsJsonObject(keysArray[0]);
		//alternatively, simply retrieve this as a double. today.get("4. close").getAsDouble();
		//I'm not sure if this .get(int).toString() business works, but I think it should, as Eclipse tends to warn me if something is out of place.
		double todayNum = Double.valueOf(today.get("4. close").toString().replaceAll("^\"|\"$", ""));
		
		
		if(time == 0) {
			for(int i = 0; i < 7; i++){
				JsonObject day = dailySeries.getAsJsonObject(keysArray[i]);
				JsonObject dayBefore = dailySeries.getAsJsonObject(keysArray[i+1]);
				double dayNum = Double.valueOf(day.get("4. close").toString().replaceAll("^\"|\"$", ""));
				double afterNum = Double.valueOf(dayBefore.get("4. close").toString().replaceAll("^\"|\"$", ""));
				difference += (dayNum - afterNum);
			}
			difference /=7;
		} else if(time == 2){
			for(int i = 0; i < 12; i++){
				JsonObject day = dailySeries.getAsJsonObject(keysArray[i]);
				JsonObject dayBefore = dailySeries.getAsJsonObject(keysArray[i+1]);
				double dayNum = Double.valueOf(day.get("4. close").toString().replaceAll("^\"|\"$", ""));
				double afterNum = Double.valueOf(dayBefore.get("4. close").toString().replaceAll("^\"|\"$", ""));
				difference += (dayNum - afterNum);
			}
			difference /=12;
		} else {
			for(int i = 0; i < 4; i++){
				JsonObject day = dailySeries.getAsJsonObject(keysArray[i]);
				JsonObject dayBefore = dailySeries.getAsJsonObject(keysArray[i+1]);
				double dayNum = Double.valueOf(day.get("4. close").toString().replaceAll("^\"|\"$", ""));
				double afterNum = Double.valueOf(dayBefore.get("4. close").toString().replaceAll("^\"|\"$", ""));
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
		Set<String> keys =  dailySeries.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);
		JsonObject today = dailySeries.getAsJsonObject(keysArray[0]);
		
		String open = today.get("1. open").toString().replaceAll("^\"|\"$", "");
		String close = today.get("4. close").toString().replaceAll("^\"|\"$", "");
		String high = today.get("2. high").toString().replaceAll("^\"|\"$", "");
		String low = today.get("3. low").toString().replaceAll("^\"|\"$", "");
		
		return "Opening Price: " + open + " Closing Price: " + close + " High: " + high + " Low: " + low;
	}
	
	static String intradayStats(JsonObject input) {
		JsonObject currentSeries = input.getAsJsonObject("Time Series (1min)");
		Set<String> keys =  currentSeries.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);
		JsonObject currenttime = currentSeries.getAsJsonObject(keysArray[0]);
		
		String open = currenttime.get("1. open").toString().replaceAll("^\"|\"$", "");
		String close = currenttime.get("4. close").toString().replaceAll("^\"|\"$", "");
		String high = currenttime.get("2. high").toString().replaceAll("^\"|\"$", "");
		String low = currenttime.get("3. low").toString().replaceAll("^\"|\"$", "");
		
		return "Opening Price: " + open + " Closing Price: " + close + " High: " + high + " Low: " + low;
	}
	
	
	static String foreignExchange(JsonObject input) {
		JsonObject currentExchange = input.getAsJsonObject("Realtime Currency Exchange Rate");
		String from = currentExchange.get("2. From_Currency Name").toString().replaceAll("^\"|\"$", "");
		String to = currentExchange.get("4. To_Currency Name").toString().replaceAll("^\"|\"$", "");
		String value = currentExchange.get("5. Exchange Rate").toString().replaceAll("^\"|\"$", "");
		
		return "A single " + from + " is equal to " + value + " " + to;
	}
	
	/*
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
	}*/
}



