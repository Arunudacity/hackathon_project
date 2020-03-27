package Hackathon_Java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class BatteryParsing {
	public static void main(String[] args)throws Exception 
	  { 
		String batteryDrainString="Uid u0a202";
		String foreGroundString="Foreground activities";
		float Battery_percentage=0,Battery_drain=0;
		File file = new File("C:\\Users\\USER\\eclipse-workspce\\arun\\BatteryParsing\\src\\Hackathon_Java\\input.txt"); 
		BufferedReader f = new BufferedReader(new FileReader(file)); 
		String string1,string2,Foreground=""; 
		while ((string1 = f.readLine()) != null) 	
		{
			String[] array = string1.split(":");
			if(array.length>1)
			{ 
				array[0]=array[0].trim();
				if(array[0].equals(batteryDrainString)  )
				{
		         string2=array[1];
		         String[] array1 = string2.split("\\(");
		         Battery_drain=Float.parseFloat(array1[0]); 
		        
				}
				if(array[0].equals(foreGroundString))
				{
               string2=array[1];
		         String[] array1 = string2.split("\\(r");
		         Foreground=array1[0].trim();
		         
				} 
			}
	   
			  
		}
		Battery_percentage=(Battery_drain/1000);
		
		
		JSONObject obj=new JSONObject();
		obj.put("Foreground_time",Foreground);
		obj.put("Battery_drain" ,Battery_drain);
		obj.put("Battery_percentage" ,Battery_percentage);
		FileWriter file1=new FileWriter("C:\\Users\\USER\\eclipse-workspce\\arun\\BatteryParsing\\src\\Hackathon_Java\\output.json");
		file1.write(obj.toString());
		file1.flush();
		file1.close();
		System.out.println(obj);
		
	  } 
	


}
