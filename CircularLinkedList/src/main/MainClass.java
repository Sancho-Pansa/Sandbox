package main;

public class MainClass 
{

	public static void main(String[] args) 
	{
		// Demonstration of Circular Linked List
		CircularLinkedList<Integer> list = new CircularLinkedList<>();
		for(int i = 0; i < 5; i++)
		{
			list.push(i);
		}
		list.addFirst(15);
		
		list.add(25, 3);
		
		System.out.println(list.pop());
	}

}
