package messages;

import java.util.HashMap;
import java.util.ArrayList;

public class Messager 
{
	public static final String[] PHASE_LIST = {"���� ���������", "���� ��������", "�������� � �������", "�������� � ���� �����", "���"};
	
	public static String getMessage(String enter)
	{
		switch (enter)
		{
		case "Blessed":
			return "������� �����. ���� ������ 1, ������������� �����.";
		case "Cursed":
			return "������� �����. ���� ������ 1, ��������� �����.";
		case "Retain":
			return "�������� 1$, ����� ������� �����. ���� ������ 1, ����� �������� ���������.";
		case "Loan":
			return "������� �����. ���� ������ �� 1 �� 3, ��������� 1$. ���� ����� ���, ������� ��� ����. ����� ��� ������ ����� ������.";
		case "Twilight":
			return "� ������� \"���� ���������� �������\" ��������� ������� �� ���������� ���������";
		case "Sheriff":
			return "�������� 1$";
			
			// to be continued
		default:
			break;
		}
		return "No message";
	}
}
