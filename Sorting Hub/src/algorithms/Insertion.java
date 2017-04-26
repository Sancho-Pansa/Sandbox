package algorithms;

public class Insertion extends SortingParent
{
	public Insertion(int input[])
	{		
		super.sortArray = input;
		insertionSort();
	}
	
	private void insertionSort()
	{
		for(int i = 0; i < sortArray.length; i++)
		{
			int temp = sortArray[i];
			int j = i - 1;
			while(j >= 0 && sortArray[j] > temp)
			{
				sortArray[j + 1] = sortArray[j];
				j--;
			}
			sortArray[j + 1] = temp;
		}
	}
}
