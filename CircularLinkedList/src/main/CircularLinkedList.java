package main;

import java.util.*;
import java.lang.StackOverflowError;

/**
 * This class is used to create circular linked list.
 * This list acts like usual linked list
 * However the last item points on the first element and vice versa
 * 
 * @author SanchoPansa
 * 
 * @param <T>
 * @version 1.0
 */

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
	
	/**
	 * Pushes next element into the list 
	 * @param t
	 */
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
	
	/**
	 * Inserts element as first element of list
	 * @param t
	 */
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
	
	/**
	 * Inserts element on specified index
	 * @param t
	 * @param index
	 */
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
	
	/**
	 * Inserts element on specified index
	 * @return
	 */
	public T pop()
	{
		T t = lastNode.getContent();
		firstNode.backward = lastNode.backward;
		lastNode = lastNode.backward;
		lastNode.forward = firstNode;
		
		size--;
		return t;
	}
	
	/**
	 * Returns last element of circular list, but do not removes it as pop()
	 * @return
	 * @throws NoSuchElementException
	 */
	public T getLast() throws NoSuchElementException
	{
		return lastNode.getContent();
	}

	/**
	 * Returns size of list
	 * @return
	 */
	public int size() 
	{
		return size;
	}
}
