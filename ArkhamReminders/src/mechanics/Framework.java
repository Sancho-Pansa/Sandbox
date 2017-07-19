package mechanics;

import java.util.ArrayList;
import java.util.Scanner;

import essence.CircularLinkedList;

public class Framework 
{
	private int players;
	private CircularLinkedList<Investigator> cll;
	private AncientOne ancientOne;
	
	public Framework(int players)
	{
		setPlayers(players);
	}
	
	public int getPlayers()
	{
		return players;
	}

	public void setPlayers(int players)
	{
		if(players <= 0 || players > 8)
			throw new IllegalArgumentException("Incorrect number of players");
		this.players = players;
	}
	
	public void setInvestigators(ArrayList<String> names)
	{
		this.cll = new CircularLinkedList<>();
		for(String x : names)
		{
			cll.push(new Investigator(x));
		}
		System.out.println(cll.pop().getMaxHealth());
	}
	
	public void setAncientOne(String name)
	{
		this.ancientOne = new AncientOne(name);
	}
	
	public AncientOne getAncientOne()
	{
		return this.ancientOne;
	}
}
