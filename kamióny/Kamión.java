package kamióny;

import containers.Kontajner;

public class Kamión extends Auto{
	
	private final int rychlost = 1;
	private boolean PrivesFull;
	
	Prives prives;					//agregácia - príves
	
	public Kamión(){
		this.nalozeny=false;
		this.PrivesFull=false;
	}
	
	
	public Kamión(Prives prives){		//agregácia
		this.prives=prives;
		this.PrivesFull=true;			//kamión obsahuje príves
		this.nalozeny=false;
	}

	
	@Override
	public void nalozAuto(Kontajner kontajner){				//prekonávajúca metóda
	
		if(kontajner.zistiHmotnost()<6000){
			if (nalozeny) {
				prives.nalozPrives(kontajner);
			}
			else{
				this.nalozeny=true;
			}
		}
	}
	
	/**
	Zisti èi je príves naložený
	*/
	public boolean zistiNakladPrives(){
		return this.PrivesFull;
	}
	
	
}
