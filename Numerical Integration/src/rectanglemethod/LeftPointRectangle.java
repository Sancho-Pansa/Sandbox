package rectanglemethod;

import main.AbstractIntegrationMethod;

public class LeftPointRectangle extends AbstractIntegrationMethod
{	
	public LeftPointRectangle()
	{
		super();
	}
	
	public LeftPointRectangle(double left, double right, double step)
	{
		super(left, right, step);
	}
	
	@Override
	protected void integralSummation()
	{
		for(int i = 0; i < stepNum; i++)
		{
			double x = this.getCurrentPoint(i);
			this.integral += super.function(x) * (this.netStep);
		}
	}
}
