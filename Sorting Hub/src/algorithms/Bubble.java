package algorithms;

public class Bubble extends SortingParent 
{
	public Bubble(int input[])
	{
		this.sortArray = input;
		bubbleSort();
	}
	
	private void bubbleSort()
	{
		int temp;
		for(int i = 0; i < sortArray.length - 1; i++)
		{
			for(int j = 0; j < sortArray.length - 1 - i; j++)
			{
				if(sortArray[j] > sortArray[j + 1])
				{
					temp = sortArray[j];
					sortArray[j] = sortArray[j + 1];
					sortArray[j + 1] = temp;
				}
			}
		}
	}
	
	public int[] getArray()
	{
		return sortArray;
	}
}
