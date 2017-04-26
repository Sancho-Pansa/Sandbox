package algorithms;

public class QuickSort extends SortingParent
{
	public QuickSort(int input[])
	{
		super.sortArray = input;
		quickSort(0, sortArray.length - 1);
	}
	
	private void quickSort(int startIndex, int endIndex)
	{
		if(startIndex >= endIndex)
			return;
		
		int i = startIndex;
		int j = endIndex;
		int refIndex = startIndex - (startIndex - endIndex) / 2;
		
		while(i < j)
		{
			while(i < refIndex && sortArray[i] <= sortArray[refIndex])
				i++;
			while(j > refIndex && sortArray[j] >= sortArray[refIndex])
				j--;

			if(i < j)
			{
				int temp = sortArray[i];
				sortArray[i] = sortArray[j];
				sortArray[j]= temp;
				if(i == refIndex)
					refIndex = j;
				else if(j == refIndex)
					refIndex = i;
			}
		}
		
		quickSort(startIndex, refIndex);
		quickSort(refIndex + 1, endIndex);
		
	}

}
