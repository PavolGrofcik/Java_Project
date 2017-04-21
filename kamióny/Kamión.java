package kamióny;

import containers.Kontajner;

public class Kamión extends Auto{
	private final int rychlost = 2;
	private boolean nalozeny;
	private boolean Sprives;
	
	Prives prives;					//agregácia - príves
	
	public Kamión(boolean prives){
		this.Sprives=true;			//kamión obsahuje príves
		nalozeny=false;
	}

	
	@Override
	public void nalozAuto(Kontajner kontajner){				//prekonávajúca metóda
	
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
