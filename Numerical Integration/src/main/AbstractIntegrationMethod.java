package main;

import static java.lang.Math.*;

public abstract class AbstractIntegrationMethod 
{
	protected double left = 0;
	protected double right = 0;
	protected double netStep = 0;
	protected double integral;
	
	protected int stepNum;
	
	public AbstractIntegrationMethod()
	{
		
	}
	
	public AbstractIntegrationMethod(double left, double right, double step)
	{
		this.left = left;
		this.right = right;
		this.netStep = step;
		this.stepNum = this.getNumOfSteps();
	}
	
	public void setLeft(double left)
	{
		this.left = left;
		this.netStep = this.getNumOfSteps();
	}
	
	public void setRight(double right)
	{
		this.right = right;
		this.netStep = this.getNumOfSteps();
	}
	
	public void setStep(double step)
	{
		this.netStep = step;
	}
	
	public double countIntegral()
	{
		this.integral = 0;
		integralSummation();
		return this.integral;
	}
	
	public double getIntegral()
	{
		return this.integral;
	}
	
	protected double function(double x)
	{
		return pow(x, 1.0);
	}
	
	protected void integralSummation()
	{
		
	}
	
	protected double getCurrentPoint(int pointNum)
	{
		return this.left + pointNum * this.netStep;
	}
	
	private int getNumOfSteps()
	{
		return (int) ((this.right - this.left) / this.netStep);
	}
}
