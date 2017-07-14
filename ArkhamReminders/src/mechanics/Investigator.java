package mechanics;

import sql.AccessDBConnect;

/**
 * This class describes a single investigator (representative of Player in the world of Arkham Horror)
 * @author SanchoPansa
 *
 */
public class Investigator 
{
	private static AccessDBConnect accdb = new AccessDBConnect();
	
	private final String name;
	private int health;
	private int sanity;
	private int money;
	
	public Investigator(String name)
	{
		this.name = name;
		this.health = new Integer(accdb.getField("Health", name));
		this.sanity = new Integer(accdb.getField("Sanity", name));
	}

	public String getName() {
		return name;
	}

	public int getSanity() {
		return sanity;
	}

	public void setSanity(int sanity) {
		this.sanity = sanity;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getHealth() {
		return health;
	}
}
