package algorithms;

public abstract class SortingParent
{
	
	protected int sortArray[];
	
	public SortingParent()
	{
		System.out.println("\n" + this.getClass() + " activated!");
	}
	
	public int[] getArray()
	{
		return sortArray;
	}
}
