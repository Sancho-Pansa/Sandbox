package messages;

import java.util.HashMap;

public class Messager 
{
	public static String getMessage(String enter)
	{
		switch (enter)
		{
		case "Bless":
			return "������� �����. ���� ������ 1, ������������� �����.";
		case "Curse":
			return "������� �����. ���� ������ 1, ��������� �����.";
		case "Retain":
			return "�������� 1$, ����� ������� �����. ���� ������ 1, ����� �������� ���������.";
		case "Loan":
			return "������� �����. ���� ������ �� 1 �� 3, �������� 1$. ���� ����� ���, ������� ��� ����. ����� ��� ������ ����� ������.";
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
