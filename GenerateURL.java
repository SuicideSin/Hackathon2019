import java.util.*;

public class GenerateURL{

	private static String function;
	private static String symbol;
	private static String otherInputs = "";
	private static String optionalInputs = "";
	private static String apikey = "QTPNO32HQGPBBKJC";
	private static String currencyFrom;
	private static String currencyTo;


	//if revieving outside input replace main(String args[]) with 'getURL(function,symbol,otherInputs,optionalInputs)'
	public static String getURL(String chosenAction, String chosenSymbol){
		function = chosenAction;
		symbol = chosenSymbol;
		
		if(chosenAction.equals("TIME_SERIES_INTRADAY")) {
			otherInputs = "1min";
		}
		//commented out due to unnecessary information, if using intraday, daily, or daily adjusted uncomment
		//determineOtherVariables();

		// return constructURL();  // ONLY HAVE THIS IF NO LONGER USING MAIN TO ACTIVATE
		return constructURL();
	}
	
	public static String getURLExchange(String chosenAction, String chosenFromCurr, String chosenToCurr) {
		function = chosenAction;
		currencyFrom = chosenFromCurr;
		currencyTo = chosenToCurr;
		
		return constructURL();
	}

	// determines if user wishes to use the optional inputs
	//private static Boolean requestOptionals(){
	//	System.out.println("Would you like to use the optional modifiers? (Y/N)");
	//	if(sc.nextLine().toLowerCase().equals("y"))
	//		return true;
	//	
	//	else
	//		return false;
	//}

	// determines interval for TIME_SERIES_DAILY
	//private static String intervalInput(){
	//	System.out.println("Please input the Time Interval, the following values are supported: 1min, 5min, 15min, 30min, 60min");
	//	return sc.nextLine();
	//}


	// determines outputsize
	//private static String optionalUserInput(){
	//	System.out.println("Please provide the wanted output size, the options are: compact or full");
	//	return sc.nextLine();
	//}

	// finds other variables for URL
	//private static void determineOtherVariables(){

	//	if(function.equals("TIME_SERIES_INTRADAY") || function.equals("TIME_SERIES_DAILY") || function.equals("TIME_SERIES_DAILY_ADJUSTED")){
	//		otherInputs = "&interval=" + intervalInput();
	//		if(requestOptionals()){
	//			optionalInputs = "&outputsize=" + optionalUserInput();
	//		}
	//	}
		

	//}

	private static String constructURL(){
		if(function.equals("CURRENCY_EXCHANGE_RATE")){
			String finalURL = "https://www.alphavantage.co/query?function=" + function + "&from_currency=" + currencyFrom + "&to_currency=" + currencyTo + "&apikey=" + apikey);
		}
		else {
			String finalURL = "https://www.alphavantage.co/query?function=" + function + "&symbol=" + symbol + otherInputs + optionalInputs + "&apikey=" + apikey + "&datatype=json";
		}
		return finalURL;
	}
}