package algorithms;

public class Gnome extends SortingParent
{
	public Gnome(int input[])
	{
		super.sortArray = input;
		gnomeSort();
		
	}
	
	private void gnomeSort()
	{
		for(int i = 0; i < sortArray.length - 1; i++)
		{
			if(sortArray[i] > sortArray[i + 1])
			{
				sortArray = gnomeWalksBack(sortArray, i + 1);
			}
		}
	}
	
	private int[] gnomeWalksBack(int input[], int index)
	{
		int temp;
		
		while(index > 0)
		{
			if(input[index] > input[index - 1])
				break;
			temp = input[index];
			input[index] = input[index - 1];
			input[index - 1] = temp;
			index--;
		}
		
		return input;
	}
}
