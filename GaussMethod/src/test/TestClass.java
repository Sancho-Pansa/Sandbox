package test;

import main.*;

public class TestClass {

	public static void main(String[] args) 
	{
		//Gauss Method
		
		double[][] matrix = {	{69.8010,	11.5380,	-80.7660,	-2.8800},
								{550.0160,	93.0030,	-644.7280,	-23.0400},
								{137.8040,	23.0760,	-160.633,	-5.7600},
								{7.5600,	1.2420,		-8.6940,	-0.5430}
							};
		double[] vector = {-190.932, -1612.059, -392.652, -23.865};
		Gauss dummy = new Gauss(matrix, vector);
		double[] ans = dummy.solveSystem();
		for(int i = 0; i < ans.length; i++)
		{
			System.out.println(ans[i]);
		}
		
		System.out.println(dummy.getNorm(ans));
		/*
		// Tridiagonal method
		double[] mainDiag = {151, 73, 56, 88, 94, 92};
		double[] underDiag = {0, 1, -1, 1, 1};
		double[] aboveDiag = {1, 1, 0, -1, 1};
		double[] f = {14, 7, 6, 9, 9, 9};
		
		Tridiagonal stub = new Tridiagonal(mainDiag, underDiag, aboveDiag);
		double[] ans = stub.solveSystem(f);
		for(int i = 0; i < ans.length; i++)
			System.out.println(ans[i]);
		*/
		//
	}

}
