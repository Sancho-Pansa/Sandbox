package main;

public class SimpsonMethod extends AbstractIntegrationMethod 
{

	public SimpsonMethod() 
	{
		super();
	}

	public SimpsonMethod(double left, double right, double step) 
	{
		super(left, right, step);
	}
	
	@Override
	protected void integralSummation()
	{
		for(int i = 1; i < super.stepNum; i += 2)
		{
			double prev = super.getCurrentPoint(i - 1);
			double current = super.getCurrentPoint(i);
			double next = super.getCurrentPoint(i + 1);
			super.integral += function(prev) + 4 * function(current) + function(next);
		}
		super.integral *= super.netStep / 3;
	}
}
