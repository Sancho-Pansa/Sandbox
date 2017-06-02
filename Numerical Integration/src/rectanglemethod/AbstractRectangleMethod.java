package rectanglemethod;

import main.AbstractIntegrationMethod;

public abstract class AbstractRectangleMethod extends AbstractIntegrationMethod
{
	protected int stepNum;
	
	public AbstractRectangleMethod()
	{
		 super();
	}
	
	public AbstractRectangleMethod(double left, double right, double step)
	{
		super(left, right);
		super.netStep = step;
	}
	
	public double getIntegral()
	{
		super.integral = 0;
		stepNum = this.getCountOfSteps();
		integralSummation();
		return super.integral;
	}
	
	private int getCountOfSteps()
	{
		return (int) ((super.right - super.left) / this.netStep);
	}
	
	protected double getCurrentPoint(int pointNum)
	{
		return super.left + pointNum * this.netStep;
	}
	
	protected void integralSummation()
	{
		
	}
}
