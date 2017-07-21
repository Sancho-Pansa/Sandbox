package messages;

import java.util.HashMap;
import java.util.ArrayList;

public class Messager 
{
	public static final String[] PHASE_LIST = {"Фаза передышки", "Фаза движения", "Контакты в Аркхэме", "Контакты в Иных мирах", "Миф"};
	
	public static String getMessage(String enter)
	{
		switch (enter)
		{
		case "Blessed":
			return "Бросьте кубик. Если выпала 1, благословение снято.";
		case "Cursed":
			return "Бросьте кубик. Если выпала 1, проклятие снято.";
		case "Retain":
			return "Получите 1$, затем бросьте кубик. Если выпала 1, карта гонорара изымается.";
		case "Loan":
			return "Бросьте кубик. Если выпало от 1 до 3, заплатите 1$. Если денег нет, отдаете все вещи. Ссуду вам больше брать нельзя.";
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
