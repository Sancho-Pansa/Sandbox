package test;

import main.*;
import rectanglemethod.*;

public class TestClass {

	public static void main(String[] args) 
	{
		TrapezoidalMethod dummy = new TrapezoidalMethod(0.0, 2.0, 1);
		System.out.println(dummy.countIntegral());
	}

}
