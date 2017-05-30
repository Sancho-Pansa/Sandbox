package main;

import static java.lang.Math.*;

public class NewtonMethod extends FixedPointIteration
{
	
	public NewtonMethod(double precision)
	{
		super(0.0, precision);
	}
	
	@Override
	protected void iteration()
	{
		super.iterNum++;
		prev = current;
		current = current - super.function(current) / derivative(current);
	}
	
	protected double derivative(double x)
	{
		return 3.0 * pow((x- 0.2), 2.0);
	}
}
