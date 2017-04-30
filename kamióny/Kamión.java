package kami�ny;

import containers.Kontajner;

public class Kami�n extends Auto{
	
	private final int rychlost = 1;
	private boolean PrivesFull;
	
	Prives prives;					//agreg�cia - pr�ves
	
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
			}
			else{
				this.nalozeny=true;
			}
		}
	}
	
	/**
	Zisti �i je pr�ves nalo�en�
	*/
	public boolean zistiNakladPrives(){
		return this.PrivesFull;
	}
	
	
}
