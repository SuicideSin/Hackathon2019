import java.util.*;

// Gets user input

// Is the front for the terminal, all user inputs are gathered here and then transferred to other classes

//To compile in the terminal
//javac -cp /home/thegasian/Public/'Imported Libraries'/gson-2.8.5.jar *.java
//To run in the terminal
//java -cp .:/home/thegasian/Public/'Imported Libraries'/gson-2.8.5.jar UserInput.java




public class UserInput{

	private static String function;
	private static String symbol;
	private static String stockURL;
	private static Scanner userInput = new Scanner(System.in);
	private static Calendar c = Calendar.getInstance();
	private static String currencyFrom;
	private static String currencyTo;


	public static void main(String arg[]){
		//ENTER GREETING HERE

		

		

		findAction();

		//CallAPI.getJSON(stockURL);		
	}

	private static void findAction(){
		while(true){
			System.out.println("What would you like me to do? Options include the following: \n1)Estimate Closing \n2)Find stats \n3)Exchange rates");

			String action = userInput.nextLine();
			
			if(action.toLowerCase().equals("estimate closing") || action.equals("1")){
				System.out.println("What Symbol would you like to look at?");

				symbol = userInput.nextLine();
				
				estimateClosing();
				break;
			}
			else if(action.toLowerCase().equals("find stats") || action.equals("2")){
				System.out.println("What Symbol would you like to look at?");

				symbol = userInput.nextLine();
				
				findStats();
				break;
			}
			else if(action.toLowerCase().equals("exchange rates") || action.equals("3")) {
				function = "CURRENCY_EXCHANGE_RATE";
				exchangeRates();
				break;
			}
			/*else if(action.toLowerCase().equals("create graph") || action.equals("4")){
				activateGraph();
			}*/
			else{
				System.out.println("Please provide a valid response. \n");
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
			System.out.println("What stats would you like to find? Options include the following: \n1)Last Trading Day Stats \n2)Current price");
			String chosenStat = userInput.nextLine();
			function = "TIME_SERIES_DAILY";
			stockURL = GenerateURL.getURL(function, symbol);

			if(chosenStat.toLowerCase().equals("last trading day stats") || chosenStat.equals("1")){
				if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
					System.out.println("The stock market is closed today! Here is the information for Friday.");
					
				}
				System.out.println(AnalyzeValues.lastTradingDayStats(CallAPI.getJSON(stockURL)));
				break;
			}
			else if(chosenStat.toLowerCase().equals("current price") || chosenStat.equals("2")) {
				if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
					System.out.println("The stock market is closed today! Here is the summary of prices on Friday.");

					System.out.println(AnalyzeValues.lastTradingDayStats(CallAPI.getJSON(stockURL)));
					break;
				}
				else
				{
					function = "TIME_SERIES_INTRADAY";
					stockURL = GenerateURL.getURL(function, symbol);
					System.out.println(AnalyzeValues.intradayStats(CallAPI.getJSON(stockURL)));
					break;
				}
			}
			else{
				System.out.println("Please provide a valid response.");
			}
		}
	}

	
	// Requests input for the two currencies to find the exchange rate to
	private static void exchangeRates() 
	{
		while(true) 
		{
			System.out.println("Please provide the currency you wish to exchange from");
			currencyFrom = userInput.nextLine();
			
			System.out.println("Please provide the currency you wish to exchange to");
			currencyTo = userInput.nextLine();
			
			stockURL = GenerateURL.getURLExchange(function, currencyFrom, currencyTo);
			
			// prints the information returned by AnalyzeValues.foreignExchange, with the information gathered from stockURL
			System.out.println(AnalyzeValues.foreignExchange(CallAPI.getJSON(stockURL)));
			break;
		
		}
	}

	// activates the graph activator with the information from stockURL
	private static void activateGraph() 
	{
		function = "TIME_SERIES_DAILY";
		stockURL = GenerateURL.getURL(function, symbol);
		
		//AnalyzeValues.generateGraph(stockURL);
	}
}