package Hackathon_Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BatteryParsing {
 public static void main(String[] args) throws Exception {
  
  // string variables to be compared for extracting values
  String compare1 = "Uid u0a202";
  String compare2 = "Foreground activities";

  // float variables used to store battery percentage and drain values
  float Battery_percentage = 0, Battery_drain = 0;

  //Opening and reading a text file
  File file = new File("C:\\Users\\USER\\eclipse-workspce\\arun\\BatteryParsing\\src\\Hackathon_Java\\input.txt");
  BufferedReader br = new BufferedReader(new FileReader(file));

  // string variable used to store foreground string
  String Foreground = "";

  String st1, st2;

  // loop runs until it reaches the end of the file
  while ((st1 = br.readLine()) != null) {
   
   // splitting the line into two half as before the colon in array[0] and after the colon in array [1] 
   String[] array = st1.split(":");

   // if array has any data
   if (array.length > 1) {
    
    // excluding the spaces
    array[0] = array[0].trim();

    // if it equals Uid u0a202 
    if (array[0].equals(compare1)) {
   
     // to extract the data we are splitting as before the bracket in array1[0] and after the bracket in array[1]
     String[] array1 = array[1].split("\\(");

     // then converting array1[0] into float which is the required battery drain
     Battery_drain = Float.parseFloat(array1[0]);

    }

    // if it equals Foreground activities 
    if (array[0].equals(compare2)) {

     // to extract the data we are splitting as before the bracket in array1[0] and after the bracket in array[1]
     String[] array1 = array[1].split("\\(r");

     // then storing array1[0] into string variable which is the required foreground
     Foreground = array1[0].trim();
    }
   }
  }
  
  // finding percentage of battery drained
  Battery_percentage = (Battery_drain / 1000);

  // creating json object 
  JSONObject obj = new JSONObject();

  // adding the required data into the json object
  obj.put("Foreground_time", Foreground);
  obj.put("Battery_drain", Battery_drain);
  obj.put("Battery_percentage", Battery_percentage);

  //writing the json object into json file
  FileWriter file1 = new FileWriter("C:\\Users\\USER\\eclipse-workspce\\arun\\BatteryParsing\\src\\Hackathon_Java\\output.json");
  file1.write(obj.toString());
  file1.flush();
  file1.close();

  //displaying data stored in the json object
  System.out.println(obj);
 }
}
