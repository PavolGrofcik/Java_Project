package controller;

import java.util.ArrayList;
/**
 * 
 * @author Pavol Grofèík
 * Trieda urèená na vytvorenie ArrayListu podåa parametra
 */

public class ListCreator{
	
	/**
	 * Metóda slúžiaca na vytvorenie ArrayList-u
	 * @return Objekt ArrayList slúžiaci na serializáciu objektov daného typu
	 * @param T Všeobecný parameter
	 */
	public static <T> ArrayList<T> Listcreate(){															//Generická metóda na vytvorenie ArrayList-u pre ukladanie objetkov daného typu
	
		return new ArrayList<>();
	}
	
}

