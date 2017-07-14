package sql;

import java.io.File;
import java.sql.*;

public class AccessDBConnect 
{
	private Connection con;
	private Statement s;
	private ResultSet rSet;
	
	public AccessDBConnect()
	{
		final File file = new File("resources\\ArkhamDB.accdb");
		try {
			this.con = DriverManager.getConnection("jdbc:ucanaccess://" + file.toString());
			this.s = con.createStatement();			
		} catch (SQLException e) 
		{
			System.out.println("Something somewhere had gone terribly wrong");
			e.printStackTrace();
		}
	}
	
	public String getField(String fieldName, String investName)
	{
		
		try {
			
			this.rSet = s.executeQuery("SELECT " + fieldName + " FROM Investigators WHERE InvestName='" + investName + "'");
			rSet.next();
			return rSet.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}
	
	public void endConnection()
	{
		try {
			s = null;
			rSet = null;
			this.con.close();
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
