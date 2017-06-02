package rectanglemethod;

import main.AbstractIntegrationMethod;

public class RightPointRectangle extends AbstractIntegrationMethod
{
	public RightPointRectangle()
	{
		
	}
	
	public RightPointRectangle(double left, double right, double step)
	{
		super(left, right, step);
	}
	
	@Override
	protected void integralSummation()
	{
		for(int i = 1; i <= stepNum; i++)
		{
			double x = this.getCurrentPoint(i);
			this.integral += super.function(x) * (this.netStep);
		}
	}
}
