package kami�ny;

import containers.Kontajner;

public class Prives {
	
	private final int nosnost=6000;
	private Boolean nalozeny;
	
	public Prives(){												//hmotnos� je useless v tomto pr�pade?? delete ?
		this.nalozeny=false;
	}
	
	//Kon�truktor
	public Prives(Kontajner kontajner){
		nalozPrives(kontajner);
	}
	
	
	public int getNosnost(){
			return this.nosnost;
	}
	
	
	public void nalozPrives(Kontajner kontajner){
		
		if (kontajner.zistiHmotnost()<nosnost) {
			this.nalozeny=true;
		}
		
	}
	
	public boolean getNalozeny(){
		return this.nalozeny;
	}
	
}
