package mechanics;

import sql.AccessDBConnect;

/**
 * This class describes a single investigator (representative of Player in the world of Arkham Horror)
 * @author SanchoPansa
 *
 */
public class Investigator 
{
	//NB Эта переменная static final, но поля внутри неё могут меняться (если им не прописать final).
	public final static AccessDBConnect ACCDB = new AccessDBConnect();
	
	private final String name;
	private int health;
	private int sanity;
	private int money;
	
	public Investigator(String name)
	{
		this.name = name;
		this.health = new Integer(ACCDB.getField("Health", name));
		this.sanity = new Integer(ACCDB.getField("Sanity", name));
		this.money = new Integer(ACCDB.getField("InitMoney", name));
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
