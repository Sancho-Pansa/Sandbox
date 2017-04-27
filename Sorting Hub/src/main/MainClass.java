package main;

import java.lang.Math;

import algorithms.*;

public class MainClass 
{
	private int sortingCode;
	private int sortArray[];
	
	public MainClass(int volume, int code)
	{
		this.sortingCode = code;
		sortArray = new int [volume];
		for(int i = 0; i < volume; i++)
		{
			sortArray[i] = (int) (Math.random() * 100);
			System.out.print(sortArray[i] + " ");
		}
		switch(sortingCode)
		{
			case 0:
			{
				Bubble sortClass = new Bubble(sortArray);
				sortArray = sortClass.getArray(); 
				break;
			}
			case 1:
			{
				Gnome sortClass = new Gnome(sortArray);
				sortArray = sortClass.getArray(); 
				break;
			}
			case 2: 
			{
				Insertion sortClass = new Insertion(sortArray);
				sortArray = sortClass.getArray(); 
				break;
			}
			case 3:
			{
				Counting sortClass = new Counting(sortArray);
				sortArray = sortClass.getArray(); 
				break;
			}
			case 4:
			{
				Selection sortClass = new Selection(sortArray);
				sortArray = sortClass.getArray(); 
				break;
			}
			case 5:
			{
				CombSort sortClass = new CombSort(sortArray);
				sortArray = sortClass.getArray(); 
				break;
			}
			case 6:
			{
				QuickSort sortClass = new QuickSort(sortArray);
				sortArray = sortClass.getArray();
			}
			default:
				break;
		}
		for(int i = 0; i < sortArray.length; i++)
		{
			System.out.print(sortArray[i] + " ");
		}
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		MainClass dummy;
		dummy = new MainClass(Integer.valueOf(args[0]), Integer.valueOf(args[1]));

	}

}
