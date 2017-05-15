package main;

import java.util.*;

/**
 * This class is used to create circular linked list.
 * This list acts like usual linked list
 * However the last item points on the first element and vice versa
 * 
 * @author SanchoPansa
 * 
 * @param <T>
 * @version 2.0
 */

public class CircularLinkedList<T>
{
	private transient Node<T> firstNode;
	private transient Node<T> lastNode;
	
	private transient int size = 0;
	
	public CircularLinkedList()
	{
		//Do nothing
	}
	
	private void linkFirst(T t)
	{
		final Node<T> f = this.firstNode;
		final Node<T> l = this.lastNode;
		
		final Node<T> newNode = new Node<>(l, t, f);
		firstNode = newNode;
		if(f == null || l == null)
		{
			newNode.forward = newNode;
			newNode.backward = newNode;
			lastNode = newNode;
		}
		else
		{
			f.backward = newNode;
			lastNode.forward = newNode;
		}
		size++;
	}
	
	private void linkLast(T t)
	{
		final Node<T> f = this.firstNode;
		final Node<T> l = this.lastNode;
		
		final Node<T> newNode = new Node<>(l, t, f);
		lastNode = newNode;
		if(f == null || l == null)
		{
			newNode.backward = newNode;
			newNode.forward = newNode;
			firstNode = newNode;
			lastNode = newNode;
		}
		else
		{
			firstNode.backward = newNode;
			l.forward = newNode;
		}
		size++;
	}
	
	private void linkBefore(T t, Node<T> succeed)
	{
		final Node<T> previous = succeed.backward;
		final Node<T> newNode = new Node<>(previous, t, succeed);
		succeed.backward = newNode;
		if(previous == null)
		{
			linkFirst(t);
		}
		else
		{
			previous.forward = newNode;
		}
		size++;
	}
	
	private T unlinkFirst(Node<T> f)
	{
		final T element = f.content;
		final Node<T> next = f.forward;
		firstNode = next;
		if(next == f)
		{
			firstNode = null;
			lastNode = null;
		}
		else
		{
			f.content = null;
			f.backward = null;
			f.forward = null;
			firstNode.backward = lastNode;
			lastNode.forward = firstNode;
		}
		size--;
		return element;
	}
	
	private T unlinkLast(Node<T> l)
	{
		final T element = l.content;
		final Node<T> prev = l.backward;
		lastNode = prev;
		if(prev == l)
		{
			firstNode = null;
			lastNode = null;
		}
		else
		{
			l.content = null;
			l.backward = null;
			l.forward = null;
			firstNode.backward = lastNode;
			lastNode.forward = firstNode;
		}
		size--;
		return element;
	}
	
	private T unlink(Node<T> c)			// c stands for "chosenNode"
	{
		final T element = c.content;
		final Node<T> prev = c.backward;
		final Node<T> next = c.forward;
		
		if(prev == c)
		{
			firstNode = null;
			lastNode = null;
		}
		else 
		{
			prev.forward = next;
			next.backward = prev;
			c.forward = null;
			c.backward = null;
		}
		c.content = null;
		size--;
		return element;
	}
	
	private Node<T> createNodeAt(int index)
	{
		if(index < (size >> 1))
		{
			Node<T> newNode = this.firstNode;
			for(int i = 0; i < index; i++)
				newNode = newNode.forward;
			return newNode;
		}
		else
		{
			Node<T> newNode = this.lastNode;
			for(int i = size - 1; i > index; i--)
				newNode = newNode.backward;
			return newNode;
		}
		
	}
	
	private boolean isPositionIndex(int index)
	{
		return index >=0 && index <= this.size;
	}
	
	private String getOutOfBoundsMsg(int index)
	{
		return "Index " + index + " exceeds size of stack (" + this.size + ")"; 
	}
	
	/*
	 * Private methods end here
	 * 
	 * Public methods start here
	 */
	
	/*
	 * Adding methods start here
	 */
	public void addFirst(T t)
	{
		linkFirst(t);
	}
	
	public void addLast(T t)
	{
		linkLast(t);
	}
	
	public void addAt(int index, T t)
	{
		if(!this.isPositionIndex(index))
			throw new IndexOutOfBoundsException(this.getOutOfBoundsMsg(index));
		if(index == size)
			linkLast(t);
		else
			linkBefore(t, createNodeAt(index));
	}

	public void push(T t)
	{
		addFirst(t);
	}
	
	/*
	 * Remove methods start here
	 */
	public T removeFirst()
	{
		final Node<T> f = firstNode;
		if(f == null)
			throw new NoSuchElementException();
		return unlinkFirst(f);
	}
	
	public T removeLast()
	{
		final Node<T> l = lastNode;
		if(l == null)
			throw new NoSuchElementException();
		return unlinkLast(l);
	}
	
	public T removeAt(int index)
	{
		if(!this.isPositionIndex(index))
			throw new IndexOutOfBoundsException(this.getOutOfBoundsMsg(index));
		return unlink(createNodeAt(index));
			
	}
	
	public T pop()
	{
		return removeFirst();
	}
	
	public int size()
	{
		return this.size;
	}
	
	public boolean isEmpty()
	{
		return this.firstNode == null || this.lastNode == null;
	}
	
	private static class Node<T>
	{
		private T content;
		private Node<T> backward;
		private Node<T> forward;
		
		Node(Node<T> prev, T elem, Node<T> next)
		{
			this.backward = prev;
			this.content = elem;
			this.forward = next;
		}
	}
}
