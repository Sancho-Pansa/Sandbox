package main;
public class BinToGray 
{
	private boolean[] medium;
	private boolean[] result;
	private char[] input;
	
	public BinToGray(DectoBin input)
	{
		this.input = input.binResult;
		medium = new boolean[this.input.length];
		result = new boolean[this.input.length];
	}
	
	public void Translate()
	{
		for(int i = 0; i < input.length; i++)
		{
			if(input[input.length - 1 - i] == '1')
				medium[i] = true;
			else
				medium[i] = false;
		}
		
		for(int i = medium.length - 1; i >=0 ; i--)
		{
			if(i == 0)
				result[i] = medium[i] ^ false;
			else
				result[i] = medium[i] ^ medium [i - 1];				
		}
	}
	
	public String getGray()
	{
		for(int i = 0; i < result.length; i++)
		{
			if(result[i])
				input[i] = '1';
			else
				input[i] = '0';
		}
		String grayRes = new String(input);
		return grayRes;
	}

}
