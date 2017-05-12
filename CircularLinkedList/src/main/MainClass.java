package main;

import java.util.LinkedList;

public class MainClass 
{

	public static void main(String[] args) 
	{
		/*LinkedList<Double> list = new LinkedList<>();
		list.add(Math.random());
		list.add(Math.random());
		System.out.println(list.getLast());*/
		
		double array[] = {3, 5, 6, 7, 1};
		CircularLinkedList<Double> myList = new CircularLinkedList<>();
		myList.push(array[0]);
		myList.push(array[2]);
		myList.addFirst(array[1]);
		
		System.out.println(myList.pop());
	}

}
