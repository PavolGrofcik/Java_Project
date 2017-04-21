package kamióny;

import containers.Kontajner;

public class Avia extends Auto {

	private final int rychlost = 1;
	private boolean nalozeny;
	
	public Avia(){
		nalozeny=false;
	}
	
	
	public void nalozAuto(Kontajner kontajner){
		super.nalozAuto(kontajner);
		nalozeny=true;
	}
	
	
	public boolean zistiNaklad(){
		return this.nalozeny;
	}
	
	
}
