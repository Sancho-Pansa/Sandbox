package polygons;

import calculations.*;

public class ConvexPolygon extends Polygon
{
	public ConvexPolygon()
	{
		super();
	}
	
	public ConvexPolygon(Coordinate[] coord)
	{
		super.points = coord;
	}
	
	public double computeArea()
	{
		super.polygonArea = 0;
		for(int i = 0; i < points.length; i++)
		{
			if(i == points.length - 1)
				polygonArea += points[i].getX() * points[0].getY() - points[i].getY() * points[0].getX();
			else
				polygonArea += points[i].getX() * points[i + 1].getY() - points[i].getY() * points[i + 1].getX();
		}
		
		return 0.5 * Math.abs(super.polygonArea);
	}
	
	public double getArea()
	{
		return super.polygonArea;
	}
}
