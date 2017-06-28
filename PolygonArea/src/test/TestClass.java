package test;

import polygons.*;
import calculations.Coordinate;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TestClass 
{

	public static void main(String[] args) 
	{
		Coordinate coord[];
		coord = new Coordinate[4];
		coord[0] = new Coordinate(-3, 0);
		coord[1] = new Coordinate(-3, 5);
		coord[2] = new Coordinate(3, 5);
		coord[3] = new Coordinate(3, 0);
		//coord[4] = new Coordinate(3, 0);
		ConvexPolygon dummy = new ConvexPolygon(coord);
		System.out.println(dummy.computeArea());
	}

}
