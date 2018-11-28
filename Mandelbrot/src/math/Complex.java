package math;

public class Complex
{
	private double Re;
	private double Im;
	
	public Complex(double x, double y)
	{
		this.Re = x;
		this.Im = y;
	}
	
	public Complex()
	{
		Re = 0;
		Im = 0;
	}

	public double getRe() {
		return Re;
	}

	public void setRe(double re) {
		Re = re;
	}

	public double getIm() {
		return Im;
	}

	public void setIm(double im) {
		Im = im;
	}
	
	public double module()
	{
		return Math.sqrt(Re * Re + Im * Im);
	}
	
	public double arg()
	{
		return Math.atan(Im / Re);
	}

	@Override
	public String toString() {
		return "Complex [" + Re + " + " + Im + "i]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(Im);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(Re);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Complex other = (Complex) obj;
		if (Double.doubleToLongBits(Im) != Double.doubleToLongBits(other.Im))
			return false;
		if (Double.doubleToLongBits(Re) != Double.doubleToLongBits(other.Re))
			return false;
		return true;
	}

	public static Complex addition(Complex a, Complex b)
	{
		return new Complex(a.getRe() + b.getRe(), a.getIm() + b.getIm());
	}
	
	public static Complex substraction(Complex a, Complex b)
	{
		return new Complex (a.getRe() - b.getRe(), a.getIm() - b.getIm());
	}
	
	public static Complex multiplication(Complex a, Complex b)
	{
		return new Complex(a.getRe() * b.getRe() - a.getIm() * b.getIm(), 
				a.getIm() * b.getRe() + a.getRe() * b.getIm());
	}
	
	public static Complex division(Complex x, Complex y)
	{
		double a = x.getRe();
		double b = x.getIm();
		double c = y.getRe();
		double d = y.getIm();
		
		return new Complex((a * c + b * d) / (c * c + d * d), (b * c - a * d) / (c * c + d * d));
	}
	
	public static Complex pow(Complex x, int n)
	{
		double r = x.module();
		r = Math.pow(r, n);
		double phi = x.arg();
		return new Complex(r * Math.cos(phi * n), r * Math.sin(phi * n));
	}
}
