package main;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

import mechanics.*;

public class MainClass 
{

	public static void main(String[] args)
	{		
			Investigator dummy = new Investigator("���");
			System.out.println(dummy.getHealth());
			Investigator dummy2 = new Investigator("������");
			System.out.println(dummy2.getMoney());
			Investigator.ACCDB.endConnection();
		}
}
