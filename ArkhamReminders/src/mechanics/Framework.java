package mechanics;

import java.util.ArrayList;
import list.CircularLinkedList;


public class Framework 
{
	private int players;
	private CircularLinkedList<Investigator> cll;
	private AncientOne ancientOne;
	
	private boolean AncientAwaken = false;
	private boolean mapLimitBreached = false;
	private boolean outskirtsLimitBreached = false;
	private boolean terrorRaised = false;
	
	private int clueNum = 5;
	private int gateNum = 0;
	private int monstersOnMap = 0;
	private int outskirtsMonsters = 0;
	private int gateLimit;
	private int monsterLimit;
	private int outskirtsLimit;
	private int terrorLevel = 0;
	private int doomTrack = 1;
	private int addedMonsters;
	
	// Victory conditions
	private int elderSignsOnMap = 0;
	
	
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
		cll = cll.revert();
	}
	
	public void setAncientOne(String name)
	{
		this.ancientOne = new AncientOne(name);
		if(name.equals("Хастур"))
			this.clueNum = 8;
	}
	
	public void cancelOutskirtsLimit()
	{
		this.outskirtsLimit = (int) Double.POSITIVE_INFINITY;
	}
	
	public AncientOne getAncientOne()
	{
		return this.ancientOne;
	}
	
	public int getClueNum() {
		return clueNum;
	}

	public CircularLinkedList<Investigator> getCList()
	{
		return this.cll;
	}

	public int getTerrorLevel() {
		return this.terrorLevel;
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

	public int getMonstersOnMap() {
		return monstersOnMap;
	}

	public int getMonsteronOutskirts()
	{
		return this.outskirtsMonsters;
	}
	
	public int getAddedMonsters() {
		return addedMonsters;
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
	
	public int getGateNum()
	{
		return this.gateNum;
	}
	
	public int getElderSignsOnMap() {
		return elderSignsOnMap;
	}
	
	public boolean isMapLimit()
	{
		if(this.mapLimitBreached)
		{
			this.mapLimitBreached = false;
			return true;
		}
		else
			return this.mapLimitBreached;
	}
	
	public boolean isOutskirtsLimit()
	{
		if(this.outskirtsLimitBreached)
		{
			this.outskirtsLimitBreached = false;
			return true;
		}
		return this.outskirtsLimitBreached;
	}
	
	public boolean isTerrorRaised()
	{
		if(this.terrorRaised)
		{
			this.terrorRaised = false;
			return true;
		}
		return this.terrorRaised;
	}
	
	public boolean isAwaken()
	{
		return this.AncientAwaken;
	}

	public void createGate(boolean isThereGate)
	{
		this.addedMonsters = 0;
		if(isThereGate)
			for(int i = 0; i < players || i < this.gateNum; i++)
			{
				this.addedMonsters++;
				this.spawnMonster();
			}
		else
		{
			this.gateNum++;
			if(gateNum > this.gateLimit)
				this.AncientAwaken = true;
			if(players > 4)
			{
				this.spawnMonster();
			}
			this.spawnMonster();
		}
	}
	
	public void closeGate()
	{
		if(this.gateNum > 0)
			this.gateNum--;
	}
	
	public void sealGate(boolean hasElderSign)
	{
		if(hasElderSign)
		{
			this.gateNum--;
			this.elderSignsOnMap++;
			this.doomTrack--;
		}
		else
		{
			this.gateNum--;
			this.elderSignsOnMap++;
		}
	}
	
	public void spawnMonster()
	{
		this.addedMonsters = 0;
		this.monstersOnMap++;
		if(this.monstersOnMap > this.monsterLimit)
		{
			this.mapLimitBreached = true;
			this.addOutskirtsMonster();
		}
		System.out.println(this.monstersOnMap);
	}
	public void killMonster()
	{
		this.monstersOnMap--;
	}
	
	public void addOutskirtsMonster()
	{
		this.outskirtsMonsters++;
		if(this.outskirtsMonsters > this.outskirtsLimit && this.terrorLevel < 10)
		{
			this.outskirtsMonsters = 0;
			this.addTerrorLevel();
			this.outskirtsLimitBreached = true;
			System.out.println("Limit breached");
		}
	}
	
	public void minusOutskirtsMonster()
	{
		this.outskirtsMonsters--;
	}
	
	public void addTerrorLevel()
	{
		this.terrorLevel++;
		this.terrorRaised = true;
		if(this.terrorLevel >= 10)
			this.addDoom();
	}
	
	public void minusTerrorLevel()
	{
		this.terrorLevel--;
	}
	
	public void addDoom()
	{
		this.doomTrack++;
		if(this.doomTrack >= this.ancientOne.getAwakening())
			this.AncientAwaken = true;
	}
	
	public void minusDoom()
	{
		if(this.doomTrack >= 0)
			this.doomTrack--;
	}
}
