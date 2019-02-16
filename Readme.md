/**
 * 
 */
/**
 * @author Matthew Lee
 *
 */
//Might end up writing this in Ruby


//TODO: Add JavaDocs documentation to written functions, on top of working on the functions themselves

/*
 * UX
 * Ideally, there will be a radio button for the time series
 * (or whether a time series or something else is being requested) and if, for example, a time series is selected, the UI will show a radio button menu that shows the possible
 * time series, then upon selecting one of those, the function variable = that time series, and another menu is revealed that shows the various options (text box for stock symbol,
 * radio buttons for interval, outputsize and datatype where applicable).
 * 
 * Generating the API Call
 * Upon hitting enter, GenerateAPICall.generateCall() is called with the variables as arguments. There will be null variables, which is fine.
 * 
 * Retrieving the JSON data
 * The call will then be generated and passed to WebCaller.getJSON(), which will attempt to access the Alpha Vantage API and retrieve the JSON
 * (or the .csv, and the function will be renamed, if this can be done more simply or efficiently with .csv files) file/
 * 
 * Using the Data
 * Then, an as-yet-unnamed function will take that data and either display it in the UI for the user or manipulate it/pass it to a neural network/data analysis function
 * /graphical representation function/etc. 
 * 
 * Potentially, there will be some sort of output option as well, like exporting a chart as a .jpg or .pdf, or creating a text file with stock estimates or something like that.
 * 
 * Outside Resources:
 * The Alpha Vantage API (obviously)
 * Probably an open-source JSON reader. I might write one myself, though; a simple non-efficient one doesn't seem that hard to write.
 * Graph/Chart Library. Used for data representation, obviously.
 * Scenebuilder, probably. I'm not going to create some ugly-ass UI myself; I want this to be worthy of the immortal words of Angry Joe: "THAT LOOK GOOOOOOOD!"
 */



//Currently writing WebCaller, a class that retrieves JSON data from the Alpha Vantage API.

//Need to write some sort of generator (probably with some sort of UI in the future) that
//then takes the data request, makes an appropriate URL, and then passes that URL to WebCaller.
