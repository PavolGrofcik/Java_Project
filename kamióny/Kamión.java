package kami�ny;

import containers.Kontajner;

public class Kami�n extends Auto implements Export{
	
	private final int rychlost = 2;
	private boolean PrivesFull;
	
	private Prives prives;					//agreg�cia - pr�ves
	
	public Kami�n(){
		this.nalozeny=false;
		this.PrivesFull=false;
	}
	
	
	public Kami�n(Prives prives){
		this.prives=prives;
		this.PrivesFull=false;
		this.nalozeny=false;
		this.nosnost=12000;
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
	
	//Funkcia zisti �i je kami�n pln�
	
	public boolean zistiNaklad(){
		return this.nalozeny;
	}
	
	// Funkcia zisti �i je pr�ves nalo�eny, prives.get nalozeny
	public boolean zistiNakladPrives() {
		
		return this.PrivesFull;
	}
	
	
	public int exportTime(int vzdialenost) {

		this.cas = vzdialenost / rychlost;
		return cas;
	}


	@Override
	public int ExportTime(int vzdialenost) {

		cas=(vzdialenost/rychlost)/100;
		
		return cas;
	}
	
	
}
