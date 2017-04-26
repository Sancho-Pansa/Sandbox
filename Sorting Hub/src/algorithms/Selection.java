package algorithms;

public class Selection extends SortingParent
{
	public Selection(int input[])
	{
		super.sortArray = input;
		SelectionSort();
	}
	
	private void SelectionSort()
	{
		int minIndex;
		int temp;
		
		for(int i = 0; i < sortArray.length; i++)
		{
			minIndex = findMinIndex(i);
			if(minIndex == i)
				continue;
			else
			{
				temp = sortArray[i];
				sortArray[i] = sortArray[minIndex];
				sortArray[minIndex] = temp;
			}
		}
	}
	
	private int findMinIndex(int index)
	{
		int min = sortArray[index];
		int minIndex = 0;
		for(int i = index; i < sortArray.length; i++)
		{
			if(sortArray[i] <= min)
			{
				min = sortArray[i];
				minIndex = i;
			}
		}
		
		return minIndex;
	}
}
