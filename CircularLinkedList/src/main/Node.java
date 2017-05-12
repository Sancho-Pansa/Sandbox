package main;

public class Node<T>
{
	private T element;
	
	public Node<T> backward = null;
	
	public Node<T> forward = null;
	
	public Node()
	{
		
	}
	
	public Node(T t)
	{
		this.element = t;
	}
	
	public T getContent()
	{
		return element;
	}
}
