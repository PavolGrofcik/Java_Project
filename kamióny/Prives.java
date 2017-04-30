package kamióny;

import containers.Kontajner;

public class Prives {
	
	private final int nosnost=6000;
	private Boolean nalozeny;
	
	public Prives(){												//hmotnosť je useless v tomto prípade?? delete ?
		this.nalozeny=false;
	}
	
	//Konštruktor
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
