package main;

public class DectoBin 
{
	private int dec;
	public char binResult[];
	private int grade;
	
	public DectoBin()
	{
		grade = 0;
	}
	
	public void setDec(int dec)
	{
		this.dec = dec;
	}
	
	public void translateToBin()
	{
		while(dec - Math.pow(2.0, grade) > 0)
		{
			grade++;
		}
		binResult = new char [grade];
		
		for(int i = grade - 1; i >=0; i--)
		{
			if(dec - Math.pow(2.0, i) >= 0)
			{
				binResult[i] = '1';
				dec -= Math.pow(2.0, i);
			}
			else
			{
				binResult[i] = '0';
				continue;
			}
		}
	}
	
	public String getBin()
	{
		char output[] = new char [binResult.length];
		for(int i = 0; i < binResult.length; i++)
		{
			output[i] = binResult[binResult.length - 1 - i];
		}
		String binString = new String(output);
		return binString;
	}
	
	public char[] getBinArray()
	{
		return binResult;
	}
	
}
