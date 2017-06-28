package test;

import main.*;
import rectanglemethod.*;

public class TestClass {

	public static void main(String[] args) 
	{
		SimpsonMethod dummy = new SimpsonMethod(0.0, 2.0, 0.01);
		System.out.println(dummy.countIntegral());
	}

}
