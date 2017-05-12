package main;

import java.util.*;
import java.lang.StackOverflowError;

public class CircularLinkedList<T>
{
	private StackOverflowError overflowException = new StackOverflowError("Index overflow");
	
	private Node<T> firstNode;
	private Node<T> lastNode;
	private Node<T> nodeCursor;
	
	private boolean isClear = true;
	
	private int size = 0;
	
	public CircularLinkedList()
	{
		
	}
	
	public void push(T t)
	{
		if(isClear)
		{
			isClear = false;
			firstNode = new Node<T>(t);
			firstNode.backward = firstNode;
			firstNode.forward = firstNode;
			lastNode = firstNode;
		}
		else
		{
			Node<T> addingNode = new Node<>(t);
			addingNode.backward = lastNode;
			lastNode.forward = addingNode;
			addingNode.forward = firstNode;
			firstNode.backward = addingNode;
			lastNode = addingNode;
		}
		nodeCursor = lastNode;
		size++;
	}
	
	public void addFirst(T t)
	{
		Node<T> addingNode = new Node<>(t);
		addingNode.forward = firstNode;
		firstNode.backward = addingNode;
		
		addingNode.backward = lastNode;
		lastNode.forward = addingNode;
		
		firstNode = addingNode;
		
		size++;
	}
	
	public void add(T t, int index)
	{
		if(index > size)
		{
			throw overflowException;
		}
		
		int iterator = 0;
		nodeCursor = firstNode;
		while(iterator != index)
		{
			nodeCursor = nodeCursor.forward;
			iterator++;
		}
		
		Node<T> addingNode = new Node<>(t);
		
		addingNode.backward = nodeCursor.backward;
		addingNode.forward = nodeCursor;
		
		nodeCursor.backward.forward = addingNode;
		nodeCursor.backward = addingNode;
		
		size++;
	}
	
	public T pop()
	{
		T t = lastNode.getContent();
		firstNode.backward = lastNode.backward;
		lastNode = lastNode.backward;
		lastNode.forward = firstNode;
		
		size--;
		return t;
	}
	
	public T getLast() throws NoSuchElementException
	{
		return lastNode.getContent();
	}

	public int size() 
	{
		return size;
	}
}
