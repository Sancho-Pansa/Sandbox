package mechanics;

import java.util.Scanner;

public class Framework 
{
	private int players;
	
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
	
	
}
