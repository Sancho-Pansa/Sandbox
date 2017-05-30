package main;

import static java.lang.Math.pow;

public abstract class IterationMethod 
{
	protected double eps;
	protected double ans;
	
	protected int reqNum;
	
	public IterationMethod(double precision)
	{
		this.eps = precision;
	}
	
	protected double function(double x)
	{
		reqNum++;
		return pow((x - 0.2), 3.0);
	}
	
	public int getNumberOfRequests()
	{
		return this.reqNum;
	}
}
