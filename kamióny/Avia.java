package kami�ny;

import containers.Kontajner;

public class Avia extends Auto {

	private final int rychlost = 1;		//Ka�d� druh vozidla je �pecifick� svojou r�chlostou a nosnostou
	
	public Avia(){
		this.nalozeny=false;
	}
	
	
	public void nalozAuto(Kontajner kontajner){			//p�vodn� met�da
		super.nalozAuto(kontajner);
	}
	
	public int exportTime(int vzdialenost){
		
		this.cas = vzdialenost/rychlost;
		return cas;
	}
	
	
}
