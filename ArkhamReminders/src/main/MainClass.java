package main;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

import mechanics.*;

public class MainClass 
{

	public static void main(String[] args)
	{
		
		/*File file = new File("resources\\ArkhamDB.accdb");
		try {
			Connection con = DriverManager.getConnection("jdbc:ucanaccess://" + file.toString());
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("SELECT Sanity FROM Investigators WHERE InvestName='Мэри'");
			while(rs.next())
				System.out.println(rs.getString(1));
			con.close();
		} catch (SQLException e) 
		{
			System.out.println("Something somewhere had gone terribly wrong");
			e.printStackTrace();
		}*/
		for(int i = 0; i < 1000; i++)
		{
			Investigator dummy = new Investigator("Джо");
			System.out.println(dummy.getHealth());
		}
	}
}
