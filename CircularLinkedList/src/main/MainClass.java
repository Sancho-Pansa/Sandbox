package main;

import essence.CircularLinkedList;

public class MainClass 
{

	public static void main(String[] args) 
	{
		// Demonstration of Circular Linked List
		CircularLinkedList<Integer> list = new CircularLinkedList<>();
		for(int i = 0; i < 5; i++)
		{
			list.push(i + 1);
			System.out.println(list.getContent(list.getFirst()));
		}
		list.scroll();
		while(!list.isEmpty())
			System.out.println(list.pop());
			
	}

}
