package core;

public class Interpreter 
{
	private String origin;
	private String[] originArray;
	private String result;
	
	public Interpreter(String stroke)
	{
		this.origin = new String(stroke);
		this.result = new String();
		prepareArray();
		doThing();
		//System.out.println(originArray[3]);
	}
	
	private void prepareArray()
	{
		int counter = 0;
		for(int i = 0; i < origin.length(); i++)
		{
			if(origin.charAt(i) == ' ')
				counter++;				
		}
		originArray = new String[counter];
		
		originArray = origin.split(" ");			
	}
	
	private void doThing()
	{
		String alias = new String();
		for(int i = 0 ; i < originArray.length; i++)
		{
			alias = originArray[i];
			
			while(isConsonant(alias.substring(0, 1)) || !isLetter(alias.substring(0, 1)))
			{
				if(!isLetter(alias.substring(0, 1)))
					break;
				else if(isConsonant(alias.substring(0, 1)))
				{
					alias = moveSymbol(alias);
				}
			}
			alias += "ay";
			
			result = result + " " + alias;
		}
		
	}
	
	private String moveSymbol(String input)
	{
		char stack[] = input.toCharArray();
		char temp = stack[0];
		
		String output;
		
		int i;
		
		for(i = 0; i < stack.length - 1; i++)
		{
			stack[i] = stack[i + 1];
		}
		stack[i] = temp;
		
		output = new String(stack);
		
		return output;
	}
	
	private boolean isLetter(String example)
	{
		if(example.toLowerCase().matches("[a-z]"))
			return true;
		else return false;
	}
	
	private boolean isConsonant(String c)
	{
		if(c.toLowerCase().matches("[bcdfghjklmnpqrstvwxz]"))
			return true;
		else
			return false;
	}
	
	public String getHog()
	{
		String hog = new String(result);
		
		return hog;
	}
}
