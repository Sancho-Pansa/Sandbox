package main;

public class Gauss 
{
	private double[][] matrix;
	private double[] vector;
	private double[] ans;
	
	public Gauss()
	{
		
	}
	
	public Gauss(double[][] matrix, double[] vector)
	{
		this.matrix = new double [matrix.length][];
		for(int i = 0; i < this.matrix.length; i++)
			this.matrix[i] = new double [matrix.length + 1];
		for(int i = 0; i < this.matrix.length; i++)
			for(int j = 0; j < this.matrix[i].length; j++)
			{
				if(j == this.matrix[i].length - 1)
				{
					this.matrix[i][j] = vector[i];
					continue;
				}
				else
				{
					this.matrix[i][j] = matrix[i][j];
				}
			}
	}
	
	public double[] solveSystem()
	{
		directWay();
		reverseWay();
		return ans;
	}
	
	public double getNorm(double[] vector)
	{
		double max = Math.abs(vector[0]);
		for(int i = 1; i < vector.length; i++)
		{
			if(Math.abs(vector[i]) > max)
				max = Math.abs(vector[i]);
		}
		return max;
	}
	
	private void directWay()
	{
		for(int i = 0; i < matrix.length; i++)
		{
			if(matrix[i][i] != 0)
			{
				double a = matrix[i][i];
				for(int j = 0; j < matrix[i].length; j++)
				{
					matrix[i][j] /= a;
				}
				for(int k = 0; k < matrix.length; k++)
				{
					a = matrix[k][i] / matrix[i][i];
					if(k == i)
						continue;
					for(int j = 0; j < matrix[k].length; j++)
					{
						matrix[k][j] = matrix[k][j] - a * matrix[i][j];
					}
				}
			}
		}
	}
	
	private void reverseWay()
	{
		ans = new double[matrix[0].length - 1];
		for(int i = 0; i < matrix.length; i++)
		{
			this.ans[i] = this.matrix[i][this.matrix[i].length - 1];
		}
	}
}
