package rectanglemethod;

import main.AbstractIntegrationMethod;

public class MiddlePointRectangle extends AbstractIntegrationMethod
{
	public MiddlePointRectangle()
	{
		super();
	}
	
	public MiddlePointRectangle(double left, double right, double step)
	{
		super(left, right, step);
	}
	
	@Override
	protected void integralSummation()
	{
		for(int i = 0; i < super.stepNum; i++)
		{
			double x = super.getCurrentPoint(i);
			this.integral += super.function(x + super.netStep / 2) * (this.netStep);
		}
	}
}
