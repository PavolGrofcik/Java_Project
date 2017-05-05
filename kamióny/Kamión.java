package kamióny;

import containers.Kontajner;

public class Kamión extends Auto{
	
	private final int rychlost = 2;
	private boolean PrivesFull;
	
	private Prives prives;					//agregácia - príves
	
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
				this.PrivesFull=true;
			}
			else{
				this.nalozeny=true;
			}
		}
	}
	
	
	// Funkcia zisti èi je príves naloženy
	public boolean zistiNakladPrives() {
		
		return this.PrivesFull;
	}

	public int exportTime(int vzdialenost) {

		this.cas = vzdialenost / rychlost;
		return cas;
	}
	
	
}
