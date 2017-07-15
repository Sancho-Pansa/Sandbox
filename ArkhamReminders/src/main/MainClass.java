package main;

import mechanics.*;

public class MainClass 
{

	public static void main(String[] args)
	{
		Framework fw = new Framework(4);
		//fw.setInvestigators();
		fw.setAncientOne();
		System.out.println(fw.getAncientOne().getAwakening());
	}
}
