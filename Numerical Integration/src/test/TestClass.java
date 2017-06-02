package test;

import rectanglemethod.*;

public class TestClass {

	public static void main(String[] args) 
	{
		MiddlePointRectangle dummy = new MiddlePointRectangle(0.0, 2.0, 1);
		System.out.println(dummy.getIntegral());
	}

}
