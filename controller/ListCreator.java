package controller;


import java.util.ArrayList;

public class ListCreator{
	
	//Generick� met�da na vytvorenie Listu pre ukladanie objetkov r�zneho typu
	public static <T> ArrayList<T> Listcreate(){
	
		return new ArrayList<T>();
	}
	
}

