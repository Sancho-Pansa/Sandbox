package mechanics;

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
	
	public void setInvestigators()
	{
		this.cll = new CircularLinkedList<>();
		Scanner in = new Scanner(System.in);
		for(int i = 0; i < this.players; i++)
		{
			Investigator invest = new Investigator(in.nextLine());
			cll.addFirst(invest);
		}
		in.close();
		cll.scroll();
		while(!cll.isEmpty())
			System.out.println(cll.pop().getSanity());
	}
	
	public void setAncientOne()
	{
		Scanner in = new Scanner(System.in);
		this.ancientOne = new AncientOne(in.nextLine());
		in.close();
	}
	
	public AncientOne getAncientOne()
	{
		return this.ancientOne;
	}
}
