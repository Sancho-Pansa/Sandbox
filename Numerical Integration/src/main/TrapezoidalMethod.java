package main;

public class TrapezoidalMethod extends AbstractIntegrationMethod
{
	public TrapezoidalMethod()
	{
		super();
	}
	
	public TrapezoidalMethod(double left, double right, double step)
	{
		super(left, right, step);
	}
	
	@Override
	protected void integralSummation()
	{
		for(int i = 1; i < super.stepNum; i++)
		{
			double x = super.getCurrentPoint(i);
			super.integral += ((function(x) + function(x + super.netStep)) / 2) * super.netStep;
		}
	}
}
