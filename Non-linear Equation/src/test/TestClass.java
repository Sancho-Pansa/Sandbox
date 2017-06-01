package test;

import main.*;

public class TestClass {

	public static void main(String[] args) 
	{
		double precision = 0.00001;
		double startingPoint = 1;
		
		Bisection first = new Bisection(0, startingPoint, precision);
		FixedPointIteration second = new FixedPointIteration(1.2, precision);
		NewtonMethod third = new NewtonMethod(precision);
		SecantMethod forth = new SecantMethod(precision);
		
		System.out.println("Method name\tBisection\tFixedPoint\t\tNewton\t\t\tSecant");
		
		System.out.println("Result" + "\t\t" + first.solveSystem() 	+ "\t" + second.solveEquation(startingPoint) 
																	+ "\t" + third.solveEquation(startingPoint) 
																	+ "\t" + forth.solveEquation(startingPoint));
		
		System.out.println("Iterations" + "\t" + first.getNumberOfRequests() + "\t\t" + second.getNumberofIterations()
																			 + "\t\t\t" + third.getNumberofIterations()
																			 + "\t\t\t" + forth.getNumberofIterations());
		
		System.out.println("f(x) calls" + "\t" + first.getNumberOfRequests() 	+ "\t\t"   + second.getNumberOfRequests()
																				+ "\t\t\t" + third.getNumberOfRequests()
																				+ "\t\t\t" + forth.getNumberOfRequests());
	}
}
