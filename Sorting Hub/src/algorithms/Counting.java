package algorithms;

public class Counting extends SortingParent
{
	private int auxArray[];
	
	public Counting(int input[])
	{
		super.sortArray = input;
		int outputLength = findMax() + 1;
		auxArray = new int[outputLength];
		
		for(int i = 0; i < outputLength; i++)
		{
			auxArray[i] = 0;
		}
		
		CountingSort();
	}
	
	private void CountingSort()
	{		
		int index = 0;
		
		for(int i = 0; i < sortArray.length; i++)
		{
			auxArray[sortArray[i]]++;
		}
		
		for(int i = 0; i < auxArray.length; i++)
		{
			for(int j = 0; j < auxArray[i]; j++)
			{
				sortArray[index] = i;
				index++;
			}
		}
	}
	
	private int findMax()
	{
		int max = 0;
		
		for(int x: sortArray)
		{
			if(x > max)
				max = x;
		}
		
		return max;
	}
}
