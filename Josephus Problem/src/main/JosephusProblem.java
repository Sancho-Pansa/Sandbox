package main;

import java.util.LinkedList;

public class JosephusProblem 
{
	private LinkedList<Integer> list;
	
	private int winner;
	private int period;
	
	public JosephusProblem()
	{
		list = new LinkedList<>();
	}
	
	public JosephusProblem(int peopleQuantity)
	{
		if(peopleQuantity <= 0)
			throw new IndexOutOfBoundsException("Negative number of people");
		list = new LinkedList<>();
		for(int i = peopleQuantity - 1; i >= 0; i--)
		{
			list.push(i + 1);
		}
	}
	
	public void setQuantity(int quantity)
	{
		list.clear();
		for(int i = 0; i < quantity; i++)
			list.push(i);
	}
	
	public void setPeriod(int period)
	{
		if(period <= 0)
			throw new IndexOutOfBoundsException("Negative period");
		this.period = period;
	}
	
	public void decimation()
	{
		int counter = 0;
		int periodCounter = 0;
		while(list.size() > 1)
		{
			if(counter == period - 1)
			{
				System.out.println(list.get(periodCounter) + " is going to Valhalla!");
				list.remove(periodCounter);
				counter = 0;
			}
			periodCounter++;
			counter++;
			if(periodCounter >= list.size())
				periodCounter = 0;
		}
		
		this.winner = list.pop();
		System.out.println(this.winner + " will tell this story");
	}
	
	public int getWinner()
	{
		return this.winner;
	}
}
