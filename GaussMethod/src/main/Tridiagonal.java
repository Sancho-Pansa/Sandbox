package main;

public class Tridiagonal 
{
	private double[][] matrix;
	private double[] vector;
	private double[] ans;
	
	private double[] alpha;
	private double[] beta;
	
	private final int N;
	
	public Tridiagonal()
	{
		N = 0;
	}
	
	public Tridiagonal(double[] diagonal, double[] underDiag, double[] aboveDiag)
	{
		N = diagonal.length;
		matrix = new double[N][];
		ans = new double[N];
		for(int i = 0; i < N; i++)
		{
			matrix[i] = new double[N];
		}
		
		for(int i = 0; i < N; i++)
			for(int j = 0; j < N; j++)
			{
				if(j == i)
					matrix[i][j] = diagonal[i];
				else if(j == i + 1)
					matrix[i][j] = aboveDiag[i];
				else if(j == i - 1)
					matrix[i][j] = underDiag[j];
				else
					matrix[i][j] = 0;
			}
	}
	
	public double[] solveSystem(double[] vector)
	{
		this.vector = vector;
		this.directWay();
		this.reverseWay();
		
		return this.ans;
	}
	
	private void directWay()
	{
		this.alpha = new double[N];
		this.beta = new double[N];
		alpha[0] = -1 * matrix[0][1] / matrix[0][0];
		beta[0] = this.vector[0] / matrix[0][0];
		for(int i = 1; i < N - 1; i++)
		{
			alpha[i] = -1 * matrix[i][i + 1] / (matrix[i][i - 1] * alpha[i - 1] + matrix[i][i]);
			beta[i] = (vector[i] - matrix[i][i - 1] * beta[i - 1]) / (matrix[i][i - 1] * alpha[i - 1] + matrix[i][i]);
		}
	}
	
	private void reverseWay()
	{
		ans[N - 1] = (vector[N - 1] - matrix[N - 1][N - 2] * beta[N - 2])
							/ (matrix[N - 1][N - 1] + matrix[N - 1][N - 2] * alpha[N - 2]);
		for(int i = N - 2; i >= 0; i--)
		{
			ans[i] = alpha[i] * ans[i + 1] + beta[i];
		}
	}
}
