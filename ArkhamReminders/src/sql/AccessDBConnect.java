package sql;

import java.io.File;
import java.sql.*;

/**
 * This class creates connection with MS Access database on selected address
 * Its only method emulates SELECT query to database in order to get Investigators features
 * This class uses UCanAccess Driver to connect to MS Access as JDBC Driver do not support this operation
 * in Java SE 8
 * @author SanchoPansa
 *
 */
//TODO Связать с помощью Мавена(?)
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
