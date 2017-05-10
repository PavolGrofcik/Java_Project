package kamióny;

import containers.Kontajner;

public class Kamión extends Auto implements Export{
	
	private final int rychlost = 2;
	private boolean PrivesFull;
	
	private Prives prives;					//agregácia - príves
	
	public Kamión(){
		this.nalozeny=false;
		this.PrivesFull=false;
	}
	
	
	public Kamión(Prives prives){
		this.prives=prives;
		this.PrivesFull=false;
		this.nalozeny=false;
		this.nosnost=12000;
	}

	
	@Override
	public void nalozAuto(Kontajner kontajner){				//prekonávajúca metóda
	
		if(kontajner.zistiHmotnost()<6000){
			if (!PrivesFull) {
				this.PrivesFull=true;
				prives.nalozPrives(kontajner);
			}
			else{
				this.nalozeny=true;
			}
		}
	}
	
	//Funkcia zisti èi je kamión plný
	
	public boolean zistiNaklad(){
		return this.nalozeny;
	}
	
	// Funkcia zisti èi je príves naloženy, prives.get nalozeny
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
