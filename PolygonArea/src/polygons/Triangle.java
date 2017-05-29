package polygons;

import calculations.Coordinate;

import static java.lang.Math.*;

public class Triangle extends Polygon 
{
	private final static int N = 3;
	
	public Triangle()
	{
		super();
		points = new Coordinate[N];
	}
	
	public Triangle(Coordinate[] coords)
	{
		super(coords);
	}
	
	private boolean checkRange(double a)
	{
		return a >= 0 && a <= 1;
	}
	
	public double computeArea()
	{
		polygonArea = 0.5 * ((	points[0].getX() 	- points[2].getX()) *
							(	points[1].getY() 	- points[2].getY()) - 
							(	points[1].getX() 	- points[2].getX()) * 
							(	points[0].getY() 	- points[2].getY()));
			
		return Math.abs(this.polygonArea);
	}
	
	public boolean isInside(Coordinate dot)
	{
		//1, 2, 3 are representing points of triangle
		double x1 = points[0].getX();
		double x2 = points[1].getX();
		double x3 = points[2].getX();
		
		double y1 = points[0].getY();
		double y2 = points[1].getY();
		double y3 = points[2].getY();
		// Cross product
		double alpha 	= (x1 - dot.getX()) * (y2 - y1) - (x2 - x1) * (y1 - dot.getY());
		double beta 	= (x2 - dot.getX()) * (y3 - y2) - (x3 - x2) * (y2 - dot.getY());
		double gamma 	= (x3 - dot.getX()) * (y1 - y3) - (x1 - x3) * (y3 - dot.getY());
		if(abs(signum(alpha) + signum(beta) + signum(gamma)) == 3 || alpha * beta * gamma == 0)
			return true;
		return false;
	}
}
