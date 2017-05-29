package polygons;

import calculations.Coordinate;

public abstract class Polygon 
{
	protected Coordinate[] points;
	protected double polygonArea;
	
	public Polygon()
	{
		
	}
	
	public Polygon(Coordinate[] coords)
	{
		this.points = coords;
	}
	
	public double getArea()
	{
		return this.polygonArea;
	}
}
