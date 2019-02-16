import java.util.*;

public class GenerateURL{

	private static String function;
	private static String symbol;
	private static String otherInputs = "";
	private static String optionalInputs = "";
	private static String apikey = "QTPNO32HQGPBBKJC";


	private static Scanner sc = new Scanner(System.in);


	//if revieving outside input replace main(String args[]) with 'getURL(function,symbol,otherInputs,optionalInputs)'
	public static void main(String args[]){
		//if recieving outside input remove printFunctionList();
		printFunctionList();


		functionInput();
		symbolInput();
		determineOtherVariables();

		// return constructURL();  // ONLY HAVE THIS IF NO LONGER USING MAIN TO ACTIVATE
		constructURL();
	}

	// prints to connsole the list of functions available
	private static void printFunctionList(){
		System.out.println("The following is a list of functions available for the Stock Time Series category:");
		System.out.println("TIME_SERIES_INTRADAY \nTIME_SERIES_DAILY \nTIME_SERIES_DAILY_ADJUSTED \nTIME_SERIES_WEEKLY \nTIME_SERIES_MONTHLY \nTIME_SERIES_MONTHYL_ADJUSTED \nGLOBAL_QUOTE \nSYMBOL_SEARCH");
		System.out.println("--------------");
	}

	// requests input for function
	private static void functionInput(){
		System.out.println("Please provide which function you wish to use");
		
		function = sc.nextLine();
	}

	// requests input for symbol()
	private static void symbolInput(){
		System.out.println("Please provide the symbol");
		symbol = sc.nextLine();
	}

	// determines if user wishes to use the optional inputs
	private static Boolean requestOptionals(){
		System.out.println("Would you like to use the optional modifiers? (Y/N)");
		if(sc.nextLine().toLowerCase().equals("y"))
			return true;
		
		else
			return false;
	}

	// determines interval for TIME_SERIES_DAILY
	private static String intervalInput(){
		System.out.println("Please innput the Time Interval, the following values are supported: 1min, 5min, 15min, 30min, 60min");
		return sc.nextLine();
	}


	// determines outputsize
	private static String optionalUserInput(){
		System.out.println("Please provide the wanted output size, the options are: compact or full");
		return sc.nextLine();
	}

	// finds other variables for URL
	private static void determineOtherVariables(){

		if(function.equals("TIME_SERIES_INTRADAY") || function.equals("TIME_SERIES_DAILY") || function.equals("TIME_SERIES_DAILY_ADJUSTED")){
			otherInputs = "&interval=" + intervalInput();
			if(requestOptionals()){
				optionalInputs = "&outputsize=" + optionalUserInput();
			}
		}
		

	}

	private static String constructURL(){
		String finalURL = "https://www.alphavantage.co/query?function=" + function + "&symbol=" + symbol + otherInputs + optionalInputs + "&apikey=" + apikey + "&datatype=json";
		System.out.println(finalURL);
		return finalURL;
	}
}