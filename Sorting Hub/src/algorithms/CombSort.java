package algorithms;

public class CombSort extends SortingParent 
{
	private final double SHRINK_FACTOR = 1.247;
	
	public CombSort(int input[])
	{
		super.sortArray = input;
		combSort();
	}
	
	private void combSort()
	{
		boolean swapFlag = true;
		int gap = sortArray.length;
		
		while(gap > 1 || swapFlag)
		{
			if(gap > 1)
				gap = (int) (gap / SHRINK_FACTOR);
			int i = 0;
			swapFlag = false;
			while(i + gap < sortArray.length)
			{
				if(sortArray[i] > sortArray[i + gap])
				{
					int temp = sortArray[i];
					sortArray[i] = sortArray[i + gap];
					sortArray[i + gap] = temp;
					swapFlag = true;
				}
				i++;
			}
		}
	}

}
