package mechanics;

import java.util.ArrayList;
import list.CircularLinkedList;


public class Framework 
{
	private int players;
	private CircularLinkedList<Investigator> cll;
	private AncientOne ancientOne;
	
	private int gateLimit;
	private int monsterLimit;
	private int outskirtsLimit;
	private int terrorLevel = 0;
	private int doomTrack = 0;
	
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
		
		this.monsterLimit = this.players + 3;
		this.outskirtsLimit = 8 - this.players;
		this.gateLimit = 9 - (int) (Math.ceil((this.players) / 2.0));
	}
	
	public void setInvestigators(ArrayList<String> names)
	{
		this.cll = new CircularLinkedList<>();
		for(String x : names)
		{
			cll.push(new Investigator(x));
		}
	}
	
	public void setAncientOne(String name)
	{
		this.ancientOne = new AncientOne(name);
	}
	
	public AncientOne getAncientOne()
	{
		return this.ancientOne;
	}
	
	public CircularLinkedList<Investigator> getCList()
	{
		return this.cll;
	}

	public int getTerrorLevel() {
		return terrorLevel;
	}

	public void setTerrorLevel(int terrorLevel) {
		this.terrorLevel = terrorLevel;
	}

	public int getGateLimit() {
		return gateLimit;
	}

	public int getMonsterLimit() {
		return monsterLimit;
	}

	public int getOutskirtsLimit() {
		return outskirtsLimit;
	}

	public int getDoomTrack() {
		return doomTrack;
	}

	public void setDoomTrack(int doomTrack) {
		this.doomTrack = doomTrack;
	}
}
