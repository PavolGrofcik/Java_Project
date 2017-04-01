package main;
import containers.*;

public class Main {

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Transportný transportný = new Transportný();
		int num =transportný.zistivahu();
		transportný.nastavzaruku(1);
		System.out.println(num + " " + transportný.zistizaruku());
		
	}

}
