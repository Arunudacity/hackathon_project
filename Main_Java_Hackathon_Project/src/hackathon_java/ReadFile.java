



package hackathon_java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


	
public class ReadFile {
	static BufferedReader br = null;
	
	static JSONObject jsonObject1 = new JSONObject();
	static JSONObject jsonObject2 = new JSONObject();
	static JSONObject jsonObject3 = new JSONObject();
	static JSONObject jsonObject4 = new JSONObject();
	static ArrayList<Double> ar=new ArrayList<Double>();
	
	static JSONArray array = new JSONArray();
	public static Connection getConnection() throws SQLException {
		
		Connection con=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?autoReconnect=true","root","Arunak@6561");
		
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void getDetails(Connection con) throws SQLException {
		int count=1;
		for(double d:ar) {
			String c=count+"s";
			jsonObject2.put(c,d);
				count++;
		}
		jsonObject1.put("values",jsonObject2);
		PreparedStatement preparedStatement2=con.prepareStatement("SELECT * FROM CPU");
		ResultSet result1=preparedStatement2.executeQuery();
		while(result1.next()) {
			double max=result1.getDouble(1);
			double avg=result1.getDouble(2);
			
			jsonObject1.put("max_cpu_value",max);
			jsonObject1.put("avg_cpu_value",avg);
			
		}
		jsonObject3.put("sampletransaction",jsonObject1);
		array.add(jsonObject3);
		
	}

    

	public static void main(String[] args) throws SQLException {
		Connection con=getConnection();

		 Statement s= con.createStatement();
							      
							      String sql = "CREATE TABLE CPU " +
							                   "(MAX_CPU_VALUE DOUBLE not NULL, " +
							                   " AVG_CPU_VALUE  DOUBLE)";
							      s.executeUpdate(sql);
		try {
			String line;
			br = new BufferedReader(new FileReader("C:\\Users\\USER\\eclipse-workspce\\arun\\Hackathon\\src\\hackathon_java\\sample.txt"));
			while ((line = br.readLine()) != null) {
			

				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");

				while (stringTokenizer.hasMoreElements()) {

					int x=0;
					while(x<8) {
						stringTokenizer.nextElement().toString();
						x++;
					}
					
//					required line
					Double reqCPU = Double.parseDouble(stringTokenizer.nextElement().toString());
					while(x<11) {
						stringTokenizer.nextElement().toString();
						x++;
					}
					
					ar.add(reqCPU);
								
					
				}
				
			}
			double total=0;
			for(double t:ar) {
				total+=t;
			}
			double max=Collections.max(ar);
			double avg=total/ar.size();
			
			max = Math.round(max * 100.0) / 100.0;
			avg = Math.round(avg * 100.0) / 100.0;
			String ts="Sample Transaction";
			 s.executeUpdate("insert into CPU (MAX_CPU_VALUE,AVG_CPU_VALUE) values ("+max+","+avg+")");

			getDetails(con);
			System.out.println(array);
			System.out.println();
			System.out.println();
			System.out.println("CPU VALUES :");
			System.out.println("-----------------------------------------------------------------------------");
		    System.out.printf("%30s   %10s   %10s", "Transaction Name", "MAX_CPU_VALUE", "AVG_CPU_VALUE");
		    System.out.println();
		    System.out.println("-----------------------------------------------------------------------------");
		        System.out.format("%30s %10s     %10s",ts,max,avg);
		        System.out.println();
		    
		    System.out.println("-----------------------------------------------------------------------------");
			
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
