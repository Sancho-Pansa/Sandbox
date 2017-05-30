package main;

public class Bisection extends IterationMethod
{
	private double left;
	private double right;
	
	private int iterNum;
	
	public Bisection()
	{
		super(0);
	}
	
	public Bisection(double leftBorder, double rightBorder, double precision)
	{
		super(precision);
		this.left = leftBorder;
		this.right = rightBorder;
	}
	
	public double solveSystem()
	{
		super.reqNum = 0;
		this.iterNum = 0;
		while(right - left >= 2 * eps)
		{
			
			iteration();
		}
		return super.ans;
	}
	
	public int getNumberofIterations()
	{
		return this.iterNum;
	}
	
	private void iteration()
	{
		iterNum++;
		double currentX = (right + left) / 2.0;
		double temp = function(currentX);
		if(temp * function(left) < 0)
			right = currentX;
		else
			left = currentX;
		super.ans = currentX;
	}
}
