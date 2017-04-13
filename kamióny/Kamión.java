package kami�ny;

import containers.Kontajner;

public class Kami�n extends Auto{
	private final int rychlost = 2;
	private boolean nalozeny;
	private boolean Sprives;
	
	Prives prives;					//agreg�cia - pr�ves
	
	public Kami�n(boolean prives){
		this.Sprives=true;			//kami�n obsahuje pr�ves
		nalozeny=false;
	}

	
	@Override
	public void nalozAuto(Kontajner kontajner){				//prekon�vaj�ca met�da
	
		if(kontajner.zistiHmotnost()>5000){
			if (nalozeny) {
				prives.nalozPrives(kontajner);
			}
			else{
				nalozeny=true;
			}
		}
		else {
			this.nalozeny=true;
					
		}
	}
	
	
	public boolean zistiNaklad(){
		return this.nalozeny;
	}
	
}
