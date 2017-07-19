package mechanics;

import sql.AccessDBConnect;

/**
 * This class describes a single investigator (representative of Player in the world of Arkham Horror)
 * @author SanchoPansa
 *
 */
public class Investigator 
{
	//NB: Эта переменная static final, но поля внутри неё могут меняться (если им не прописать final).
	public final static AccessDBConnect ACCDB = new AccessDBConnect();
	
	private final String name;
	private final int maxHealth;
	private final int maxSanity;
	private final int initMoney;
	private byte blessing = 0;
	
	private int health;
	private int sanity;
	private int money;
	
	public Investigator(String name)
	{
		this.name = name;
		this.maxHealth = new Integer(ACCDB.getInvestField("Health", name));
		this.health = this.maxHealth;
		this.maxSanity = new Integer(ACCDB.getInvestField("Sanity", name));
		this.sanity = this.maxSanity;
		this.initMoney = new Integer(ACCDB.getInvestField("InitMoney", name));
		this.money = this.initMoney;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
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

	public String getName() {
		return name;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMaxSanity() {
		return maxSanity;
	}

	public int getInitMoney() {
		return initMoney;
	}

	public boolean isBlessed()
	{
		return this.blessing == 1;
	}
	
	public boolean isCursed()
	{
		return this.blessing == -1;
	}
}
