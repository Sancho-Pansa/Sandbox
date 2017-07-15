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
//TODO ������� � ������� ������(?)
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
	
	public String getInvestField(String fieldName, String investName)
	{
		try {
			this.rSet = s.executeQuery("SELECT " + fieldName + " FROM Investigators WHERE InvestName='" + investName + "'");
			rSet.next();
			return rSet.getString(1);
		} catch (SQLException e) {
			System.out.println("Exception in Investigators table");
			e.printStackTrace();
		}
		return null;
	}
	
	public String getAncientField(String fieldName, String ancientName)
	{
		try {
			this.rSet = s.executeQuery("SELECT " + fieldName + " FROM AncientOnes WHERE AncientName='" + ancientName + "'");
			this.rSet.next();
			return rSet.getString(1);
		} catch (SQLException e) {
			System.out.println("Exception in AncientOnes table");
			e.printStackTrace();
		}
		return null;
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
