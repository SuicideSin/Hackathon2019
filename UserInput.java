import java.util.*;

// Gets user input

// Is the front for the terminal, all user inputs are gathered here and then transferred to other classes


public class UserInput{

	private static String function;
	private static String symbol;
	private static String stockURL;
	private static Scanner userInput = new Scanner(System.in);
	private static Calendar c = Calendar.getInstance();


	public static void main(String arg[]){
		//ENTER GREETING HERE

		System.out.println("What Symbol would you like to look at?");

		symbol = userInput.nextLine();

		System.out.println("What would you like me to do? Options include the following: \n1)Estimate Closing \n2)Find stats \n...");

		String action = userInput.nextLine();

		findAction(action);

		//CallAPI.getJSON(stockURL);		
	}

	private static void findAction(String action){
		while(true){
			if(action.toLowerCase().equals("estimate closing") || action.equals("1")){
				estimateClosing();
				break;
			}
			else if(action.toLowerCase().equals("find stats") || action.equals("2")){
				findStats();
				break;
			}
			else if(action.toLowerCase().equals("make graph") || action.equals("3")) {
				
			}
			
			else{
				System.out.println("Please provide a valid response.");
			}
		}
	}

	private static void estimateClosing(){
		while(true){
			System.out.println("For what time period? Options include the following: \n1)Past week \n2)Past month \n3)Past year");

			String period = userInput.nextLine();
			if(period.toLowerCase().equals("past week") || period.equals("1")){
				function = "TIME_SERIES_DAILY";
				stockURL = GenerateURL.getURL(function, symbol);
				System.out.println(AnalyzeValues.estimateTomorrowClosing(CallAPI.getJSON(stockURL),0));
				break;
			}
			else if(period.toLowerCase().equals("past month") || period.equals("2")){
				function = "TIME_SERIES_WEEKLY";
				stockURL = GenerateURL.getURL(function, symbol);
				System.out.println(AnalyzeValues.estimateTomorrowClosing(CallAPI.getJSON(stockURL),1));
				break;
			}
			else if(period.toLowerCase().equals("past year") || period.equals("3")){
				function = "TIME_SERIES_MONTHLY";
				stockURL = GenerateURL.getURL(function, symbol);
				System.out.println(AnalyzeValues.estimateTomorrowClosing(CallAPI.getJSON(stockURL),2));
				break;
			}
			else{
				System.out.println("Please provide a valid response");
			}
		}
	}

	private static void findStats(){
		while(true){
			System.out.println("What stats would you like to find? Options include the following: \n1)Yesterday's stats \n2)Today's stats \n3)Current price");
			
			String chosenStat = userInput.nextLine();
			function = "TIME_SERIES_DAILY";
			stockURL = GenerateURL.getURL(function, symbol);

			if(chosenStat.toLowerCase().equals("yesterday's stats") || chosenStat.equals("1")){
				System.out.println(AnalyzeValues.lastTradingDayStats(CallAPI.getJSON(stockURL)));
				break;
			}
			else if(chosenStat.toLowerCase().equals("today's stats") || chosenStat.toLowerCase().equals("todays open") || chosenStat.equals("2")){
				if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
					System.out.println("The stock market is closed today! Here is the information for Friday.");
					
				}
				System.out.println(AnalyzeValues.lastTradingDayStats(CallAPI.getJSON(stockURL)));
				break;
			}
			else if(chosenStat.toLowerCase().equals("current price") || chosenStat.equals("3")) {
				if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
					System.out.println("The stock market is closed today! Here is the information for Friday.");
					
				}
				function = "TIME_SERIES_INTRADAY";
				stockURL = GenerateURL.getURL(function, symbol);
				System.out.println(AnalyzeValues.intradayStats(CallAPI.getJSON(stockURL)));
				break;
			}
			else{
				System.out.println("Please provide a valid response.");
			}
		}
	}


}