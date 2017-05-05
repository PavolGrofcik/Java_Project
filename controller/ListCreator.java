package controller;


import java.util.ArrayList;

public class ListCreator{
	
	//Generická metóda na vytvorenie Listu pre ukladanie objetkov rôzneho typu
	public static <T> ArrayList<T> Listcreate(){
	
		return new ArrayList<T>();
	}
	
}

