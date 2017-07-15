package mechanics;

import sql.AccessDBConnect;

/**
 * This class describes the main monster - one of the Ancient Ones
 * @author SanchoPansa
 *
 */

public class AncientOne
{
	private String name;
	private byte awakening;
	private String followers;
	private final AccessDBConnect ACCDB = new AccessDBConnect();  
	
	public AncientOne(String name)
	{
		this.setName(name);
	}
	

	public void setName(String name)
	{
		this.name = name;
		this.awakening = new Byte(ACCDB.getAncientField("Awakening", name));
	}
	
	public String getName() 
	{
		return name;
	}


	public byte getAwakening() 
	{
		return awakening;
	}

	public String getFollowers()
	{
		return followers;
	}

}
