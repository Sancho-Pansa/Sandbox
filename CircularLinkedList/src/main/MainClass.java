package main;

public class MainClass 
{

	public static void main(String[] args) 
	{
		// Demonstration of Circular Linked List
		CircularLinkedList<Integer> list = new CircularLinkedList<>();
		for(int i = 0; i < 10; i++)
		{
			list.push((int) (Math.random() * 100));
		}
		for(int i = 0; i < 10; i++)
		{
			System.out.print(list.pop());
			System.out.println("\t" + list.size());
		}
	}

}
