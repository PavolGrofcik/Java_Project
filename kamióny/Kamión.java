package kami�ny;

import containers.Kontajner;

public class Kami�n extends Auto{
	
	private final int rychlost = 2;
	private boolean PrivesFull;
	
	private Prives prives;					//agreg�cia - pr�ves
	
	public Kami�n(){
		this.nalozeny=false;
		this.PrivesFull=false;
	}
	
	
	public Kami�n(Prives prives){		//agreg�cia
		this.prives=prives;
		this.PrivesFull=true;			//kami�n obsahuje pr�ves
		this.nalozeny=false;
	}

	
	@Override
	public void nalozAuto(Kontajner kontajner){				//prekon�vaj�ca met�da
	
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
	
	
	// Funkcia zisti �i je pr�ves nalo�eny
	public boolean zistiNakladPrives() {
		
		return this.PrivesFull;
	}

	public int exportTime(int vzdialenost) {

		this.cas = vzdialenost / rychlost;
		return cas;
	}
	
	
}
