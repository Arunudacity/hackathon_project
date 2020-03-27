package hackathon_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
@Path("/display/")
public class Table {
public static Connection getConnection() throws SQLException {
		
		Connection con=null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/employeedb?autoReconnect=true","root","Arunak@6561");
		System.out.println("Success");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}

public static String getDetails() throws SQLException {
	PreparedStatement preparedStatement3=getConnection().prepareStatement("SELECT * FROM CPU");
	ResultSet result2=preparedStatement3.executeQuery();
	String max="";
	String avg="";
	while(result2.next()) {
		max=result2.getString(1);
		avg=result2.getString(2);
	}
	String s="<h3>CPU VALUES:</h3><table style=\"border: 1px solid black\"><tr><th style=\"border: 1px solid black\">Transaction Name</th><th style=\"border: 1px solid black\">Max</th><th style=\"border: 1px solid black\">Avg</th></tr><tr><td style=\"border: 1px solid black\">Sample Trasaction</td><td style=\"border: 1px solid black\">"+max+"</td><td style=\"border: 1px solid black\">"+avg+"</td></tr></table>";
	return s;
}
@Path("/table")
@GET
@Produces(MediaType.TEXT_HTML)
public String display() throws SQLException {
	String s=getDetails();
	return s;
	
	
}

}
