package main;

public class MainClass 
{

	public static void main(String[] args) 
	{
		// Demonstration of Circular Linked List
		CircularLinkedList<Integer> list = new CircularLinkedList<>();
		System.out.println(list.isEmpty());
		for(int i = 0; i < 10; i++)
		{
			list.push((int) (Math.random() * 100));
		}	
		list.addLast(-15);
		list.addAt(5, -4);
		list.removeAt(list.size());
		while(list.size() != 0)
		{
			System.out.print(list.pop());
			System.out.print("\t" + list.size());
			System.out.println("\t" + list.isEmpty());
		}
	}

}
