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

		//gets the URL for all information
		stockURL = GenerateURL.getURL(function, symbol);

		System.out.println(stockURL);

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
				break;
			}
			else if(period.toLowerCase().equals("past month") || period.equals("2")){
				function = "TIME_SERIES_WEEKLY";
				break;
			}
			else if(period.toLowerCase().equals("past year") || period.equals("3")){
				function = "TIME_SERIES_MONTHLY";
				break;
			}
			else{
				System.out.println("Please provide a valid response");
			}
		}
	}

	private static void findStats(){
		while(true){
			System.out.println("What stats would you like to find? Options include the following: \n1)Last open \n2)Last close \n3)Today's open \n4)Today's close");
			
			String chosenStat = userInput.nextLine();

			if(chosenStat.toLowerCase().equals("last open") || chosenStat.equals("1")){
				function = "TIME_SERIES_DAILY";

				break;
			}
			else if(chosenStat.toLowerCase().equals("last close") || chosenStat.equals("2")){
				function = "TIME_SERIES_DAILY";

				break;
			}
			else if(chosenStat.toLowerCase().equals("today's open") || chosenStat.toLowerCase().equals("todays open") || chosenStat.equals("3")){
				function = "TIME_SERIES_DAILY";
				if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
					System.out.println("The stock market is closed today! Here is the information for Friday's open.");
					break;
				}
				else{
					break;
				}
			}
			else if(chosenStat.toLowerCase().equals("today's close") || chosenStat.toLowerCase().equals("todays close") || chosenStat.equals("4")){
				function = "TIME_SERIES_DAILY";
				if(c.get(Calendar.DAY_OF_WEEK) == 1 || c.get(Calendar.DAY_OF_WEEK) == 7){
					System.out.println("The stock market is closed today! Here is the information for Friday's close.");
					break;
				}
				else{
					break;
				}
			}
			else{
				System.out.println("Please provide a valid response.");
			}
		}
	}


}