package main;

import static java.lang.Math.abs;

public class SecantMethod extends NewtonMethod 
{
	public SecantMethod(double precision)
	{
		super(precision);
	}
	
	@Override
	public double solveEquation(double startingPoint)
	{
		super.iterNum = 0;
		super.reqNum = 0;
		super.prev = startingPoint;
		super.current = prev - super.function(prev) / super.derivative(prev);
		while(abs(current - prev) > eps)
		{
			iteration();
		}
		return current;
	}
	
	@Override
	protected void iteration()
	{
		super.iterNum++;
		double temp = current;
		current = current - super.function(current) / derivative(current);
		prev = temp;
	}
	
	@Override
	protected double derivative(double x)
	{
		double anotherDerivative = (prev - current) / (super.function(prev) - super.function(current)); 
		
		return anotherDerivative;		
	}
}
