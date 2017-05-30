package main;

import static java.lang.Math.*;

public class FixedPointIteration extends IterationMethod
{
	protected double current;
	protected double prev;
	protected double lambda;
	
	protected int iterNum;
	
	public FixedPointIteration(double lambda, double precision)
	{
		super(precision);
		this.lambda = lambda;
	}
	
	public double solveEquation(double startingPoint)
	{
		this.iterNum = 0;
		super.reqNum = 0;
		this.current = startingPoint;
		while(abs(current - prev) > eps)
		{
			iteration();
		}
		return current;
	}
	
	protected void iteration()
	{
		iterNum++;
		prev = current;
		current = current - lambda * super.function(current);
	}
	
	public int getNumberofIterations()
	{
		return this.iterNum;
	}
}
