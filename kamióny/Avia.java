package kami�ny;

import containers.Kontajner;

public class Avia extends Auto {

	private final int rychlost = 1;		//rychlost neak od vzdialenosti
	
	public Avia(){
		this.nalozeny=false;
	}
	
	
	public void nalozAuto(Kontajner kontajner){			//p�vodn� met�da
		super.nalozAuto(kontajner);
	}
	
	
	
}
