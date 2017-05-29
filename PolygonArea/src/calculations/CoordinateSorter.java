package calculations;

import static java.lang.Math.*;

public final class CoordinateSorter 
{
	private Coordinate sortArray[];
	
	public CoordinateSorter(Coordinate input[])
	{
		this.sortArray = input;
	}
	
	public CoordinateSorter()
	{
		
	}
	
	public void setArray(Coordinate input[])
	{
		this.sortArray = input;
	}
	
	public Coordinate[] sortThis()
	{
		arrangeLowest();
		for(int i = 0; i < sortArray.length - 1; i++)
		{
			int k = this.findNextIndex(i);
			if(k != i + 1)
			{
				Coordinate temp = sortArray[i + 1];
				sortArray[i + 1] = sortArray[k];
				sortArray[k] = temp;
			}
		}
		
		return sortArray;
	}
	
	private void arrangeLowest()
	{
		Coordinate temp;
		int k = 0;
		for(int i = 1; i < sortArray.length; i++)
		{
			if(this.sortArray[i].getY() < this.sortArray[k].getY())
			{
				k = i;
			}
		}
		
		temp = sortArray[0];
		sortArray[0] = sortArray[k];
		sortArray[k] = temp;
	}
	
	private int findNextIndex(int index)
	{
		double min = 3.14;
		int returnIndex = index + 1;
		
		for(int i = index + 1; i < sortArray.length; i++)
		{
			double temp = getArccos(0, i);
			if(min > temp)
			{
				min = temp;
				returnIndex = i;
			}
		}
		return returnIndex;
	}
	
	private double getArccos(int first, int second)
	{
		double a = pow(sortArray[first].getX() - sortArray[second].getX(), 2.0);
		double b = pow(sortArray[first].getY() - sortArray[second].getY(), 2.0);
		double hypotenuse = sqrt(a + b);
		
		/*double hypotenuse = sqrt(
			pow(sortArray[first].getAbscissa() - sortArray[second].getAbscissa(), 2.0)
			+ pow(sortArray[first].getOrdinate() - sortArray[second].getAbscissa(), 2.0));
		*/
		return acos((sortArray[first].getX() - sortArray[second].getX()) / hypotenuse);
	}
}
