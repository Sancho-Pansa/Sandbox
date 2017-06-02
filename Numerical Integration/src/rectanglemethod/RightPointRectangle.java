package rectanglemethod;

public class RightPointRectangle extends AbstractRectangleMethod
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
