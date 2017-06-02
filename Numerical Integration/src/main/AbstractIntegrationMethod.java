package main;

import static java.lang.Math.*;

public abstract class AbstractIntegrationMethod 
{
	protected double left;
	protected double right;
	protected double netStep;
	protected double integral;
	
	public AbstractIntegrationMethod()
	{
		
	}
	
	public AbstractIntegrationMethod(double left, double right)
	{
		this.left = left;
		this.right = right;
	}
	
	protected double function(double x)
	{
		return pow(x, 1.0);
	}
}
