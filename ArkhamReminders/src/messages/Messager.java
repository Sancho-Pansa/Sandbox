package messages;

import java.util.HashMap;

public class Messager 
{
	public static String getMessage(String enter)
	{
		switch (enter)
		{
		case "Bless":
			return "Бросьте кубик. Если выпала 1, благословение снято.";
		case "Curse":
			return "Бросьте кубик. Если выпала 1, проклятие снято.";
		case "Retain":
			return "Получите 1$, затем бросьте кубик. Если выпала 1, карта гонорара изымается.";
		case "Loan":
			return "Бросьте кубик. Если выпало от 1 до 3, заплатит 1$. Если денег нет, отдаете все вещи. Ссуду вам больше брать нельзя.";
		case "Twilight":
			return "В локации \"Ложа Серебряных Сумерек\" проходите контакт во Внутреннем святилище";
		case "Sheriff":
			return "Возьмите 1$";
			
			// to be continued
		default:
			break;
		}
		return "No message";
	}
}
