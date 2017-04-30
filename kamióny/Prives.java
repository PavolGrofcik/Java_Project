package kamiÛny;

import containers.Kontajner;

public class Prives {
	
	private final int nosnost=6000;
	private Boolean nalozeny;
	
	public Prives(){												//hmotnosù je useless v tomto prÌpade?? delete ?
		this.nalozeny=false;
	}
	
	//Konötruktor
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
