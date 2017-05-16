package controller;

import java.util.ArrayList;
/**
 * 
 * @author Pavol Grof��k
 * Trieda ur�en� na vytvorenie ArrayListu pod�a parametra
 */

public class ListCreator{
	
	/**
	 * Met�da sl��iaca na vytvorenie ArrayList-u
	 * @return Objekt ArrayList sl��iaci na serializ�ciu objektov dan�ho typu
	 * @param T V�eobecn� parameter
	 */
	public static <T> ArrayList<T> Listcreate(){															//Generick� met�da na vytvorenie ArrayList-u pre ukladanie objetkov dan�ho typu
	
		return new ArrayList<>();
	}
	
}

