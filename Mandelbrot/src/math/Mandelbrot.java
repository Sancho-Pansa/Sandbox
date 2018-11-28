package math;

import java.util.HashMap;

public class Mandelbrot implements Fractal
{
	private final double Z = 0.0;
	private int iterations;
	private int maxIterations;
	private Complex initPoint;
	private HashMap<Integer, Complex> coordinates;
	
	public Mandelbrot(int maxIterations, Complex initialPoint)
	{
		this.maxIterations = maxIterations;
		this.initPoint = initialPoint;
		coordinates = new HashMap<>(maxIterations);
	}
	
	@Override
	public int createFractal()
	{
		this.iterations = 0;
		Complex temp = new Complex(Z, Z);
		coordinates.put(0, temp);
		
		while(iterations < maxIterations && temp.module() < 2.0)
		{
			temp = formula(temp);
			iterations++;
			coordinates.put(iterations, temp);
			//System.out.println(iterations + ": " + temp.toString());
		}
		
		return iterations;
	}
	
	@Override
	public HashMap<Integer, Complex> getCoordinates()
	{
		return coordinates;
	}
	
	public int getIterations() {
		return iterations;
	}

	private Complex formula(Complex enter)
	{
		return Complex.addition(Complex.multiplication(enter, enter), initPoint);
	}
}
